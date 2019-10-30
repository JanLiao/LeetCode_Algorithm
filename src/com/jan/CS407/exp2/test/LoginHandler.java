package com.jan.CS407.exp2.test;

/**
 * @author jan
 * @data 2019骞�10鏈�9鏃� 涓嬪崍7:05:54
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jan.CS407.exp2.JDBCUtil;
import com.jan.CS407.exp2.SessionUtil;
import com.jan.CS407.exp2.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Method: " + httpExchange.getRequestMethod());
        Headers reqHeaders = httpExchange.getRequestHeaders();
        List<String> cookies = reqHeaders.get("Cookie");
        if(cookies != null) {
            System.out.println(cookies.size());
            System.out.println(cookies.get(0));
        }

        InputStream is = httpExchange.getRequestBody();
        String body = is2string(is);
        String[] str = body.split("&");
        System.out.println(str[0]);
        System.out.println("body: " + body);
        is.close();
        
        JSONObject obj = JSON.parseObject(body);
        String userName = obj.getString("user_name");
        String password = obj.getString("password");
        User user = new JDBCUtil().queryByNameAndPwd(userName, password);
        //User user = new User();
        String respStr = "success";
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", "application/json; charset=utf8");
        // 璁剧疆璺ㄥ煙璁块棶
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        headers.set("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
        if(user != null) {
        	String uuid = UUID.randomUUID().toString();
        	ConcurrentHashMap<String, Map<String, Object>> session = SessionUtil.getSession();
        	long ct = System.currentTimeMillis();
        	Map<String, Object> map = new HashMap<>();
        	map.put("user", user);
        	map.put("create_time", ct);
        	session.put(uuid, map);
        	
            List<String> list = new ArrayList<>();
            String cookieStr = "http_session_id=" + uuid;
            System.out.println("uuid = " + cookieStr);
            list.add(cookieStr);
            // 璁剧疆娴忚鍣╟ookie
            headers.put("Set-Cookie", list);
            //User u = new User();
            //u.setPassword("212");
            //u.setUserName("22");
            respStr = "{\"status\":0,\"msg\":\"ok\"}";
            //respStr = JSON.toJSONString(user);
            httpExchange.sendResponseHeaders(200, respStr.length());
        }else {
        	respStr = "{\"status\":400,\"msg\":\"failed\"}";
        	httpExchange.sendResponseHeaders(200, respStr.length());
        }
        
        //System.out.println(respStr);

        OutputStream os = httpExchange.getResponseBody();
        
        os.write(respStr.getBytes());
        os.close();
        httpExchange.close();
    }

    private String is2string(InputStream is) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}



