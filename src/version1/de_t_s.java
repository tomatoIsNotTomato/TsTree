/**
 * 
 */
package version1;

import java.sql.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dell
 *
 */
@SuppressWarnings("serial")
public class de_t_s extends ActionSupport {
 
  private String name;
  

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private String relation;
  private int id;
  private String email;
  private String period;

  
  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }
  
  public String execute() throws Exception {
    try {
      DBcrud conn = new DBcrud();
      String n[] = name.split(" ");
      HttpServletRequest request = ServletActionContext.getRequest();
      String ID = CookieCtrl.getCookie(request);
      conn.de_t_s_info(Integer.parseInt(ID), id, n[0]+" "+n[1],  period, relation, email);

    } catch (Exception e) {
      return "ERROR";
    }
    return "SUCCESS";
  }
}
