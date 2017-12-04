package version1;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class LoginWithLinkedIn extends ActionSupport{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String clientId = "81j634z5c30h61";
  private String clientSecrte = "Hy6SEzFPBnuqXUhx";
  private String id;
  private String code;
  private String state;
  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setId(String id) {
    this.id = id;
  }
   
  public String execute() throws SQLException {
    HttpServletRequest request = ServletActionContext.getRequest();
    String getTokenUrl="https://www.linkedin.com/oauth/v2/accessToken"; 
    String getUserInfoUrl="https://api.linkedin.com/v1/people/~";  
    /*String code=request.getParameter("code");  
    String status=request.getParameter("state");  */
    String tempCode=(String)request.getSession().getAttribute("linkStatus");  
    request.getSession().removeAttribute("linkStatus");  
    
    /*防止跨站攻击*/  
    if(null==tempCode||null==getState()||!tempCode.trim().equalsIgnoreCase(getState().trim())){  
        System.out.println("疑似跨站攻击");  
        return "ERROR";  
    }  
    
    /*判断第一步是否得到Code*/  
    if(code==null ||code.length() == 0){  
        //登录失败处理    
        String errorDes=request.getParameter("error_description");  
        System.out.println(errorDes);  
        return "ERROR"; 
    }else{  

        //int serverPost = request.getServerPort();  
        String basePath = linkedInCtrl.getBaseUrl(request);
        //String redirectUri = basePath + "/linkedInProcess"; 
        String redirectUri = "http://localhost:8080/Ttree/LoginWithLinkedIn";
        JSONObject tokenInfo=linkedInCtrl.getAccessToken(getTokenUrl, clientId, clientSecrte, code, "authorization_code", redirectUri);  
        System.out.println(getCode());
        if(null==tokenInfo){  
          System.out.println("返回令牌出错");  
          return "ERROR";   
        }  
        if(!tokenInfo.containsKey("access_token")){  
            /*获取Token失败处理*/  
            //登录失败处理  
          System.out.println("不包含令牌");  
          return "ERROR";  
        }else{  
            /*用Token获取用户信息*/  
            String accessToken=tokenInfo.getString("access_token");  
            if(accessToken == null || accessToken.length() == 0) {
              System.out.println("令牌为空");  
              return "ERROR";
            } 
            System.out.println("令牌");
            System.out.println(accessToken);
            JSONObject userInfo=linkedInCtrl.getUserInfo(getUserInfoUrl, accessToken); 
            System.out.println("firstNmae");
            System.out.println(userInfo.get("firstName").toString());
            System.out.println(userInfo);
            
            if(!userInfo.containsKey("firstName")){  
                /*获取信息失败*/  
              System.out.println("信息获取失败");  
              return "ERROR";
            }else{  
                /*获取信息成功*/  
                //保存用户  
             
                DBcrud conn = new DBcrud();
                int flag = conn.checkExist(userInfo.getString("id"), "linkedin");
                if (flag == 0) return "SUCCESS";
                else if (flag == -1) return "ERROR";
                else {

                    CampusUser user=new CampusUser(); 
                    user.setFirstName(userInfo.getString("firstName"));
                    if (userInfo.containsKey("lastName")) user.setLastName(userInfo.getString("lastName"));  
                    if (userInfo.containsKey("headline")) user.setHeadline(userInfo.getString("headline"));
                    if (userInfo.containsKey("location")) user.setLocation(userInfo.getString("location"));
                    if (userInfo.containsKey("industry")) user.setIndustry(userInfo.getString("industry"));
                    if (userInfo.containsKey("picture_url")) user.setPicture_url(userInfo.getString("picture_url"));
                    if (userInfo.containsKey("public-profile-url")) user.setProfile_url(userInfo.getString("public-profile-url"));
                    if (userInfo.containsKey("email-address")) user.setEmail(userInfo.getString("email-address"));
                    int idint = conn.saveCode(userInfo.getString("id"), accessToken, "linkedin");
                    if (idint == -1) return "ERROR";
                    id = String.format("%0" + 5 + "d", idint);
                  return "SUCCESS";
                }
            }  
        }
    } 
  }
  
  
}
