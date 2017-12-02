package version1;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Register extends ActionSupport{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String firstName;
  private String ID;
  private String lastName;
  private String headline;
  private String location;
  private String industry;
  private String email;
  private String picture_url;
  private String profile_url;
  private String pwd;

 
  private Date bpDate;
  private Date mpDate;
  private Date dpDate;
  private String bpSchool;
  private String mpSchool;
  private String dpSchool;
  
  public String getPwd() {
    return pwd;
  }
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getID() {
    return ID;
  }
  public void setID(String iD) {
    ID = iD;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getHeadline() {
    return headline;
  }
  public void setHeadline(String headline) {
    this.headline = headline;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getIndustry() {
    return industry;
  }
  public void setIndustry(String industry) {
    this.industry = industry;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPicture_url() {
    return picture_url;
  }
  public void setPicture_url(String picture_url) {
    this.picture_url = picture_url;
  }
  public String getProfile_url() {
    return profile_url;
  }
  public void setProfile_url(String profile_url) {
    this.profile_url = profile_url;
  }
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
  
  
  public String execute() throws Exception{
    
    
    CampusUser user = new CampusUser(getFirstName(), getLastName(), getHeadline(), getLocation(), getIndustry(), getEmail(), getPicture_url(), getProfile_url());
    
    HttpServletRequest request = ServletActionContext.getRequest();
    
    SchoolInfo bInfor = new SchoolInfo("bachelor", getBpSchool(), getBpDate());
    SchoolInfo mInfor = new SchoolInfo("master", getMpSchool(), getMpDate());
    SchoolInfo dInfor = new SchoolInfo("doctor", getDpSchool(), getDpDate());
    
    user.setBachelorPeriod(bInfor);
    user.setMasterPeriod(mInfor);
    user.setDoctorPeriod(dInfor);
    
    DBcrud conn = new DBcrud();
    int id = conn.saveCode(getEmail(), getPwd());
    if (id == -1) return "ERROR";
    user.setID(String.valueOf(id));
    setID(String.valueOf(id));
    ID = user.userID();
    request.getSession().setAttribute("userEmail", email);
    if (conn.insertBasicInfo(user)) {
      return "SUCCESS";
    }
    else return "ERROR";
  }
}


