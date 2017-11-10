package version1;

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
    if (conn.loginJudge(Integer.parseInt(getId()), getPwd())) return SUCCESS;
    else return "ERROR";
  }
  
}
