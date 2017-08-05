package com.company;

/**
 * Created by Khubaib ch on 7/22/2017.
 */
public class ViewBillsTable {
    private String sn,particulars,qty,batchNo,date,mrp,rate,amount;

    public ViewBillsTable(String sn, String particulars, String qty, String batchNo, String date, String mrp, String amount,String invoicedate) {
        this.sn = sn;
        this.particulars = particulars;
        this.qty = qty;
        this.batchNo = batchNo;
        this.date = date;
        this.mrp = mrp;
        this.amount = amount;
        this.rate=rate;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
