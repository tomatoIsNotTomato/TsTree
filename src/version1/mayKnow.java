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
  HashSet<NameIdPair> a1=new HashSet<NameIdPair>();
  HashSet<NameIdPair> a2=new HashSet<NameIdPair>();
  HashSet<NameIdPair> a3=new HashSet<NameIdPair>();
  HashSet<NameIdPair> a4=new HashSet<NameIdPair>();
  HashSet<NameIdPair> a5=new HashSet<NameIdPair>();
  HashSet<NameIdPair> a6=new HashSet<NameIdPair>();
  ArrayList<CampusUser> a7=new ArrayList<CampusUser>();
  ArrayList<CampusUser> a8=new ArrayList<CampusUser>();
  ArrayList<CampusUser> a9=new ArrayList<CampusUser>();
  
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
      a1=conn.mayKnow(ID,"bachelor","teacher");
      a2=conn.mayKnow(ID,"bachelor","student");
      a3=conn.mayKnow(ID,"master","teacher");
      a4=conn.mayKnow(ID,"master","student");
      a5=conn.mayKnow(ID,"doctor","teacher");
      a6=conn.mayKnow(ID,"doctor","student");
      a7=conn.mayKnow2(ID, "headline", 4);
      a8=conn.mayKnow2(ID, "location", 5);
      a9=conn.mayKnow2(ID, "industry", 6);
    } catch (Exception e) {
      return "ERROR";
    }
    return "SUCCESS";
  }


public HashSet<NameIdPair> getA1() {
	return a1;
}


public void setA1(HashSet<NameIdPair> a1) {
	this.a1 = a1;
}


public HashSet<NameIdPair> getA2() {
	return a2;
}


public void setA2(HashSet<NameIdPair> a2) {
	this.a2 = a2;
}


public HashSet<NameIdPair> getA3() {
	return a3;
}


public void setA3(HashSet<NameIdPair> a3) {
	this.a3 = a3;
}


public HashSet<NameIdPair> getA4() {
	return a4;
}


public void setA4(HashSet<NameIdPair> a4) {
	this.a4 = a4;
}


public HashSet<NameIdPair> getA5() {
	return a5;
}


public void setA5(HashSet<NameIdPair> a5) {
	this.a5 = a5;
}


public HashSet<NameIdPair> getA6() {
	return a6;
}


public void setA6(HashSet<NameIdPair> a6) {
	this.a6 = a6;
}


public ArrayList<CampusUser> getA7() {
	return a7;
}


public void setA7(ArrayList<CampusUser> a7) {
	this.a7 = a7;
}


public ArrayList<CampusUser> getA8() {
	return a8;
}


public void setA8(ArrayList<CampusUser> a8) {
	this.a8 = a8;
}


public ArrayList<CampusUser> getA9() {
	return a9;
}


public void setA9(ArrayList<CampusUser> a9) {
	this.a9 = a9;
}


}