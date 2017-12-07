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
    int id = conn.loginJudge(getEmail(), getPwd());
    if (id > 0) {
      Cookie cookie = CookieCtrl.addCookie(String.format("%0" + 5 + "d", id));
      HttpServletResponse response = ServletActionContext.getResponse();
      response.addCookie(cookie);
      /*HttpServletRequest request = ServletActionContext.getRequest();
      request.getSession().setAttribute("userEmail", getEmail());*/
      return "SUCCESS";
    }
    else if(id == 0) return "ERROR";
    else return "ERROR";
  }
  
}




