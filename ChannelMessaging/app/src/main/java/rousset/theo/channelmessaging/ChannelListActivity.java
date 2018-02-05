package rousset.theo.channelmessaging;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.HashMap;

/**
 * Created by roustheo on 29/01/2018.
 */
public class ChannelListActivity extends AppCompatActivity implements View.OnClickListener, OnDownloadListener {

    private ListView listChannel;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        listChannel = (ListView)findViewById(R.id.listChannel);
        String url = "http://www.raphaelbischof.fr/messaging/?function=connect";
        HashMap<String, String> data = new HashMap<String, String>();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        data.put("accesstoken",settings.getString("AccessToken",null));
        HttpPostHandler channelCharging = new HttpPostHandler();
        channelCharging.addOnDownloadListener(this);
        PostRequest pr = new PostRequest(url,data);
        String response = null;
        channelCharging.execute(pr);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDownloadComplete(String downloadedContent) {

    }

    @Override
    public void onDownloadError(String error) {

    }
}
