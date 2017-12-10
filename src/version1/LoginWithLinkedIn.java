package version1;

import java.io.Serializable;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class LoginWithLinkedIn extends ActionSupport implements Serializable{
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
    //String tempCode=(String)request.getSession().getAttribute("linkStatus");  
    //request.getSession().removeAttribute("linkStatus");  
    
    /*��ֹ��վ����*/  
    /*if(null==tempCode||null==getState()||!tempCode.trim().equalsIgnoreCase(getState().trim())){  
        System.out.println("���ƿ�վ����");  
        return "ERROR";  
    }*/  
    
    /*�жϵ�һ���Ƿ�õ�Code*/  
    if(code==null ||code.length() == 0){  
        //��¼ʧ�ܴ���    
        String errorDes=request.getParameter("error_description");  
        System.out.println(errorDes);  
        return "ERROR"; 
    }else{  

        //int serverPost = request.getServerPort();  
        String basePath = linkedInCtrl.getBaseUrl(request);
        //String redirectUri = basePath + "/linkedInProcess"; 
        //String redirectUri = "http://localhost:8080/Ttree/LoginWithLinkedIn";
        String redirectUri =  "http://tomato.applinzi.com/Ttree/LoginWithLinkedIn";
        JSONObject tokenInfo=linkedInCtrl.getAccessToken(getTokenUrl, clientId, clientSecrte, code, "authorization_code", redirectUri);  
        System.out.println(getCode());
        if(null==tokenInfo){  
          System.out.println("�������Ƴ���");  
          return "ERROR";   
        }  
        if(!tokenInfo.containsKey("access_token")){  
            /*��ȡTokenʧ�ܴ���*/  
            //��¼ʧ�ܴ���  
          System.out.println("����������");  
          return "ERROR";  
        }else{  
            /*��Token��ȡ�û���Ϣ*/  
            String accessToken=tokenInfo.getString("access_token");  
            if(accessToken == null || accessToken.length() == 0) {
              System.out.println("����Ϊ��");  
              return "ERROR";
            } 
            System.out.println("����");
            System.out.println(accessToken);
            JSONObject userInfo=linkedInCtrl.getUserInfo(getUserInfoUrl, accessToken); 
            System.out.println("firstNmae");
            System.out.println(userInfo.get("firstName").toString());
            System.out.println(userInfo);
            
            if(!userInfo.containsKey("firstName")){  
                /*��ȡ��Ϣʧ��*/  
              System.out.println("��Ϣ��ȡʧ��");  
              return "ERROR";
            }else{  
                /*��ȡ��Ϣ�ɹ�*/  
                //�����û�  
             
                DBcrud conn = new DBcrud();
                int treeId = conn.checkExist(userInfo.getString("id"), "linkedin");
                if (treeId > 0) {
                  id = String.format("%0" + 5 + "d", treeId);
                  Cookie cookie = CookieCtrl.addCookie(id, request);
                  System.out.println(cookie);
                  HttpServletResponse response = ServletActionContext.getResponse();
                  response.addCookie(cookie);
                  
                  return "LOGIN";
                }
            
                else if (treeId == -2) return "ERROR";
                else {
                    CampusUser user=new CampusUser(); 
                    user.setFirstName(userInfo.getString("firstName"));
                    if (userInfo.containsKey("lastName")) user.setLastName(userInfo.getString("lastName"));  
                    if (userInfo.containsKey("headline")) user.setHeadline(userInfo.getString("headline"));
                    if (userInfo.containsKey("location")) {
                        System.out.println(userInfo.get("location"));
                          JSONObject jsb = JSONObject.fromObject(userInfo.getString("location"));
                          System.out.println(jsb);
                          System.out.println(jsb.getString("name"));
                          user.setLocation(jsb.getString("name"));
                    }
                    if (userInfo.containsKey("industry")) user.setIndustry(userInfo.getString("industry"));
                    if (userInfo.containsKey("pictureUrl")) user.setPicture_url(userInfo.getString("pictureUrl"));
                    if (userInfo.containsKey("publicProfileUrl")) user.setProfile_url(userInfo.getString("publicProfileUrl"));
                    if (userInfo.containsKey("emailAddress")) user.setEmail(userInfo.getString("emailAddress"));
                    int idint = conn.saveCode(userInfo.getString("id"), accessToken, "linkedin");
                    if (idint == -1) return "ERROR";
                    id = String.format("%0" + 5 + "d", idint);
                    user.setID(id);
                 // request.getSession().setAttribute("userId", id);  
                   
                  Cookie cookie = CookieCtrl.addCookie(id, request);
                  HttpServletResponse response = ServletActionContext.getResponse();
                  response.addCookie(cookie);
                  conn.insertBasicInfo(user);
                  return "SUCCESS";
                }
            }  
        }
    } 
  }
}
