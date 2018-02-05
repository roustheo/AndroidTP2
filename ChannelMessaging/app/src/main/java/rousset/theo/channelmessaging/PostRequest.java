package rousset.theo.channelmessaging;

import java.util.HashMap;

/**
 * Created by roustheo on 19/01/2018.
 */
public class PostRequest {
    public PostRequest(String URL, HashMap<String, String> params) {
        this.URL = URL;
        this.params = params;
    }

    private String URL;
    private HashMap<String, String> params;

    public String getURL() {
        return URL;
    }

    public HashMap<String, String> getParams() {
        return params;
    }
}
