package rousset.theo.channelmessaging;

/**
 * Created by roustheo on 29/01/2018.
 */
public class Response {
    private String response;
    private int code;
    private String accesstoken;

    Response() {

    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public int getCode() {
        return code;
    }
}
