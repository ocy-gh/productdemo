package com.example.productdemo.client;

import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;

import java.util.ArrayList;
import java.util.List;

public class OAuth2PlatformClientFactory {
    //Prepare the config
    OAuth2Config oauth2Config = new OAuth2Config.OAuth2ConfigBuilder("clientId", "clientSecret")
            .callDiscoveryAPI(Environment.SANDBOX).buildConfig();

    //Generate the CSRF token
    String csrf = oauth2Config.generateCSRFToken();

    //Prepare scopes
    List<Scope> scopes = new ArrayList<Scope>();
    scopes.add(Scope.Accounting); // add as needed

    //Get the authorization URL
    String url = oauth2Config.prepareUrl(scopes, redirectUri, csrf); //redirectUri - pass the callback url

}
