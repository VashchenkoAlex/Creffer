package com.creffer.services.utils;
import com.google.common.net.HttpHeaders;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service("httpHeadersSorter")
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

    public String getAcceptLanguage(){
        return headersMap.get("accept-language");
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

    public void setRequest(HttpServletRequest request) {
        this.request = request;
        getHeadersInfo();
    }
    public String getReferer(){
        String referer = request.getHeader(HttpHeaders.REFERER);
        if (referer==null){
            referer = "Direct";
        }
        return referer;
    }
    public LocalDateTime getClickDate(){
        return LocalDateTime.now();
    }
}
