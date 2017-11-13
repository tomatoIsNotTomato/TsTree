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
  String tel_old;
  String tel_new;
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
  public String getTel_old() {
    return tel_old;
  }
  public void setTel_old(String tel_old) {
    this.tel_old = tel_old;
  }
  public String getTel_new() {
    return tel_new;
  }
  public void setTel_new(String tel_new) {
    this.tel_new = tel_new;
  }
  public String getPeriod() {
    return period;
  }
  public void setPeriod(String period) {
    this.period = period;
  }
  public String getPostName() {
    return postName;
  }
  public void setPostName(String postName) {
    this.postName = postName;
  }
  String period;
  String postName;
  public String execute() throws Exception{
    try {
        DBcrud conn = new DBcrud();
        
        if(conn.update_t_s_info(ID, id,preName, postName, period, relation,tel_old,tel_new)) {
          return "SUCCESS";
        }
        else return "ERROR";
        
    }catch (Exception e) {
      return "ERROR";
    }
  }
}
