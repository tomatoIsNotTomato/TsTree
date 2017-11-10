package version1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;

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
  private Boolean sex;
  private String place;
  private String phoneNumber;
  private String job;
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
  public Boolean getSex() {
    return sex;
  }
  public void setSex(Boolean sex) {
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
    
    
    CampusUser user = new CampusUser(getName(),getBirthDay(),getSex(), getPlace(),getJob(),getPhoneNumber());
    
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
    if (conn.insertBasicInfo(user)) return SUCCESS;
    else return "ERROR";
  }
}


