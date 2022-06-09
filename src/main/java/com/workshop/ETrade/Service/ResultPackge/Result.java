package com.workshop.ETrade.Service.ResultPackge;

public class Result<T> {
    private String errMsg;
    private T val;
    
    public Result(T val, String errMsg) {
        this.val = val;
        this.errMsg = errMsg;
    }

    public boolean isSuccess(){
        if(this.errMsg == null){
            return true;
        }
        return false;
    }

    public String getErr(){
        return this.errMsg;
    }

    public T getVal() {
        return val;
    }
}
