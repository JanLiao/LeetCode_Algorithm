package com.jan;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    public static void main(String[] args){
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
        map.put("4", "2");
        for(String s : map.keySet()){
            String v = map.get(s);
            String ss = "32";
            //map.put(s, ss);
            //map.put("22", "2");
        }
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        m.put("1", "2");
        m.put("2", "2");
        m.put("3", "2");
        m.put("4", "2");
        for(String s : m.keySet()){
            m.put("22", "2");
        }
    }
}
