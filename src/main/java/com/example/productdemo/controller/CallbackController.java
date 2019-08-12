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

/**
 * @author dderose
 *
 */
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
     *
     * @param state
     * @param realmId
     * @param session
     * @return
     */
    @RequestMapping("/oauth2redirect")
    public String callBackFromOAuth(@RequestParam("code") String authCode,
                                    @RequestParam("state") String state,
                                    @RequestParam(value = "realmId", required = false) String realmId,
                                    HttpSession session,
                                    Model model) {

        System.out.println("inside oauth2redirect of sample"  );
        try {

            // TODO mybatis insert token here chang session to Credential Object
            String csrfToken = (String) session.getAttribute("csrfToken");
            if (csrfToken.equals(state)) {
                session.setAttribute("realmId", realmId);
                session.setAttribute("auth_code", authCode);

                OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
                String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");
                System.out.println("inside oauth2redirect of sample -- redirectUri " + redirectUri  );

                BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUri);

                session.setAttribute("access_token", bearerTokenResponse.getAccessToken());
                session.setAttribute("refresh_token", bearerTokenResponse.getRefreshToken());

                StoreApiIntuit storeApiIntuit = new StoreApiIntuit();
                storeApiIntuit.setRealmId(realmId);
                storeApiIntuit.setAuthCode(authCode);
                storeApiIntuit.setAccess_token(bearerTokenResponse.getAccessToken());
                storeApiIntuit.setRefresh_token(bearerTokenResponse.getRefreshToken());
                storeApiIntuitService.insert(storeApiIntuit);

                // Update your Data store here with user's AccessToken and RefreshToken along with the realmId
                model.addAttribute("realmId", realmId);
                model.addAttribute("auth_code", authCode);
                model.addAttribute("access_token", bearerTokenResponse.getAccessToken());
                model.addAttribute("refresh_token", bearerTokenResponse.getRefreshToken());
                return "connected";
            }
            System.out.println("csrf token mismatch " );
        } catch (OAuthException e) {
            System.out.println("Exception in callback handler ");
            e.printStackTrace();
        }
        return null;
    }


}