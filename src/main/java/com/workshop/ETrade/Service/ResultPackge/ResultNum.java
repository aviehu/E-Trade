package com.workshop.ETrade.Service.ResultPackge;

public class ResultNum extends Result{
    private int val;

    public ResultNum(int val, String errMsg){
        super(errMsg);
        this.val = val;
    }

    public int getVal(){
        return this.val;
    }


}
