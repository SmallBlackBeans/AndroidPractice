package com.example.helloworld.Projects.WangYI.News.View.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.helloworld.Basic.AsynTask.Constant;
import com.example.helloworld.Projects.WangYI.Ad.util.JsonUtil;
import com.example.helloworld.Projects.WangYI.News.Bean.DetailWeb;
import com.example.helloworld.Projects.WangYI.News.Bean.DetailWebImage;
import com.example.helloworld.Projects.WangYI.Services.WYHttpResponse;
import com.example.helloworld.Projects.WangYI.Utils.WYHttpUtil;
import com.example.helloworld.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class WYNewsDetailctivity extends SwipeBackActivity {

    public static final String DOCID = "doc";

    public static int INITSUCCESS = 0;

    String docid;
    String body;

    WebView mWebView;

    MyHandler mHandler;


    int replayCount;
    TextView replayCountTextView;
    LinearLayout share_outer;
    EditText feeback;
    TextView send;
    RelativeLayout parent;
    boolean hasFocus = false;
     ArrayList<DetailWebImage> imgs;

    private SwipeBackLayout mSwipeBackLayout;


    @JavascriptInterface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynews_detailctivity);

        mHandler = new MyHandler(this);
        docid = getIntent().getStringExtra(DOCID);


        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this, "demo");

        replayCountTextView = (TextView) findViewById(R.id.replyCount);
        feeback = (EditText) findViewById(R.id.feedback);
        share_outer = (LinearLayout) findViewById(R.id.share_outer);
        send = (TextView) findViewById(R.id.send);
        parent = (RelativeLayout) findViewById(R.id.parent);

        final Drawable left = getResources().getDrawable(R.drawable.biz_pc_main_tie_icon);
        //setBounds->设置DrawAbleLeft
        left.setBounds(0, 0, 30, 30);
        feeback.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean fouces) {
                hasFocus = fouces;
                if (fouces) {
                    //有焦点
                    feeback.setCompoundDrawables(null, null, null, null);
                    feeback.setHint("");
                    share_outer.setVisibility(View.GONE);
                    send.setVisibility(View.VISIBLE);
                } else {
                    //无焦点
                    feeback.setCompoundDrawables(left, null, null, null);
                    feeback.setHint("写跟帖");
                    share_outer.setVisibility(View.VISIBLE);
                    send.setVisibility(View.GONE);
                }
            }
        });


        WYHttpUtil util = WYHttpUtil.getInstance();

        String url = Constant.getDetailUrl(docid);
        util.getData(url, new WYHttpResponse<String>(String.class) {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onSuccess(String json) {
                try {
                    JSONObject jObj = new JSONObject(json);
                    JSONObject need_js = jObj.optJSONObject(docid);
                    DetailWeb web = JsonUtil.parseJson(need_js.toString(), DetailWeb.class);
                    if (web != null) {
                        body = web.getBody();
                        imgs = web.getImg();
                        for (int i = 0; i < imgs.size(); i++) {
                            String src = imgs.get(i).getSrc();
                            String imageTag = "<img src='" + src + "' onclick=\"show()\"/>";
                            //<!--IMG#0-->
                            String tag = "<!--IMG#" + i + "-->";
                            body = body.replace(tag, imageTag);
                        }
                        //标题
                        String title = "<p><span style='font-size:18px;'><strong>" + web.getTitle() + "</strong></span></p>";
                        //来源时间
                        title = title + "<p><span style='color:#666666;'>" + web.getSource() + "&nbsp&nbsp" + web.getPtime() + "</span></p>";
                        body = title + body;
                        body = "<html><head><style>img{width:100%}</style><script type='text/javascript'>function show(){window.demo.javaShow()} </script></head><body>" + body + "</body></html>";
                        Message message = mHandler.obtainMessage(INITSUCCESS);
                        mHandler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSwipeBackLayout = getSwipeBackLayout();
                mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(hasFocus){
            //有焦点,失去焦点的最关键的做法就是让另外一个控件获取焦点
            mWebView.requestFocus();

        }else{
            finish();
        }
    }

    @JavascriptInterface
    public void javaShow(){
        Intent intent = new Intent();
        intent.setClass(this,WebDetailImageActivity.class);
        intent.putExtra("images",  imgs);
        startActivity(intent);

    }



    public void initWebView() {
        mWebView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
        replayCountTextView.setText(String.valueOf(replayCount));
    }

    static class MyHandler extends Handler {
        WeakReference<WYNewsDetailctivity> mWeakReference;

        public MyHandler(WYNewsDetailctivity activity) {
            mWeakReference = new WeakReference(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            WYNewsDetailctivity activity = mWeakReference.get();
            if (activity != null) {
                activity.initWebView();
                return;
            }


        }
    }
}
