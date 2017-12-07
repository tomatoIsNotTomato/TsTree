package version1;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class CookieCtrl { 
    public static final String USER_COOKIE = "userId";  
  
    // 添加一个cookie  
    public static Cookie addCookie(String id) {  
        Cookie cookie = new Cookie(USER_COOKIE, id);  
        System.out.println("添加cookie");  
        cookie.setMaxAge(60 * 60 * 24);// cookie保存两周  
        return cookie;  
    } 
  
    // 得到cookie  
    public static String getCookie(HttpServletRequest request) {  
        Cookie[] cookies = request.getCookies();  
        System.out.println("cookies: " + cookies);  
        if (cookies != null) {  
            for (Cookie cookie : cookies) {  
                System.out.println("cookie: " + cookie.getName());  
                if (USER_COOKIE.equals(cookie.getName())) {  
                    String value = cookie.getValue();  
                    if (StringUtils.isNotBlank(value)) {  
                       return value;
                    }
                }  
            }  
        }  
        return null;
    }  
  
    // 删除cookie  
    public static Cookie delCookie(HttpServletRequest request) {  
        Cookie[] cookies = request.getCookies();  
        if (cookies != null) {  
            for (Cookie cookie : cookies) {  
                if (USER_COOKIE.equals(cookie.getName())) {  
                    cookie.setValue("");  
                    cookie.setMaxAge(0);  
                    return cookie;  
                }  
            }  
        }  
        return null;  
    }  
  }
