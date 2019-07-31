package com.example.productdemo.controller;

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
        httpRequest.doPost("/v3/company/4620816365001314160/invoice?minorversion=38", main);



    }
}
