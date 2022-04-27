package Service.ResultPackge;

public class ResultBool extends Result {
    private boolean flag;

    public ResultBool(boolean flag, String errMsg){
        super(errMsg);
        this.flag = flag;
    }

    public boolean getVal(){
        return this.flag;
    }

}
