package com.example.helloworld.Basic.Network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class AsycHttpClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyc_http_client);
    }



    public void uploadClick(View v) throws Exception {
        AsyncHttpClient client = new AsyncHttpClient();
        String urlPath = "http:127.0.0.1:8090/upload/upload";
        RequestParams params = new RequestParams();
        params.put("fName","xxx.text");
        File file =  new File(getFilesDir(),"xxx.png");
        params.put("file",file);
        client.post(urlPath, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });
    }
}
