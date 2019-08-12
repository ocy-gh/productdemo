package com.example.productdemo.controller;

import javax.servlet.http.HttpSession;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.productdemo.client.OAuth2PlatformClientFactory;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;

/**
 * @author dderose
 *
 */
@Controller
public class RefreshTokenController {

    @Autowired
    OAuth2PlatformClientFactory factory;

    /**
     * Call to refresh tokens
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/refreshToken")
    public String refreshToken(HttpSession session) {

        String failureMsg="Failed";

        try {

            OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
            String refreshToken = (String)session.getAttribute("refresh_token");
            BearerTokenResponse bearerTokenResponse = client.refreshToken(refreshToken);
            session.setAttribute("access_token", bearerTokenResponse.getAccessToken());
            session.setAttribute("refresh_token", bearerTokenResponse.getRefreshToken());
            String jsonString = new JSONObject()
                    .put("access_token", bearerTokenResponse.getAccessToken())
                    .put("refresh_token", bearerTokenResponse.getRefreshToken()).toString();
            return jsonString;
        }
        catch (Exception ex) {
            System.out.println("Exception while calling refreshToken " + ex.getMessage());
            return new JSONObject().put("response",failureMsg).toString();
        }

    }

}