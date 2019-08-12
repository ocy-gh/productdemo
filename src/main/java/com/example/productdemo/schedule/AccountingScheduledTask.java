package com.example.productdemo.schedule;

import com.example.productdemo.client.OAuth2PlatformClientFactory;
import com.example.productdemo.entity.po.StoreApiIntuit;
import com.example.productdemo.service.IStoreApiIntuitService;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class AccountingScheduledTask {
    private final IStoreApiIntuitService storeApiIntuitService;
    private final OAuth2PlatformClientFactory factory;

    public AccountingScheduledTask(IStoreApiIntuitService storeApiIntuitService, OAuth2PlatformClientFactory factory) {
        this.storeApiIntuitService = storeApiIntuitService;
        this.factory = factory;
    }

    @Scheduled(fixedDelay = 3000000, initialDelay = 15000)
    public void scheduledRefreshToken() {
        StoreApiIntuit storeApiIntuit = storeApiIntuitService.selectBySellerId(12345L);

        ZoneId klZoneId = ZoneId.of("Asia/Kuala_Lumpur");
        ZoneOffset klZoneOffset = klZoneId.getRules().getOffset(LocalDateTime.now(klZoneId));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        long authorizeAtSeconds  = LocalDateTime.parse(storeApiIntuit.getAuthorize_at(), format).toEpochSecond(klZoneOffset);
        long refreshAtSeconds  = LocalDateTime.parse(storeApiIntuit.getRefresh_at(), format).toEpochSecond(klZoneOffset);
        long currentSeconds = LocalDateTime.now(klZoneId).toEpochSecond(klZoneOffset);
        String refreshAt = LocalDateTime.now(ZoneId.of("Asia/Kuala_Lumpur")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        if(currentSeconds - refreshAtSeconds > 5) {

            try {
                OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
                String refreshToken = storeApiIntuit.getRefresh_token();

                BearerTokenResponse bearerTokenResponse = client.refreshToken(refreshToken);
                storeApiIntuit.setAccess_token(bearerTokenResponse.getAccessToken());
                storeApiIntuit.setRefresh_token(bearerTokenResponse.getRefreshToken());
                storeApiIntuit.setRefresh_at(refreshAt);
                storeApiIntuitService.updateByStoreApiIntuit(storeApiIntuit);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // TODO: Remind user of authorization expiration date
        /*
        if(currentSeconds - authorizeAtSeconds > 8600000) {

        }
         */
    }
}