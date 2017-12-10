package version1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;



public class Active extends HttpServlet{
  
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)  {
    String uuid = request.getParameter("checkcode");
    DBcrud conn = new DBcrud();
    try {
    int ID;
    
      ID = conn.active(uuid);
   
    if (ID>0) {
      String id = String.format("%0" + 5 + "d", ID);
      Cookie cookie = CookieCtrl.addCookie(id, request);;
      response.addCookie(cookie);
      response.sendRedirect("addPeriodInfor.jsp");
    }
    else {
      response.sendRedirect("error.jsp");
    }
  }
   catch (Exception e) {
    
    e.printStackTrace();
  }
  }
}
    


