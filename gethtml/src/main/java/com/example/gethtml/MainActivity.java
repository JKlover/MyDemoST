package com.example.gethtml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {
private TextView mHtml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mHtml=findViewById(R.id.tv_html);
        OkGo.<String>get("http://www.meijuck.com/dzza/7324.html")//
                .tag(this)//
                .headers("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.4620.400 QQBrowser/9.7.13014.400")//
//                .params("wd","路西法")//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Response<String> response) {

                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String bb= response.body();

                        System.out.println(bb);
                        Log.e("获取的内容为：",bb);
                       parseHtml(bb);
//                            Log.e("返回的是", bb);

                    }
                });
    }


    private void parseHtml(String result) {
        Document doc= Jsoup.parse(result);
        String bodyHtml=doc.select("article.article-content").html();
        String data=getMyHtml(bodyHtml);
        final String showData=data.replaceAll("<img","<img style='max-width:100%;height:auto;'");//让图片为屏幕做适应除去恶心人的横向滑轮
        mHtml.setText(Html.fromHtml(showData,new HtmlHttpImageGetter(mHtml),null));

    }



    /**
     * 1、先剔除不要的内容
     * 2、把图片路径换成可以显示的路径
     * 3、让图片为屏幕做适应除去恶心人的横向滑轮
     * @param htmltext
     * @return
     */
    private String getMyHtml(String htmltext){
        Document doc = Jsoup.parse(htmltext);
        //1、剔除不要的内容
        doc.select("strong").remove();
        doc.select("div.pagination").remove();
        doc.select("p.article-tags").remove();
        doc.select("p.post-copyright").remove();
        //2、把图片路径换成可以显示的绝对路径
//        Elements pngs = doc.select("img[src]");
//        for (Element element : pngs) {
//            String imgUrl = element.attr("src");
//            if (imgUrl.trim().startsWith("/")) {
//                imgUrl =HTTPHOST + imgUrl;
//                element.attr("src", imgUrl);//替换静态页面的img地址属性再加载
//            }
//        }
//        //让图片为屏幕做适应除去恶心人的横向滑轮
//        Elements elements=doc.getElementsByTag("img");
//        for (Element element : elements) {
////            element.attr("width","100%").attr("height","auto");
//            element.attr("width","auto").attr("height","100%");
//        }
        return doc.toString();
    }

}
