package com.example.helloworld.Basic.Parse;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XmlParseActivity extends AppCompatActivity {


    @BindView(R.id.tv_weather)
    TextView mTvWeather;
    @BindView(R.id.btn_get)
    Button mBtnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parse);
        ButterKnife.bind(this);
    }


    public void parseXmlClick(View v) throws Exception {
        String result = "";

        //AssetManager管理器
        AssetManager assets = getAssets();
        //"file:///android_asset/test.html"
        InputStream fis = assets.open("weather.xml");

        XmlPullParser pullParser = Xml.newPullParser();
        //开始解析文档
        pullParser.setInput(fis, "utf-8");
        int eventType = pullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && pullParser.getName().equals("string")) {
                result += pullParser.nextText() + "\n";
            }
            eventType = pullParser.next();
        }

        mTvWeather.setText(result);

    }
}
