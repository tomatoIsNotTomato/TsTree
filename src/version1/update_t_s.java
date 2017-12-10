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
public class update_t_s extends ActionSupport{
  int ID;
  String preName;
  String relation;
  int id;
  String email_old;
  String email_new;
  public int getID() {
    return ID;
  }
  public void setID(int iD) {
    ID = iD;
  }
  public String getPreName() {
    return preName;
  }
  public void setPreName(String preName) {
    this.preName = preName;
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
  public String getEmail_old() {
    return email_old;
  }
  public void setEmail_old(String email_old) {
    this.email_old = email_old;
  }
  public String getEmail_new() {
    return email_new;
  }
  public void setEmail_new(String email_new) {
    this.email_new = email_new;
  }
  public String getPeriod() {
    return period;
  }
  public void setPeriod(String period) {
    this.period = period;
  }
  
  String period;
  String postFirstName;
  String postLastName;
  public String getPostFirstName() {
    return postFirstName;
  }
  public void setPostFirstName(String postFirstName) {
    this.postFirstName = postFirstName;
  }
  public String getPostLastName() {
    return postLastName;
  }
  public void setPostLastName(String postLastName) {
    this.postLastName = postLastName;
  }
  public String execute() throws Exception{
    try {
        DBcrud conn = new DBcrud();
        String postName = postLastName+" "+postFirstName;
        if(conn.update_t_s_info(ID, id,preName, postName, period, relation,email_old,email_new)) {
          return "SUCCESS";
        }
        else return "ERROR";
        
    }catch (Exception e) {
      return "ERROR";
    }
  }
}
