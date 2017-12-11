/**
 * 
 */
package version1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class mayKnow extends ActionSupport {
  private int ID; // ×Ô¼ºµÄID
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  ArrayList<Map<String, String>> pmn;
  
  public int getID() {
    return ID;
  }
  public void setID(int iD) {
    ID = iD;
  }

  public ArrayList<Map<String, String>> getPmn() {
    return pmn;
  }
  public void setPmn(ArrayList<Map<String, String>> pmn) {
    this.pmn = pmn;
  }
  
  public String execute() throws Exception {
    try {
      DBcrud conn = new DBcrud();
      ArrayList<Map<String,Object>> l = conn.getBasicInfo(ID);
      String name = l.get(0).get("lastName")+" "+l.get(0).get("firstName");

      pmn = conn.peopleMayKnow(ID, name);
      if (pmn == null) {
        return "ERROR";
      }
      else if(pmn.size() == 0) {
        return "NoResult";
      }
      return "SUCCESS";
    } catch (Exception e) {
      return "ERROR";
    }
  }
 
}