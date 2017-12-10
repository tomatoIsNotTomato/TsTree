package version1;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String email;
  private String pwd;
  private String id;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPwd() {
    return pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  
 public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    System.out.println(getEmail()+getPwd());
    int ID = conn.loginJudge(getEmail(), getPwd());
    if (ID>0) {
      int re = conn.checkActive(ID);
      id = String.format("%0" + 5 + "d", ID);
      if (re == 1) {
        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie cookie = CookieCtrl.addCookie(id, request);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.addCookie(cookie);
        /*HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("userEmail", getEmail());*/
        return "SUCCESS";
      }
      else if (re == 0){
        return "VAD";
      }
    }
   
    return "ERROR";
  }
  
}




