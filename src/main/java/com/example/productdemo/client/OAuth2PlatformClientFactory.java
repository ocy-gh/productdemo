package com.example.productdemo.client;

import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@PropertySource(value="classpath:/application.properties", ignoreResourceNotFound=true)
public class OAuth2PlatformClientFactory {
    private final org.springframework.core.env.Environment env;
    private OAuth2PlatformClient client;
    private OAuth2Config oauth2Config;

    public OAuth2PlatformClientFactory(org.springframework.core.env.Environment env) {
        this.env = env;
    }

    // intitialize a single thread executor, this will ensure only one thread processes the queue
    @PostConstruct
    public void init() {
        //set client id, secret
        oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(env.getProperty("OAuth2AppClientId"), env.getProperty("OAuth2AppClientSecret"))
                // call discovery API to populate urls
                .callDiscoveryAPI(Environment.SANDBOX)
                .buildConfig();
        client  = new OAuth2PlatformClient(oauth2Config);
    }

    public OAuth2PlatformClient getOAuth2PlatformClient()  {
        return client;
    }

    public OAuth2Config getOAuth2Config()  {
        return oauth2Config;
    }

    public String getPropertyValue(String propertyName) {
        return env.getProperty(propertyName);
    }
}