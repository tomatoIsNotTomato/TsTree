package version1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUncheckedMsg extends HttpServlet{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)  {
    int count = 0;
    DBcrud conn = new DBcrud();
    String id = CookieCtrl.getCookie(request);//request.getSession().getAttribute("userID").toString(); 
    try {
      count = conn.getMsgCount(Integer.parseInt(id));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // œÏ”¶
    response.setContentType("text/html;charset=UTF-8");
    response.setStatus(200);
    PrintWriter out;
    try {
      
      out = response.getWriter();
      
      out.println(
          count);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}
