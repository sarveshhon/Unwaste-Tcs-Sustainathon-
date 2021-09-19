package com.teamunwaste.unwaste.Home.ExchangeFM;

public class ExchangeModel {

    String exchangeId;
    String exchangeTitle;
    String exchangeImage;
    String exchangeDescription;
    String exchangeType;
    String exchangeFee;
    String userName;
    String userEmail;
    String userPhone;
    String userAddress;
    String postDate;

    public ExchangeModel() {
    }

    public ExchangeModel(String exchangeId, String exchangeTitle, String exchangeImage, String exchangeDescription, String exchangeType, String exchangeFee, String userName, String userEmail, String userPhone, String userAddress, String postDate) {
        this.exchangeId = exchangeId;
        this.exchangeTitle = exchangeTitle;
        this.exchangeImage = exchangeImage;
        this.exchangeDescription = exchangeDescription;
        this.exchangeType = exchangeType;
        this.exchangeFee = exchangeFee;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.postDate = postDate;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public String getExchangeTitle() {
        return exchangeTitle;
    }

    public String getExchangeImage() {
        return exchangeImage;
    }

    public String getExchangeDescription() {
        return exchangeDescription;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public String getExchangeFee() {
        return exchangeFee;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getPostDate() {
        return postDate;
    }
}
