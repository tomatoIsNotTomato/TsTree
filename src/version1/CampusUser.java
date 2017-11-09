package version1;

import java.sql.Date;

import com.opensymphony.xwork2.ActionSupport;

public class CampusUser extends ActionSupport {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private String ID;
  private Date birthDay;
  private Boolean sex;
  private String place;
  private String phoneNumber;
  private String job;
  private SchoolInfo bachelorPeriod; // 本科
  private SchoolInfo masterPeriod; // 硕士
  private SchoolInfo doctorPeriod; // 博士
  private static final int IDLENGTH = 5;

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

  public SchoolInfo getBachelorPeriod() {
    return bachelorPeriod;
  }

  public void setBachelorPeriod(SchoolInfo bachelorPeriod) {
    this.bachelorPeriod = bachelorPeriod;
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

  public SchoolInfo getMasterPeriod() {
    return masterPeriod;
  }

  public void setMasterPeriod(SchoolInfo masterPeriod) {
    this.masterPeriod = masterPeriod;
  }

  public SchoolInfo getDoctorPeriod() {
    return doctorPeriod;
  }

  public void setDoctorPeriod(SchoolInfo doctorPeriod) {
    this.doctorPeriod = doctorPeriod;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  // 获取5位用户ID
  public String userID() {
    return String.format("%0" + IDLENGTH + "d", Integer.parseInt(ID) + 1);
  }
  
  public CampusUser() {
    name = null;
    ID = null;
    birthDay = null;
    sex = null;
    place = null;
    phoneNumber = null;
    job = null;
    bachelorPeriod = null;
    masterPeriod = null;
    doctorPeriod = null;
  }
  
  public CampusUser(String name, Date bDate, Boolean sex, String place, String phoneNumber, String job) {
    this.name = name;
    this.ID = null;
    this.birthDay = bDate;
    this.sex = sex;
    this.place = place;
    this.phoneNumber = phoneNumber;
    this.job = job;
    bachelorPeriod = null;
    masterPeriod = null;
    doctorPeriod = null;
  }
}
