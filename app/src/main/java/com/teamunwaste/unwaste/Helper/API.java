package com.teamunwaste.unwaste.Helper;

public interface API {

    public String PRODUCTS_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/products";
    public String PRODUCT_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/product";
    public String EXCHANGE_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/exchange";
    public String SELL_PRODUCT_POST_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/postToSellProduct";
    public String SELL_PRODUCT_HISTORY_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/userSellHistory";
    public String EXCHANGE_HISTORY_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/userExchangeHistory";
    public String REMOVE_SELL_PRODUCT_HISTORY_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/removeSellProduct";
    public String REMOVE_EXCHANGE_POST_HISTORY_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/removeExchangePost";
    public String EXCHANGE_POST_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/postToExchange";
    public String SLIDER_API = "https://webhooks.mongodb-realm.com/api/client/v2.0/app/unapp-ntnli/service/unwaste/incoming_webhook/slider";

}
