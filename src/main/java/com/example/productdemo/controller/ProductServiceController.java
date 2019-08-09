package com.example.productdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.productdemo.entity.po.*;
import com.example.productdemo.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductServiceController {

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
