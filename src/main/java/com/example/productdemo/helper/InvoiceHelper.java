package com.example.productdemo.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.example.productdemo.entity.po.CustomerRef;
import com.intuit.ipp.data.*;
import com.intuit.oauth2.data.Address;
import org.apache.commons.lang.RandomStringUtils;

import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.DateUtils;

/**
 * @author dderose
 *
 */
public final class InvoiceHelper {

    private InvoiceHelper() {

    }

    public static Invoice getInvoiceFields(DataService service) throws FMSException, ParseException {
        Invoice invoice = new Invoice();
        /*


        // Mandatory Fields
        invoice.setDocNumber(RandomStringUtils.randomAlphanumeric(5));

        try {
            invoice.setTxnDate(DateUtils.getCurrentDateTime());
        } catch (ParseException e) {
            throw new FMSException("ParseException while getting current date.");
        }

        Customer customer = CustomerHelper.getCustomer(service);
        CustomerRef customerRef = new CustomerRef();
        customerRef.setValue("");
        invoice.setCustomerRef("Shopee Malaysia");

        invoice.setPrivateNote("Testing");
        invoice.setTxnStatus("Payable");
        invoice.setBalance(new BigDecimal("10000"));

        PhysicalAddress billingAdd = new PhysicalAddress();
        billingAdd.setLine1("123 Main St");
        billingAdd.setCity("Mountain View");
        billingAdd.setCountry("United States");
        billingAdd.setCountrySubDivisionCode("CA");
        billingAdd.setPostalCode("94043");

        invoice.setBillAddr(billingAdd);

        List<Line> invLine = new ArrayList<Line>();
        Line line = new Line();
        line.setDescription("New test (14.48mm)\nGiftBox: Red Heart Gift Box [ £ 3.00]");
        line.setAmount(new BigDecimal("10000"));
        line.setDetailType(LineDetailTypeEnum.SALES_ITEM_LINE_DETAIL);


        SalesItemLineDetail silDetails = new SalesItemLineDetail();

        Address adress = new Address();

        Item item = ItemHelper.getItem(service);
        silDetails.setItemRef(ItemHelper.getItemRef(item));

        line.setSalesItemLineDetail(silDetails);
        invLine.add(line);
        invoice.setLine(invLine);

        invoice.setRemitToRef(CustomerHelper.getCustomerRef(customer));

        invoice.setPrintStatus(PrintStatusEnum.NEED_TO_PRINT);
        invoice.setTotalAmt(new BigDecimal("10000"));
        invoice.setFinanceCharge(false);
*/
        return invoice;
    }
}