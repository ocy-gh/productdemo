package com.example.productdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.productdemo.client.OAuth2PlatformClientFactory;
import com.example.productdemo.entity.po.*;
import com.example.productdemo.helper.InvoiceHelper;
import com.example.productdemo.http.HttpRequest;
import com.example.productdemo.service.IStoreApiIntuitService;
import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final OAuth2PlatformClientFactory factory;
    private final IStoreApiIntuitService storeApiIntuitService;

    public HomeController(OAuth2PlatformClientFactory factory,
                          IStoreApiIntuitService storeApiIntuitService) {

        this.factory = factory;
        this.storeApiIntuitService = storeApiIntuitService;
    }

    @GetMapping("/c")
    public View connectToQuickbooks(HttpSession session) {
        System.out.println("inside connectToQuickbooks");
        OAuth2Config oauth2Config = factory.getOAuth2Config();

        String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");

        storeApiIntuitService.deleteByStoreApiIntuit(storeApiIntuitService.selectBySellerId(12345L));
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

    @GetMapping("/create_invoice")
    public String createInvoice() {
        Invoice savedInvoice = null;
        StoreApiIntuit storeApiIntuit = storeApiIntuitService.selectBySellerId(12345);
        IAuthorizer oauth = new OAuth2Authorizer(storeApiIntuit.getAccess_token());

        try {
            Context context = new Context(oauth, ServiceType.QBO, storeApiIntuit.getRealm_id());
            DataService service = new DataService(context);

            // add invoice
            Invoice invoice = InvoiceHelper.getInvoiceFields(service);
            savedInvoice = service.add(invoice);
            System.out.println("Invoice created: " + savedInvoice.getId() + " :: invoice doc num: " + savedInvoice.getDocNumber());
            Invoice deletedInvoice = service.delete(savedInvoice);
            System.out.println("Invoice deleted : " + deletedInvoice.getId() + " status ::: " + deletedInvoice.getStatus());
//            Invoice voidedInvoice = service.voidRequest(savedInvoice);
//            System.out.println("Invoice voided : " + voidedInvoice.getId() + " status ::: " + voidedInvoice.getPrivateNote());
        } catch (FMSException e) {
            List<Error> list = e.getErrorList();
            list.forEach(error -> System.out.println("Error :: " + error.getMessage()));
        }
        return "connected";
    }

//    @GetMapping("/delete_inv")
//    public String delete_invoice() {
//
//        StoreApiIntuit storeApiIntuit = storeApiIntuitService.selectBySellerId(12345);
//        IAuthorizer oauth = new OAuth2Authorizer(storeApiIntuit.getAccess_token());
//
//        try {
//            Context context = new Context(oauth, ServiceType.QBO, storeApiIntuit.getRealm_id());
//            DataService service = new DataService(context);
//
//            // create invoice
//            Invoice invoice = InvoiceHelper.getInvoiceFields(service);
//            Invoice addInvoice = service.add(invoice);
//            System.out.println("Invoice added : " + addInvoice.getId());
//            // delete invoice
//            Invoice deletedInvoice = service.delete(addInvoice);
//            System.out.println("Invoice deleted : " + deletedInvoice.getId() + " status ::: " + deletedInvoice.getStatus());
//        } catch (FMSException e) {
//            List<Error> list = e.getErrorList();
//            list.forEach(error -> System.out.println("Error while deleting entity :: " + error.getMessage()));
//        }
//        return "connected";
//    }

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