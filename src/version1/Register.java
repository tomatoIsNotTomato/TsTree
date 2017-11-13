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
  private String name;
  private String pwd;
  private String ID;
  private Date birthDay;
  private String sex;
  private String place;
  private String phoneNumber;
  private String job;
  private Date bpDate;
  private String linkedIn;

  public String getLinkedIn() {
    return linkedIn;
  }
  public void setLinkedIn(String linkedIn) {
    this.linkedIn = linkedIn;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }


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

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getID() {
    return ID;
  }
  public void setID(String iD) {
    ID = iD;
  }
  public Date getBirthDay() {
    return birthDay;
  }
  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getPlace() {
    return place;
  }
  public void setPlace(String place) {
    this.place = place;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public String getJob() {
    return job;
  }
  public void setJob(String job) {
    this.job = job;
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
    
    Boolean sexi = true;
    if (getSex().equals("1")) {
      sexi = false;
    }
    CampusUser user = new CampusUser(getName(),getBirthDay(),sexi, getPlace(), getPhoneNumber(),getJob(), getLinkedIn());
    
    HttpServletRequest request = ServletActionContext.getRequest();
    
    SchoolInfo bInfor = new SchoolInfo("bachelor", getBpSchool(), getBpDate());
    SchoolInfo mInfor = new SchoolInfo("master", getMpSchool(), getMpDate());
    SchoolInfo dInfor = new SchoolInfo("doctor", getDpSchool(), getDpDate());
    
    user.setBachelorPeriod(bInfor);
    user.setMasterPeriod(mInfor);
    user.setDoctorPeriod(dInfor);
    
    DBcrud conn = new DBcrud();
    int id = conn.saveCode(getName(), getPwd());
    if (id == -1) return "ERROR";
    user.setID(String.valueOf(id));
    setID(String.valueOf(id));
    ID = user.userID();
    request.getSession().setAttribute("userID", ID);
    if (conn.insertBasicInfo(user)) {
      return "SUCCESS";
    }
    else return "ERROR";
  }
}


