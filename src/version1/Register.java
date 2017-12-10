package version1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.UUID;

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
  private String country;
  private String province;
  private String city;
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  public String getProvince() {
    return province;
  }
  public void setProvince(String province) {
    this.province = province;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  private String industry;
  private String email;
  private String picture_url;
  private String profile_url;
  private String pwd;
  private String img;

  
  public String getImg() {
    return img;
  }
  public void setImg(String img) {
    this.img = img;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
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

  private String generateUUId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
  
  public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    int ext = conn.checkExist(email, "aa");
    if (ext>=0) {
      return "EMAIL";
    }
    else if (ext==-2) {
      return "ERROR";
    };
    
    HttpServletRequest request = ServletActionContext.getRequest();
    String projectPath = "http://tomato.applinzi.com/";/*request.getSession().getServletContext().getRealPath("/");
    projectPath = projectPath.replace('\\', '/');*/
      String imgPath = projectPath+"userImage/"; 
      System.out.println(imgPath);
      String picture_url = imgPath+getEmail().replace('@', '_').replace('.', '_')+".jpg";
      try { 
        if (img!=null) {
          InputStream is = new FileInputStream(img);
            File destFile = new File(picture_url);
             if (!destFile.getParentFile().exists()) {  
                    destFile.getParentFile().mkdirs();  
            }  
             
            OutputStream os = new FileOutputStream(destFile);
            
            byte[] buffer = new byte[2048];
            int length = 0;
            while((length = is.read(buffer))!=-1) {
              
              os.write(buffer, 0, length);
            }
            is.close();
            os.flush(); 
            os.close();
        }
      }
      catch(IOException e) {
        e.printStackTrace();
      return "error";
      }
      
    location = city+", "+province+", "+country;
    CampusUser user = new CampusUser(getFirstName(), getLastName(), getHeadline(), location, getIndustry(), getEmail(), picture_url, getProfile_url());
   
    int id = conn.saveCode(getEmail(), getPwd(), "pwd");
    if (id == -1) return "ERROR";
    user.setID(String.valueOf(id));
    setID(String.valueOf(id));
    ID = user.userID();
    String uuid = generateUUId();
    if (conn.insertBasicInfo(user)) {
      if (conn.updateUUid(email, uuid)){
        EmailCtrl.sendAccountActivateEmail(email, uuid);
        return "SUCCESS";
      }
    }
    
    return "ERROR";
    
    
    
    /*Cookie cookie = CookieCtrl.addCookie(String.format("%0" + 5 + "d", id), request);
    HttpServletResponse response = ServletActionContext.getResponse();
    response.addCookie(cookie);*/
   /* request.getSession().setAttribute("userID", ID);*/
    
   
  }
}


