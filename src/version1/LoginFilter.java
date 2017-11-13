package version1;

import java.io.IOException;  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
public class LoginFilter extends HttpServlet implements Filter {  
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    public void destroy() {  
    }  
  
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse,        
            FilterChain filterChain) throws IOException, ServletException{  
          
        HttpServletRequest request = (HttpServletRequest) sRequest;        
        HttpServletResponse response = (HttpServletResponse) sResponse;        
        HttpSession session = request.getSession();        
        String url=request.getServletPath();    
        String contextPath=request.getContextPath();    
        if(url.equals("")) url+="/";    

        if((url.startsWith("/"))&&!((url.startsWith("/userLogin"))||(url.startsWith("/login"))||(url.startsWith("/css"))||(url.startsWith("/register")))){//�����ʺ�̨��Դ ���˵�login    
             String user=(String)session.getAttribute("userID");    
             if(user==null){//ת�����Ա��½ҳ��    
                  response.sendRedirect(contextPath+"/userLogin.jsp");   
                  return;    
             }    
        }    
          filterChain.doFilter(sRequest, sResponse);      
    }    
  
    public void init(FilterConfig arg0) throws ServletException {  
  
    }  
}  