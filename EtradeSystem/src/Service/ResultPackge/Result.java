package Service.ResultPackge;

public abstract class Result {
    private String errMsg;

    public Result(String errMsg){
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

}
