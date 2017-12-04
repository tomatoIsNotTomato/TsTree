package version1;

import java.sql.Date;

import com.opensymphony.xwork2.ActionSupport;

public class CampusUser extends ActionSupport {
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

  public static int getIdlength() {
    return IDLENGTH;
  }

  private SchoolInfo bachelorPeriod; // 本科
  private SchoolInfo masterPeriod; // 硕士
  private SchoolInfo doctorPeriod; // 博士
  private static final int IDLENGTH = 5;

  // 获取5位用户ID
  public String userID() {
    return String.format("%0" + IDLENGTH + "d", Integer.parseInt(ID) );
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


  public SchoolInfo getBachelorPeriod() {
    return bachelorPeriod;
  }

  public void setBachelorPeriod(SchoolInfo bachelorPeriod) {
    this.bachelorPeriod = bachelorPeriod;
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

  public CampusUser(String ID) {
	    firstName = null;
	    this.ID = ID;
	    lastName = null;
	    headline = null;
	    location = null;
	    industry = null;
	    email = null;
	    picture_url = null;
	    profile_url = null;
	  }
  
  public CampusUser() {
    firstName = null;
    ID = null;
    lastName = null;
    headline = null;
    location = null;
    industry = null;
    email = null;
    picture_url = null;
    profile_url = null;
  }
  
  public CampusUser(String firstName, String lastName, String headline, String location, String industry, String email, String picture_url, String profile_url) {
    this.ID = null;
    this.firstName = firstName;
    this.lastName = lastName;
    this.headline = headline;
    this.location = location;
    this.industry = industry;
    this.email = email;
    this.picture_url = picture_url;
    this.profile_url = profile_url;
    bachelorPeriod = null;
    masterPeriod = null;
    doctorPeriod = null;
  }
}
