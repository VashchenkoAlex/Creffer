package com.creffer.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HTTPHeadersSorter {
    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };
    private Map<String,String> headersMap;

    private HttpServletRequest request;

    public HTTPHeadersSorter(HttpServletRequest request) {
        this.request = request;
        getHeadersInfo();
        printMap();
    }

    public String getClientIpAddress(){
        for (String header: IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if(ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)){
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
    public String getUserAgent(){
        return headersMap.get("user-agent");
    }

    private void getHeadersInfo(){
        headersMap = new HashMap<>();
        Enumeration headersNames = request.getHeaderNames();
        while (headersNames.hasMoreElements()){
            String key = (String) headersNames.nextElement();
            String value = request.getHeader(key);
            headersMap.put(key,value);
        }
    }
    private void printMap(){
        for (Map.Entry<String,String> pair:headersMap.entrySet()) {
            System.out.println(pair.getKey()+" : "+pair.getValue());
            System.out.println();
        }
    }
}
