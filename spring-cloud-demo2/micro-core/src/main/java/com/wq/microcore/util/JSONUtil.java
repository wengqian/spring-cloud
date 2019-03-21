package com.wq.microcore.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * 
 * JSONUtils
 * 
 * @Author cjjcsu
 * Apr 18, 2013 7:21:35 PM
 * 
 * @version 1.0.0
 *
 */
public class JSONUtil {
	private static SerializeConfig mapping = new SerializeConfig();
	private static String dateFormatStr;
	static{
		dateFormatStr = "yyyy-MM-dd HH:mm:ss";
		mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormatStr));
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormatStr));
	}

	/**
	 * 将集合转换为数组
	 * list2JsonArray
	 * @param list
	 * @return JSONArray
	 * @exception 
	 * @since  1.0.0
	 */
	public static JSONArray list2JsonArray(List<JSONObject> list) {
		JSONArray ja = new JSONArray();
		ja.addAll(list);
		return ja;
	}
	
	public static JSONObject key2LowerCase(JSONObject jo){
		if(jo==null){
			return null;
		}
		JSONObject lck = new JSONObject();
		for (String str : jo.keySet()) {
			lck.put(str.toLowerCase(), jo.get(str));
		}
		return lck;
	}
	
	public static Map<String, Object> mapKey2LowerCase(Map<String, Object> jo){
		Map<String, Object> lck = new HashMap<String, Object>();
		for (String str : jo.keySet()) {
			lck.put(str.toLowerCase(), jo.get(str));
		}
		return lck;
	}
	
	public static JSONArray jaKey2LowerCase(JSONArray jo){
		JSONArray lck = new JSONArray();
		for (Object object : jo) {
			Map<String, Object> map = (Map<String, Object>)object;
			JSONObject _jo = new JSONObject(map);
			lck.add(key2LowerCase(_jo));
		}
		return lck;
	}
	
	public static List<JSONObject> listKey2LowerCase(List<JSONObject> list){
		List<JSONObject> lck = new ArrayList<JSONObject>(0);
		for (Object object : list) {
			JSONObject _jo = (JSONObject)object;
			lck.add(key2LowerCase(_jo));
		}
		return lck;
	}
	
	public static List<Object> listKey2LowerCaseObject(List<JSONObject> list){
		List<Object> lck = new ArrayList<Object>(0);
		for (Object object : list) {
			JSONObject _jo = (JSONObject)object;
			lck.add(key2LowerCase(_jo));
		}
		return lck;
	}
	
	public static List<JSONObject> array2JSONList(JSONArray ja){
		List<JSONObject> result = new ArrayList<JSONObject>();
		if(ja==null){
			return result;
		}
		for(int i = 0;i<ja.size();i++){
			Map<String, Object> _obj = (Map<String, Object>)ja.get(i);
			JSONObject _jo = new JSONObject(_obj);
			result.add(_jo);
		}
		return result;
	}
	
	public static List<JSONObject> mapList2JSONList(List list){
		List<JSONObject> result = new ArrayList<JSONObject>();
		if(list==null){
			return result;
		}
		for(Object obj : list){
			JSONObject jsonObj = new JSONObject((Map)obj);
			jsonObj = key2LowerCase(jsonObj);
			result.add(jsonObj);
		}
		return result;
	}
	
	public static JSONObject map2JSON(Map map){
		if(map==null){
			return null;
		}
		JSONObject jsonObj = new JSONObject(map);
		JSONObject lck = key2LowerCase(jsonObj);
		return lck;
	}
	
	public static String toJSONString(Object o){
		return JSONObject.toJSONString(o, mapping);
	}
	
	public static JSONObject JSONRemoveNull(JSONObject obj){
		List<String> removeList = new ArrayList<String>();
		if(obj==null){
			return obj;
		}
		for(String key:obj.keySet()){
			Object value = obj.get(key);
			if(value==null){
				removeList.add(key);
				continue;
			}
			if(value instanceof String){
				if("null".equals(value)){
					removeList.add(key);
				}
			}
		}
		if(removeList.size()>0){
			for(String key:removeList){
				obj.remove(key);
			}
		}
		return obj;
	}
}
