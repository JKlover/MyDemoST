package com.example.agentwebview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agentwebview.utils.HtmlUtils;
import com.just.agentweb.AgentWeb;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleTextView;
    private AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout mLinearLayout = findViewById(R.id.container);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
//        mToolbar.setTitle(mBean.getTitle() );
        mTitleTextView = findViewById(R.id.toolbar_title);
        this.setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
        getDetailsData("https://baidu.5g500.com/sexdm/guifufanwaipiansibuqu/");
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator(-1, 3)
                .createAgentWeb()//
                .ready()
                .go("http://www.baidu.com/");//这个在调用系统的webview后会被覆盖

//	    Log.e("dsdsd",HtmlUtils.getHtml(showData));
    }

    private void getDetailsData(String url) {
        OkGo.<String>get(url)//
                .tag(this)//
                .headers("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.4620.400 QQBrowser/9.7.13014.400")//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Response<String> response) {

                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        parseHtml(response.body());
                    }

                });
    }

    private void parseHtml(String body) {
        Document doc= Jsoup.parse(body);
        String bodyHtml=doc.select(".main.sec.clearfix").html();
        Log.e("A这是什么....",bodyHtml);
        String playHtml=doc.select(".playlist.xfplay.sec").html();
        Log.e("B这是什么....",playHtml);
//        String data=getMyHtml(bodyHtml);
        String getData=bodyHtml.replaceAll("<img","<img style='max-width:100%;height:auto;'");//让图片为屏幕做适应除去恶心人的横向滑轮
        String showData=getData.replaceAll("<div class=\"video\"","<div class=\"video\" style='height:auto;'");//改变视频的窗口属性适应手机屏幕
        String newHtml=getData+playHtml;
//		String showHtml=showData.replaceAll("<a  href","<a  href=''");//改变视频的窗口属性适应手机屏幕
        mAgentWeb.getWebCreator().getWebView().loadDataWithBaseURL(null, HtmlUtils.getHtml(newHtml), HtmlUtils.getMimeType(), HtmlUtils.getCoding(), null);
    }


}
