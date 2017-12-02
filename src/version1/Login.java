package version1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String email;
  private String pwd;
  
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
    if (conn.loginJudge(getEmail(), getPwd())) {
      HttpServletRequest request = ServletActionContext.getRequest();
      request.getSession().setAttribute("userEmail", getEmail());
      return "SUCCESS";
    }
    else return "ERROR";
  }
  
}
