package entity;

/**
 * @author yzy
 * @classname Result
 * @description TODO
 * @create 2019-06-19 16:11
 */
public class Result {

    private Integer code;
    private Boolean flag;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(Integer code, Boolean flag, String message) {

        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public Result(Integer code, Boolean flag, String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result(Boolean flag, Integer code, String message) {

        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
