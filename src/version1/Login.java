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
  private String id;
  private String pwd;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPwd() {
    return pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  
 public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    System.out.println(getId()+getPwd());
    if (conn.loginJudge(Integer.parseInt(getId()), getPwd())) {
      id = String.format("%0" + 5 + "d", Integer.parseInt(id) );
      HttpServletRequest request = ServletActionContext.getRequest();
      request.getSession().setAttribute("userID", id);
      return "SUCCESS";
    }
    else return "ERROR";
  }
  
}
