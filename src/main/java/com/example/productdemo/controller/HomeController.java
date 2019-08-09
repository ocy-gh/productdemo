package com.example.productdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.productdemo.client.OAuth2PlatformClientFactory;
import com.example.productdemo.entity.po.*;
import com.example.productdemo.http.HttpRequest;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final OAuth2PlatformClientFactory factory;

    public HomeController(OAuth2PlatformClientFactory factory) {
        this.factory = factory;
    }

    @GetMapping("/c")
    public View connectToQuickbooks(HttpSession session) {
        System.out.println("inside connectToQuickbooks");
        OAuth2Config oauth2Config = factory.getOAuth2Config();

        String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");

        String csrf = oauth2Config.generateCSRFToken();
        session.setAttribute("csrfToken", csrf);

        try {
            List<Scope> scopes = new ArrayList<>();
            scopes.add(Scope.Accounting);
            return new RedirectView(oauth2Config.prepareUrl(scopes, redirectUri, csrf), true, true, false);
        } catch (com.intuit.oauth2.exception.InvalidRequestException e) {
            System.out.println("Exception calling connectToQuickbooks ");
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/invoice")
    public void createInvoice() {
        CustomerRef customerRef = new CustomerRef();
        customerRef.setValue("1");

        SalesItemLineDetail salesItemLineDetail = new SalesItemLineDetail();
        ItemRef itemRef = new ItemRef();
        itemRef.setName("Services");
        itemRef.setValue("100");
        salesItemLineDetail.setItemRef(itemRef);

        SalesItemLine salesItemLine = new SalesItemLine();
        salesItemLine.setDetailType("SalesItemLineDetail");
        salesItemLine.setSalesItemLineDetail(salesItemLineDetail);
        salesItemLine.setAmount(1000000.00f);

        List<Line> listList = new ArrayList<>();

        Line line = new Line();
        line.setDetailType("SalesItemLineDetail");
        line.setAmount(100.00f);
        line.setSalesItemLineDetail(salesItemLineDetail);
        listList.add(line);

        JSONObject main = new JSONObject();
        main.put("Line",listList);
        main.put("CustomerRef",customerRef);

        System.out.println(main.toString());


        HttpRequest httpRequest = new HttpRequest();
//        httpRequest.doPost("/v3/company/4620816365001314160/invoice?minorversion=38", main);
        httpRequest.doPost("/oauth2/v1/tokens/bearer?grant_type=authorization_code", main);
    }

    @GetMapping("/delete")
    public void deleteInvoice(){
        JSONObject del = new JSONObject();
        del.put("SyncToken", "3");
        del.put("Id","33");

        System.out.println(del.toString());

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.doPost("/v3/company/4620816365001314160/invoice?operation=delete&minorversion=38", del);

    }

    @GetMapping("/void")
    public void voidInvoice(){
        JSONObject v = new JSONObject();
        v.put("SyncToken", "0");
        v.put("Id","129");

        System.out.println(v.toString());

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.doPost("/v3/company/4620816365001314160/invoice?operation=void&minorversion=38", v);

    }
}
