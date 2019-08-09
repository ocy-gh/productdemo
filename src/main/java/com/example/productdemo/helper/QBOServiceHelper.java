package com.example.productdemo.helper;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.productdemo.client.OAuth2PlatformClientFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.IEntity;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.exception.InvalidTokenException;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Config;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;

@Service
public class QBOServiceHelper {

    private final OAuth2PlatformClientFactory factory;

    public QBOServiceHelper(OAuth2PlatformClientFactory factory) {
        this.factory = factory;
    }

    public DataService getDataService(String realmId, String accessToken) throws FMSException {

        String url = factory.getPropertyValue("IntuitAccountingAPIHost") + "/v3/company";

        Config.setProperty(Config.BASE_URL_QBO, url);
        //create oauth object
        OAuth2Authorizer oauth = new OAuth2Authorizer(accessToken);
        //create context
        Context context = new Context(oauth, ServiceType.QBO, realmId);

        // create dataservice
        return new DataService(context);
    }



    /**
     * Queries data from QuickBooks
     *
     * @param session
     * @param sql
     * @return
     */
    public List<? extends IEntity> queryData(HttpSession session, String sql) {

        String realmId = (String)session.getAttribute("realmId");
        if (StringUtils.isEmpty(realmId)) {
            System.out.println("Relam id is null ");
        }
        String accessToken = (String)session.getAttribute("access_token");

        try {

            //get DataService
            DataService service = getDataService(realmId, accessToken);

            // get data
            QueryResult queryResult = service.executeQuery(sql);
            return queryResult.getEntities();
        }
        /*
         * Handle 401 status code -
         * If a 401 response is received, refresh tokens should be used to get a new access token,
         * and the API call should be tried again.
         */
        catch (InvalidTokenException e) {
            System.out.println("Error while calling executeQuery :: " + e.getMessage());

            //refresh tokens
            System.out.println("received 401 during companyinfo call, refreshing tokens now");
            OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
            String refreshToken = (String)session.getAttribute("refresh_token");

            try {
                BearerTokenResponse bearerTokenResponse = client.refreshToken(refreshToken);
                session.setAttribute("access_token", bearerTokenResponse.getAccessToken());
                session.setAttribute("refresh_token", bearerTokenResponse.getRefreshToken());

                //call company info again using new tokens
                System.out.println("calling companyinfo using new tokens");
                DataService service = getDataService(realmId, accessToken);

                // get data
                QueryResult queryResult = service.executeQuery(sql);
                return queryResult.getEntities();

            } catch (OAuthException e1) {
                System.out.println("Error while calling bearer token :: " + e.getMessage());

            } catch (FMSException e1) {
                System.out.println("Error while calling company currency :: " + e.getMessage());
            }

        } catch (FMSException e) {
            List<Error> list = e.getErrorList();
            list.forEach(error -> System.out.println("Error while calling executeQuery :: " + error.getMessage()));
        }
        return null;
    }
}