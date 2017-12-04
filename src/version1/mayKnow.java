/**
 * 
 */
package version1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dell
 *
 */
@SuppressWarnings("serial")
public class mayKnow extends ActionSupport {
  private int ID; // ×Ô¼ºµÄID
  private String period;
  private String str;
  private int row;
  ArrayList<CampusUser> a1=new ArrayList<CampusUser>();
  ArrayList<CampusUser> a2=new ArrayList<CampusUser>();
  ArrayList<CampusUser> a3=new ArrayList<CampusUser>();
  ArrayList<CampusUser> a4=new ArrayList<CampusUser>();
  ArrayList<CampusUser> a5=new ArrayList<CampusUser>();
  
  public int getID() {
	return ID;
}


public void setID(int iD) {
	ID = iD;
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
      a1=conn.mayKnow(ID,"bachelor");
      a2=conn.mayKnow(ID,"master");
      a3=conn.mayKnow(ID,"doctor");
      a4=conn.mayKnow2(ID, "headline", 4);
      a4=conn.mayKnow2(ID, "location", 5);
      a5=conn.mayKnow2(ID, "industry", 6);
    } catch (Exception e) {
      return "ERROR";
    }
    return "SUCCESS";
  }


public ArrayList<CampusUser> getA1() {
	return a1;
}


public void setA1(ArrayList<CampusUser> a1) {
	this.a1 = a1;
}


public ArrayList<CampusUser> getA2() {
	return a2;
}


public void setA2(ArrayList<CampusUser> a2) {
	this.a2 = a2;
}


public ArrayList<CampusUser> getA3() {
	return a3;
}


public void setA3(ArrayList<CampusUser> a3) {
	this.a3 = a3;
}


public ArrayList<CampusUser> getA4() {
	return a4;
}


public void setA4(ArrayList<CampusUser> a4) {
	this.a4 = a4;
}


public ArrayList<CampusUser> getA5() {
	return a5;
}


public void setA5(ArrayList<CampusUser> a5) {
	this.a5 = a5;
}
}
