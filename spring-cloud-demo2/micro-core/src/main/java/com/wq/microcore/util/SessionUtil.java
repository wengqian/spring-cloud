package com.wq.microcore.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionUtil {
	public static Map<String,JSONObject> sessionMap= new HashMap<String,JSONObject>();
	//获取session
	public static HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
	public static JSONObject getUserInfoFromSession(HttpServletRequest request){
		HttpSession session= request.getSession();
		Object userInfo = session.getAttribute("userInfo");
		if(userInfo==null){
			return null;
		}else{
			JSONObject obj =(JSONObject) userInfo;
			return obj;
		}
	}
	
	public static String getUsercodeFromSession(HttpServletRequest request){
		JSONObject userInfo = SessionUtil.getUserInfoFromSession(request);
		if(userInfo==null){
			return null;
		}
		String usercode = userInfo.getString("usercode");
		return usercode;
	}
	
	public static HttpServletRequest getHttpServletRequest(){
		RequestAttributes rs= RequestContextHolder.getRequestAttributes();
		if(rs==null){
			return null;
		}
		HttpServletRequest baserequest=((ServletRequestAttributes)rs).getRequest();
		return baserequest;
	}
	
	public static HttpSession getSession(){
		HttpServletRequest request = getHttpServletRequest();
		if(request==null){
			return null;
		}
		return request.getSession();
	}
	
	public static JSONObject getUserInfoFromSession(){
		HttpSession session= getSession();
		if(session==null){
			return null;
		}
		Object userInfo = session.getAttribute("userInfo");
		if(userInfo==null){
			return null;
		}else{
			JSONObject obj =(JSONObject) userInfo;
			return obj;
		}
	}
	
	public static String getUsercodeFromSession(){
		JSONObject userInfo = SessionUtil.getUserInfoFromSession();
		if(userInfo==null){
			return null;
		}
		String usercode = userInfo.getString("usercode");
		return usercode;
	}
	
	public static String getUsernameFromSession(){
		JSONObject userInfo = SessionUtil.getUserInfoFromSession();
		if(userInfo==null){
			return null;
		}
		String username = userInfo.getString("username");
		return username;
	}
	
	public static JSONObject getAllDmFromSession(){
		JSONObject userInfo = SessionUtil.getUserInfoFromSession();
		JSONObject obj = new JSONObject();
		obj.put("qxdm", userInfo.get("qxdm"));
		obj.put("pcsdm", userInfo.get("pcsdm"));
		obj.put("sqdm", userInfo.get("sqdm"));
		obj.put("fjdm", userInfo.get("fjdm"));
		obj.put("xzjddm", userInfo.get("xzjddm"));
		obj.put("wgdm", userInfo.get("wgdm"));
		return obj;
	}
	
	public static String getRemoteIP(){
		HttpServletRequest request = getHttpServletRequest();
		if(request==null){
			return null;
		}
		String ip = request.getHeader("X-Forwarded-For");
		if(ip!=null&&!"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
		    int index = ip.indexOf(",");
		    if(index != -1){
		        return ip.substring(0,index);
		    }else{
		        return ip;
	        }
		}
		ip = request.getHeader("X-Real-IP");
		if(ip!=null&&!"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)){
		    return ip;
		}
		return request.getRemoteAddr();
	}
}
