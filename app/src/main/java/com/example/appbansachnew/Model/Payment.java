package com.example.appbansachnew.Model;

public class Payment {
    private int paymentId;
    private String paymentUserName;
    private String paymentName;
    private String paymentSDT;
    private String paymentAddress;
    private String datePayment;
    private int paymentTotalBook;
    private int paymentTotalMoney;
private String listBook;
    public Payment() {
    }

    public Payment(int paymentId, String paymentUserName, String paymentName, String paymentSDT, String paymentAddress, String datePayment, int paymentTotalBook, int paymentTotalMoney, String listBook) {
        this.paymentId = paymentId;
        this.paymentUserName = paymentUserName;
        this.paymentName = paymentName;
        this.paymentSDT = paymentSDT;
        this.paymentAddress = paymentAddress;
        this.datePayment = datePayment;
        this.paymentTotalBook = paymentTotalBook;
        this.paymentTotalMoney = paymentTotalMoney;
        this.listBook = listBook;
    }

    public String getListBook() {
        return listBook;
    }

    public void setListBook(String listBook) {
        this.listBook = listBook;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentUserName() {
        return paymentUserName;
    }

    public void setPaymentUserName(String paymentUserName) {
        this.paymentUserName = paymentUserName;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentSDT() {
        return paymentSDT;
    }

    public void setPaymentSDT(String paymentSDT) {
        this.paymentSDT = paymentSDT;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public int getPaymentTotalBook() {
        return paymentTotalBook;
    }

    public void setPaymentTotalBook(int paymentTotalBook) {
        this.paymentTotalBook = paymentTotalBook;
    }

    public int getPaymentTotalMoney() {
        return paymentTotalMoney;
    }

    public void setPaymentTotalMoney(int paymentTotalMoney) {
        this.paymentTotalMoney = paymentTotalMoney;
    }
}
