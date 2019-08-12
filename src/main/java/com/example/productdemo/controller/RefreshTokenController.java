package com.example.productdemo.controller;

import com.example.productdemo.client.OAuth2PlatformClientFactory;
import com.example.productdemo.entity.po.StoreApiIntuit;
import com.example.productdemo.service.IStoreApiIntuitService;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.data.PlatformResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RefreshTokenController {
    private final OAuth2PlatformClientFactory factory;
    private final IStoreApiIntuitService storeApiIntuitService;

    public RefreshTokenController(OAuth2PlatformClientFactory factory,
                                  IStoreApiIntuitService storeApiIntuitService) {

        this.factory = factory;
        this.storeApiIntuitService = storeApiIntuitService;
    }

    @GetMapping("/refreshToken")
    @ResponseBody
    public String refreshToken() {

        try {
            OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
            StoreApiIntuit storeApiIntuit = storeApiIntuitService.selectBySellerId(12345L);
            String refreshToken = storeApiIntuit.getRefresh_token();

            BearerTokenResponse bearerTokenResponse = client.refreshToken(refreshToken);
            storeApiIntuit.setAccess_token(bearerTokenResponse.getAccessToken());
            storeApiIntuit.setRefresh_token(bearerTokenResponse.getRefreshToken());
            storeApiIntuitService.updateByStoreApiIntuit(storeApiIntuit);

            return storeApiIntuit.toString();
        } catch (Exception ex) {
            return new JSONObject().put("response",ex.getMessage()).toString();
        }
    }

    @GetMapping("/revokeToken")
    @ResponseBody
    public String revokeToken(@RequestParam("id") long id) {

        try {
            OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
            StoreApiIntuit storeApiIntuit = storeApiIntuitService.selectBySellerId(id);
            PlatformResponse response  = client.revokeToken(storeApiIntuit.getRefresh_token());

            storeApiIntuitService.deleteByStoreApiIntuit(storeApiIntuit);

            return response.toString();
        } catch (Exception ex) {
            return new JSONObject().put("response",ex.getMessage()).toString();
        }
    }
}