package com.turku.core.model;


//import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;


/**
 * @author yunus.emre.eryazar@gelirler.gov.tr
 * 31.08.2023
 */
@Getter
@Setter
public class Request {
    private String cmd;
    private String token;
    private String callid;
    private JSONObject jp;
   // private HttpServletRequest req;
    private String url;
    private String sessionStr;
    private JSONObject otherField;
    private String clientIp;
}
