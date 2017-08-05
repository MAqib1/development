package com.company;

/**
 * Created by Khubaib ch on 7/22/2017.
 */
public class ViewReportTable {
    String invoice,amount,date;

    public ViewReportTable(String invoice, String amount, String date) {
        this.invoice = invoice;
        this.amount = amount;
        this.date = date;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
