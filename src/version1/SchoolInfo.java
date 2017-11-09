package version1;

import java.sql.Date;
import java.util.HashSet;

import com.opensymphony.xwork2.ActionSupport;

public class SchoolInfo extends ActionSupport {
   /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private enum schPeriod {
   bachelor, master, doctor
   }

  private schPeriod period;
  private String school;

  public String getSchool() {
    return school;
  }

  public void setPeriod(String period) {
    if (period == "bachelor")
      this.period = schPeriod.bachelor;
    else if (period == "master")
      this.period = schPeriod.master;
    else
      this.period = schPeriod.doctor;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public Date getAdmittionDate() {
    return admittionDate;
  }

  public void setAdmittionDate(Date admittionDate) {
    this.admittionDate = admittionDate;
  }


  public HashSet<NameIdPair> getTeacher() {
    return teacher;
  }

  public void setTeacher(HashSet<NameIdPair> teacher) {
    this.teacher = teacher;
  }

  public HashSet<NameIdPair> getStudent() {
    return student;
  }

  public void setStudent(HashSet<NameIdPair> student) {
    this.student = student;
  }

  public String getPeriod() {
    if (period == schPeriod.bachelor)
      return "bachelor";
    else if (period == schPeriod.master)
      return "master";
    else return "doctor";
  }
  
  
  private Date admittionDate;
  private HashSet<NameIdPair> teacher;
  private HashSet<NameIdPair> student;
  
  public SchoolInfo(String per) {
    setPeriod(per);
    school = "";
    admittionDate = null;
    teacher = new HashSet();
    student = new HashSet();
  }
  
  public SchoolInfo(String per, String school, Date date) {
    setPeriod(per);
    this.school = school;
    admittionDate = date;
    teacher = new HashSet();
    student = new HashSet();
  }
}
