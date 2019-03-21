package com.wq.microcore.util;

import java.util.HashMap;
import java.util.Map;

public class TableFieldUtil {
	public static Map<String,String[]> ORACLE_FIELD = new HashMap<String,String[]>();
	private static final String BUILDING_SOLR_FIELDS[] = { "id", "buildingcode", "state", "lz", "ly", "lx", "xzjd", "qx", "pronvince", "xqbzw", "standardaddress",
		"xzjddm", "cjwh", "pcsdm", "qxdm", "sqdm", "lylx", "city", "state_mark", "NOIKADDR", "country", "cz", "jlx", "jlxbc1", "mph", "xqbzwbc1", "wordlen", "ADMINCODE",
		"ishistory","operatetime","barcode","anoaddr","supplyaddr","spcaddr","noik_search","smart_search","zdry","isbzdz",
		"pcsmc","fjdm","fjmc","addtime","addusername","addusercode","fangchatime","wgdm","dah","hh","isjwab","isjwabnb","isjwabwb","jwabwbqy","iszgd","isjwabyx","isjwabhxq","iszdzgd","isybzgd","zgdxx","bzdz","jwbzdz","jth"};
	
	private static final String ROOM_SOLR_FIELDS[] = { "id", "roomname", "usage", "state", "location", "floor", "unite", "floorshowname", "uniteshowname",
		"buildingcode", "roomcode","qxdm","fjdm","pcsdm","xzjddm","sqdm","searchname","standardaddress","fangchatime","wgdm","dah","hh","barcode"};
	
	private static final String RESIDENTINFO_SOLR_FIELDS[] = { "id", "name", "sex", "idnumber", "buildingcode", "type","roomcode","roomname","unitename","addtime","unitename_origin","roomname_origin","sjly","qxdm","xzjddm",
		"sqdm","fjdm","pcsdm","state","juzhuhere","dengjihere","djdz","czrktype","wgdm","addtimedate","addusercode","addusername","isbdgk","wld","controllevel"};
	
	private static final String DEPARTMENT_SOLR_FIELDS[] = { "id", "qxdm","pcsdm","xzjddm","sqdm","fjdm","roomcode","buildingcode","company_info_id","company_name","business_license_no","address","law_peron","company_type",
		"jylx","is_zhuce","is_fire","is_trinity","has_uygur","skdw","szqy","tzhy","is_fhabzd","hasjgdm","is_hgswxg","wgdm","addtime","addusercode","addusername","wld","has_uygur_set","has_often_uygur_people","has_hire_more_uygur","is_albpxjg","fzgysljhddd","dmxbgdw","controllevel"};
	
	private static final String POSITION_SOLR_FIELDS[] = { "id", "qxdm","pcsdm","xzjddm","sqdm","fjdm","roomcode","buildingcode","name","address","lb","wgdm"};
	
	private static final String CASE_SOLR_FIELDS[] = { "a_ajbh", "qxdm","pcsdm","xzjddm","sqdm","fjdm","roomcode","buildingcode","id","kf","a_ajzt","a_ajlb1","a_ajlb2","ajmc","wgdm","a_slsj"};
	
	private static final String CYRY_SOLR_FIELDS[] = {"id","qxdm", "pcsdm","xzjddm","sqdm","fjdm","roomcode","buildingcode","wgdm","companyname","companyid","sfzh","cyryxm","xb","age","prsj","jpsj","bq","ssrk","standardaddress","jglb","addtime","addusercode","addusername","is_delete"};
	/**
	 * 判断是否在字段中
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isField(String name, String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if (name.equals(fields[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isBuildingSolrField(String name){
		return isField(name,BUILDING_SOLR_FIELDS);
	}
	public static boolean isDepartmentSolrField(String name){
		return isField(name,DEPARTMENT_SOLR_FIELDS);
	}
	public static boolean isPositionSolrField(String name){
		return isField(name,POSITION_SOLR_FIELDS);
	}
	public static boolean isResidentinfoSolrField(String name){
		return isField(name,RESIDENTINFO_SOLR_FIELDS);
	}
	public static boolean isRoomSolrField(String name){
		return isField(name,ROOM_SOLR_FIELDS);
	}
	public static boolean isCaseSolrField(String name){
		return isField(name,CASE_SOLR_FIELDS);
	}
	public static boolean isCyrySolrField(String name){
		return isField(name,CYRY_SOLR_FIELDS);
	}
}
