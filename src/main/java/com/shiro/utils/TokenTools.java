package com.shiro.utils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Token的工具类  
 *  
 */  
public class TokenTools {  
      
    /**  
     * 生成token放入session  
     * @param request  
     * @param tokenServerkey  
     */  
    public static void createToken(HttpServletRequest request,String tokenServerkey){  
        String token = TokenProccessor.getInstance().makeToken();  
        request.getSession().setAttribute(tokenServerkey, token);  
    }  
      
    /**  
     * 移除token  
     * @param request  
     * @param tokenServerkey  
     */  
    public static void removeToken(HttpServletRequest request,String tokenServerkey){  
        request.getSession().removeAttribute(tokenServerkey);  
    }  
      
    /**  
     * 判断请求参数中的token是否和session中一致  
     * @param request  
     * @param tokenClientkey  
     * @param tokenServerkey  
     * @return  
     */  
    public static boolean judgeTokenIsEqual(HttpServletRequest request,String tokenClientkey,String tokenServerkey){  
        String token_client = request.getParameter(tokenClientkey);  
        if(StringUtils.isEmpty(token_client)){  
            return false;  
        }  
        String token_server = (String) request.getSession().getAttribute(tokenServerkey);  
        if(StringUtils.isEmpty(token_server)){  
            return false;  
        }  
          
        if(!token_server.equals(token_client)){  
            return false;  
        }
        return true;  
    }


    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 取出cookies
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }
}  