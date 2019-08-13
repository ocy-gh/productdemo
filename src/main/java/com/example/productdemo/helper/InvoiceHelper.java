package com.example.productdemo.helper;

import com.intuit.ipp.data.*;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author dderose
 */
public final class InvoiceHelper {

    private InvoiceHelper() { }

    public static Invoice getInvoiceFields(DataService service) throws FMSException {
        Invoice invoice = new Invoice();

        ReferenceType referenceType = new ReferenceType();
        referenceType.setValue("1");
        referenceType.setName("CustomerRef");

        SalesItemLineDetail salesItemLineDetail = new SalesItemLineDetail();
        Item item = ItemHelper.getItem(service);
        salesItemLineDetail.setItemRef(ItemHelper.getItemRef(item));

        List<Line> listList = new ArrayList<>();
        Line line = new Line();
        line.setDetailType(LineDetailTypeEnum.SALES_ITEM_LINE_DETAIL);
        line.setAmount(BigDecimal.valueOf(100));
        line.setSalesItemLineDetail(salesItemLineDetail);
        listList.add(line);

        invoice.setCustomerRef(referenceType);
        invoice.setLine(listList);

        return invoice;
    }

}