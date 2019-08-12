package com.example.productdemo.controller;

import com.example.productdemo.client.OAuth2PlatformClientFactory;
import com.example.productdemo.entity.po.StoreApiIntuit;
import com.example.productdemo.service.IStoreApiIntuitService;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class CallbackController {

    private final OAuth2PlatformClientFactory factory;
    private final IStoreApiIntuitService storeApiIntuitService;

    public CallbackController(OAuth2PlatformClientFactory factory, IStoreApiIntuitService storeApiIntuitService) {
        this.factory = factory;
        this.storeApiIntuitService = storeApiIntuitService;
    }

    /**
     *  This is the redirect handler you configure in your app on developer.intuit.com
     *  The Authorization code has a short lifetime.
     *  Hence Unless a user action is quick and mandatory, proceed to exchange the Authorization Code for
     *  BearerToken
     */
    @RequestMapping("/oauth2redirect")
    public String callBackFromOAuth(@RequestParam("code") String authCode,
                                    @RequestParam("state") String state,
                                    @RequestParam(value = "realmId", required = false) String realmId,
                                    HttpSession session,
                                    Model model) {

        try {
            String csrfToken = (String) session.getAttribute("csrfToken");

            if (csrfToken.equals(state)) {
                OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
                String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");
                System.out.println("inside oauth2redirect of sample -- redirectUri " + redirectUri  );

                BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUri);

                StoreApiIntuit storeApiIntuit = new StoreApiIntuit();
                storeApiIntuit.setSeller_id(12345L);
                storeApiIntuit.setRealm_id(realmId);
                storeApiIntuit.setAuth_code(authCode);
                storeApiIntuit.setAccess_token(bearerTokenResponse.getAccessToken());
                storeApiIntuit.setRefresh_token(bearerTokenResponse.getRefreshToken());
                storeApiIntuit.setExpires_in(bearerTokenResponse.getExpiresIn());
                storeApiIntuit.setX_refresh_token_expires_in(bearerTokenResponse.getXRefreshTokenExpiresIn());
                String connectAt = LocalDateTime.now(ZoneId.of("Asia/Kuala_Lumpur")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                storeApiIntuit.setAuthorize_at(connectAt);
                storeApiIntuit.setRefresh_at(connectAt);
                storeApiIntuitService.insert(storeApiIntuit);

                // Update your Data store here with user's AccessToken and RefreshToken along with the realmId
                model.addAttribute("realmId", realmId);
                model.addAttribute("auth_code", authCode);
                model.addAttribute("access_token", bearerTokenResponse.getAccessToken());
                model.addAttribute("refresh_token", bearerTokenResponse.getRefreshToken());
                return "connected";
            }

        } catch (OAuthException e) {
            e.printStackTrace();
        }

        return null;
    }
}