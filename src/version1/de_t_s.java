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
  private int ID; // ×Ô¼ºµÄID
  private String Name;
  private String relation;
  private int id;
  private String tel;
  private String period;

  public int getID() {
    return ID;
  }

  public void setID(int iD) {
    ID = iD;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

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

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
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
      
      conn.de_t_s_info(ID, id, Name, period, relation, tel);

    } catch (Exception e) {
      return "ERROR";
    }
    return "SUCCESS";
  }
}
