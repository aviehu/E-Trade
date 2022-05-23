package com.workshop.ETrade.Service.ResultPackge;

public class newResult<T> {
    private String errMsg;
    private T val;

    public newResult(T val, String errMsg) {
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
