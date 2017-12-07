package version1;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class AddPeriodInfo extends ActionSupport{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public Date getBpDate() {
    return bpDate;
  }
  public void setBpDate(Date bpDate) {
    this.bpDate = bpDate;
  }
  public Date getMpDate() {
    return mpDate;
  }
  public void setMpDate(Date mpDate) {
    this.mpDate = mpDate;
  }
  public Date getDpDate() {
    return dpDate;
  }
  public void setDpDate(Date dpDate) {
    this.dpDate = dpDate;
  }
  public String getBpSchool() {
    return bpSchool;
  }
  public void setBpSchool(String bpSchool) {
    this.bpSchool = bpSchool;
  }
  public String getMpSchool() {
    return mpSchool;
  }
  public void setMpSchool(String mpSchool) {
    this.mpSchool = mpSchool;
  }
  public String getDpSchool() {
    return dpSchool;
  }
  public void setDpSchool(String dpSchool) {
    this.dpSchool = dpSchool;
  }

  private String id;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  private Date bpDate;
  private Date mpDate;
  private Date dpDate;
  private String bpSchool;
  private String mpSchool;
  private String dpSchool;
  
public String execute() throws Exception{
    
    Boolean fail = false;
    HttpServletRequest request = ServletActionContext.getRequest();
    int id = Integer.parseInt(CookieCtrl.getCookie(request)); 
    SchoolInfo bInfor = new SchoolInfo("bachelor", getBpSchool(), getBpDate());
    SchoolInfo mInfor = new SchoolInfo("master", getMpSchool(), getMpDate());
    SchoolInfo dInfor = new SchoolInfo("doctor", getDpSchool(), getDpDate());
    
    DBcrud conn = new DBcrud();
    if (!conn.insertPeriodInfo(id, bInfor));
      fail = true;
    if (!conn.insertPeriodInfo(id, mInfor));
      fail = true;
    if (!conn.insertPeriodInfo(id, dInfor));
      fail = true;

    if (fail) {
      return "SUCCESS";
    }
    else return "ERROR";
  }
}
