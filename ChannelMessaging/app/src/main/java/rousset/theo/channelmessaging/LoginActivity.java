package rousset.theo.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnDownloadListener {

    private TextView idTextView;
    private TextView mdpTextView;
    private Button validBtn;
    private EditText idEditText;
    private EditText mdpEditText;
    public static final String PREFS_NAME = "MyPrefsFile";
    Response response = new Response();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validBtn = (Button) findViewById(R.id.validBtn);
        validBtn.setOnClickListener(this);
        idEditText = (EditText) findViewById(R.id.idEditText);
        mdpEditText = (EditText) findViewById(R.id.mdpEditText);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == validBtn.getId()) {
            HttpPostHandler http = new HttpPostHandler();
            http.addOnDownloadListener(this);
            String url = "http://www.raphaelbischof.fr/messaging/?function=connect";
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("username", idEditText.getText().toString());
            data.put("password", mdpEditText.getText().toString());
            http.execute(new PostRequest(url, data));
        }
    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        System.out.println(downloadedContent);

        Gson gson = new Gson();
        response = gson.fromJson(downloadedContent, Response.class);

        if(response.getCode() == 200) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("accesstoken", response.getAccesstoken());
        editor.commit();
        Intent intent = new Intent(LoginActivity.this, ChannelListActivity.class);
        startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),"Mauvais identifiant/mot de passe",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDownloadError(String error) {

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rousset.theo.channelmessaging/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rousset.theo.channelmessaging/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
