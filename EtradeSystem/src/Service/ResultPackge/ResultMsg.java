package Service.ResultPackge;

public class ResultMsg extends Result{
    private String msg;

    public ResultMsg(String msg, String errMsg){
        super(errMsg);
        this.msg = msg;
    }

    public String getVal(){
        return this.msg;
    }


}
