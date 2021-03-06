package com.example.productdemo.controller;

import com.example.productdemo.client.OAuth2PlatformClientFactory;
import com.example.productdemo.helper.QBOServiceHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.exception.InvalidTokenException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author dderose
 *
 */
@Controller
public class QBOController {

    private final OAuth2PlatformClientFactory factory;
    private final QBOServiceHelper helper;
    private static final String failureMsg="Failed";

    public QBOController(OAuth2PlatformClientFactory factory, QBOServiceHelper helper) {
        this.factory = factory;
        this.helper = helper;
    }

    /**
     * Sample QBO API call using OAuth2 tokens
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCompanyInfo")
    public String callQBOCompanyInfo(HttpSession session) {

        String realmId = (String)session.getAttribute("realmId");
        if (StringUtils.isEmpty(realmId)) {
            return new JSONObject().put("response","No realm ID.  QBO calls only work if the accounting scope was passed!").toString();
        }
        String accessToken = (String)session.getAttribute("access_token");

        try {


            //get DataService
            DataService service = helper.getDataService(realmId, accessToken);

            // get all companyinfo
            String sql = "select * from companyinfo";
            QueryResult queryResult = service.executeQuery(sql);
            return processResponse(failureMsg, queryResult);

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
                DataService service = helper.getDataService(realmId, accessToken);

                // get all companyinfo
                String sql = "select * from companyinfo";
                QueryResult queryResult = service.executeQuery(sql);
                return processResponse(failureMsg, queryResult);

            } catch (OAuthException e1) {
                System.out.println("Error while calling bearer token :: " + e.getMessage());
                return new JSONObject().put("response",failureMsg).toString();
            } catch (FMSException e1) {
                System.out.println("Error while calling company currency :: " + e.getMessage());
                return new JSONObject().put("response",failureMsg).toString();
            }

        } catch (FMSException e) {
            List<Error> list = e.getErrorList();
            list.forEach(error -> System.out.println("Error while calling executeQuery :: " + error.getMessage()));
            return new JSONObject().put("response",failureMsg).toString();
        }

    }

    private String processResponse(String failureMsg, QueryResult queryResult) {
        if (!queryResult.getEntities().isEmpty() && queryResult.getEntities().size() > 0) {
            CompanyInfo companyInfo = (CompanyInfo) queryResult.getEntities().get(0);
            System.out.println("Companyinfo -> CompanyName: " + companyInfo.getCompanyName());
            ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonInString = mapper.writeValueAsString(companyInfo);
                return jsonInString;
            } catch (JsonProcessingException e) {
                System.out.println("Exception while getting company info ");
                e.printStackTrace();
                return new JSONObject().put("response",failureMsg).toString();
            }

        }
        return failureMsg;
    }




}