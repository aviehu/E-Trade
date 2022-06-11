package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class OpenStoreFunction extends Function<Boolean>{
    private String founderName;
    private String storeName;
    private int card;

    public OpenStoreFunction() {
    }

    public OpenStoreFunction(String founderName, String storeName, int card) {
        this.founderName = founderName;
        this.storeName = storeName;
        this.card = card;
    }

    public String getFounderName() {
        return founderName;
    }

    public void setFounderName(String founderName) {
        this.founderName = founderName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    @Override
    public Result<Boolean> execute() {
        return this.service.openStore(founderName, storeName, card);
    }
}
