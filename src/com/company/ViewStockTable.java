package com.company;

/**
 * Created by Khubaib ch on 7/21/2017.
 */
public class ViewStockTable {
    private String particulars;
    private String qty,batchNo,mrp,rate;
    private String date;

    public ViewStockTable(String particulars, String qty, String batchNo, String mrp, String rate, String date) {
        this.particulars = particulars;
        this.qty = qty;
        this.batchNo = batchNo;
        this.mrp = mrp;
        this.rate = rate;
        this.date = date;
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

    public void setQty(String quantity) {
        this.qty = qty;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
