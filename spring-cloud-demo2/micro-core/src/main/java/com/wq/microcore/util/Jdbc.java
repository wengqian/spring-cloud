package com.wq.microcore.util;

import java.sql.*;

public class Jdbc {

	public static void main(String[] args) throws Exception {

		Class.forName("oracle.jdbc.OracleDriver");
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.118.128.184:1521:GAZHK2", "basepolice", "gpsadmin");

		PreparedStatement pst = connection.prepareStatement("select t.* from case t ");

		ResultSet resultSet = pst.executeQuery();

//		while(resultSet.next()){
//			System.out.println(resultSet.getString("a_ajbh"));
//		}
		
		
		ResultSetMetaData rsmd = resultSet.getMetaData();
		
		int cc = rsmd.getColumnCount();

		
		
		String tpl1 ="<if test=\"%s != null\">"
		 +" #{%s,jdbcType=%s},"
        +"</if>";
		
		
		String tpl2 ="<if test=\"%s != null\">"
			 +" %s,"
	        +"</if>";
		
		for (int i = 0; i < cc; i++) {
			String name = rsmd.getColumnName(i+1);
			String type = rsmd.getColumnTypeName(i+1);
			if("NUMBER".equals(type)){
				type = "INTEGER";
			}
			if("VARCHAR2".equals(type)){
				type = "VARCHAR";
			}
			if("DATE".equals(type)){
				type = "DATE";
			}
//			System.out.print(name.toLowerCase() + ",");
			System.out.print("\""+name.toLowerCase() + "\",");
			
//			System.out.println(String.format(tpl1, new Object[]{name.toLowerCase(),name.toLowerCase(),type}));
			
			
//			System.out.println(String.format(tpl2, new Object[]{name.toLowerCase(),name.toLowerCase()}));
		}
		
	}

}
