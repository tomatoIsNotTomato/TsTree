/**
 * 
 */
package version1;

import java.sql.Date;
import java.util.HashSet;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dell
 *
 */
@SuppressWarnings("serial")
public class update_t_s extends ActionSupport{
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
  int ID;
  String preName;
  String t_or_s;
  CampusUser user;
  int id;
  String tel_old;
  String tel_new;
  private Date birthDay;
  private Boolean sex;
  private String place;
  private String phoneNumber;
  private String job;
  private String name;
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
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public HashSet<NameIdPair> getBteacher() {
    return bteacher;
  }
  public void setBteacher(HashSet<NameIdPair> bteacher) {
    this.bteacher = bteacher;
  }
  public HashSet<NameIdPair> getBstudent() {
    return bstudent;
  }
  public void setBstudent(HashSet<NameIdPair> bstudent) {
    this.bstudent = bstudent;
  }
  public HashSet<NameIdPair> getMteacher() {
    return mteacher;
  }
  public void setMteacher(HashSet<NameIdPair> mteacher) {
    this.mteacher = mteacher;
  }
  public HashSet<NameIdPair> getMstudent() {
    return mstudent;
  }
  public void setMstudent(HashSet<NameIdPair> mstudent) {
    this.mstudent = mstudent;
  }
  public HashSet<NameIdPair> getDteacher() {
    return dteacher;
  }
  public void setDteacher(HashSet<NameIdPair> dteacher) {
    this.dteacher = dteacher;
  }
  public HashSet<NameIdPair> getDstudent() {
    return dstudent;
  }
  public void setDstudent(HashSet<NameIdPair> dstudent) {
    this.dstudent = dstudent;
  }
  private HashSet<NameIdPair> bteacher;
  private HashSet<NameIdPair> bstudent;
  private HashSet<NameIdPair> mteacher;
  private HashSet<NameIdPair> mstudent;
  private HashSet<NameIdPair> dteacher;
  private HashSet<NameIdPair> dstudent;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
 
  public String getTel_old() {
    return tel_old;
  }
  public void setTel_old(String tel_old) {
    this.tel_old = tel_old;
  }
  public String getTel_new() {
    return tel_new;
  }
  public void setTel_new(String tel_new) {
    this.tel_new = tel_new;
  }
  public CampusUser getUser() {
    return user;
  }
  public void setUser(CampusUser user) {
    this.user = user;
  }
  public String getT_or_s() {
    return t_or_s;
  }
  public void setT_or_s(String t_or_s) {
    this.t_or_s = t_or_s;
  }
  public String getPreName() {
    return preName;
  }
  public void setPreName(String preName) {
    this.preName = preName;
  }
  public String getPostName() {
    return postName;
  }
  public void setPostName(String postName) {
    this.postName = postName;
  }
  String period;
  String postName;
  public String execute() throws Exception{
    try {
        DBcrud conn = new DBcrud();
        conn.update_t_s_info(ID, id,preName, postName, period, t_or_s,tel_old,tel_new);
        user=conn.userInfo(ID);
        
        name=user.getName();
        birthDay=user.getBirthDay();
        sex=user.getSex();
        place=user.getPlace();
        phoneNumber=user.getPhoneNumber();
        System.out.println(phoneNumber);
        job=user.getJob();
        bteacher=user.getBachelorPeriod().getTeacher();
        
        bstudent=user.getBachelorPeriod().getStudent();

        mteacher=user.getMasterPeriod().getTeacher();
        mstudent=user.getMasterPeriod().getStudent();
        dteacher=user.getDoctorPeriod().getTeacher();
        dstudent=user.getDoctorPeriod().getStudent();
    }catch (Exception e) {
      return "error";
    }
      return SUCCESS;
  }
}
