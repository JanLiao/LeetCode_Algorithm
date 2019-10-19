package com.jan.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* @author jan
* @data 2019年10月10日 上午10:54:11
*/
public class JDBCUtilOracle1 {
	
	public Connection getConn() {
		String driver = Consts.sql_driver;
		String url = Consts.sql_url;
		String username = Consts.username;
		String password = Consts.password;
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}

	public void readyInsert(int type_id, String cls, String cls_tag, String subtype_name, String subtype_tag, int status_id){
		String sql = "insert into ecg_subtype (type_id, subtype_name, subtype_tag, subtype_desc, status_id) values (" + type_id
				+ ", '" + subtype_name + "', '" + subtype_tag + "', '" + subtype_name + "', " + status_id + ")";
		try {
			PreparedStatement ps = getConn().prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String jsonStr){
		Map<String, Integer> map = new HashMap<>();
		map.put("心律失常", 1);
		map.put("房颤", 2);
		map.put("室颤", 3);
		map.put("基准点标识", 4);
		map.put("心肌缺血与心肌梗死", 5);
		JSONObject obj = JSON.parseObject(jsonStr);
		String type = obj.getString("annotation_type");
		System.out.println(type);
		JSONArray arr = obj.getJSONArray("annotation_type");
		String sql = "insert into ecg_subtype (type_id, subtype_name, subtype_tag, subtype_desc, status_id) values (?, ?, ?, ?, ?)";
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
			String cls = arr.getJSONObject(i).getString("cls");
			String cls_tag = arr.getJSONObject(i).getString("cls_tag");
			String cls_type = arr.getJSONObject(i).getString("cls_type");
//			System.out.println(cls_type);
			JSONArray ar = arr.getJSONObject(i).getJSONArray("cls_type");
			try {
				Connection conn = getConn();
				PreparedStatement ps = conn.prepareStatement(sql);
				for (int j = 0; j < ar.size(); j++) {
//				System.out.println(ar.getJSONObject(j));
					JSONObject oo = ar.getJSONObject(j);
					for(String s : oo.keySet()){
//						readyInsert(map.get(cls), cls, cls_tag, s, oo.getString(s), 0);
						ps.setInt(1, map.get(cls));
						ps.setString(2, s);
						ps.setString(3, oo.getString(s));
						ps.setString(4, s);
						ps.setInt(5, 0);
						ps.addBatch();
					}
				}
				ps.executeBatch();
//				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int query1() {
	    Connection conn = getConn();
	    String sql = "SELECT * from tj2_hkzyy_1218.\"OP_MEDICAL_DATA_V\"";
	    PreparedStatement pstmt;
	    int num = 0;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	        	num++;
	        }
	        System.out.println("total = " + num);
	        System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return num;
	}

	public String readJSONTxt(String path) throws IOException {
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String result = "";
		String line = br.readLine();
		while(line != null){
			if(line != null){
				result = result + line;
			}
//			System.out.println(line);
			line = br.readLine();
		}
		br.close();
		fr.close();

		return result;
	}

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\CVTE\\Documents\\WeChat Files\\janliao79\\FileStorage\\File\\2019-10\\annotation_type(4).json";
		JDBCUtilOracle1 juo = new JDBCUtilOracle1();
		String json = juo.readJSONTxt(path);
		System.out.println(json);
		//juo.query1();
		//juo.insert(json);
		ThreadPoolExecutor e = new ThreadPoolExecutor(60, 80, 10, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy());
		e.getActiveCount();
	}

}
