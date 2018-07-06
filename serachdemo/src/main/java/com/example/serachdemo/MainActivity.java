package com.example.serachdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {
private SearchView svCus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getDataFromNet();

    }

    private void initView() {
        svCus = (SearchView) findViewById(R.id.sv_cus);
        svCus.setIconified(false);
        svCus.onActionViewExpanded();


        if (svCus != null) {


            try {        //--拿到字节码
                Class<?> argClass = svCus.getClass();
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                //--暴力反射,只有暴力反射才能拿到私有属性
                ownField.setAccessible(true);
                View mView = (View) ownField.get(svCus);
                //--设置背景
                mView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }


            //获取ImageView的id
            int imgId = svCus.getContext().getResources().getIdentifier("android:id/search_mag_icon",null,null);
            //获取ImageView
            ImageView searchButton = (ImageView)svCus.findViewById(imgId);
             //设置图片
            searchButton.setImageResource(R.mipmap.search);
            //不使用默认
            svCus.setIconifiedByDefault(false);


            //获取到TextView的ID
            int id = svCus.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
           //获取到TextView的控件
            TextView textView = (TextView) svCus.findViewById(id);
           //设置字体大小为14sp
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);//14sp
           //设置提示字体颜色
            textView.setTextColor(this.getResources().getColor(R.color.gray_color));
          //设置输入文字颜色
            textView.setHintTextColor(this.getResources().getColor(R.color.black_overlay));
        }
        svCus.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("A","我被激活");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("B","我被激活");
                return false;
            }
        });
    }

    private void getDataFromNet() {
//        OkGo.<String>post("http://www.go1977.com/index.php?m=vod-search")//
//                .tag(this)//
////                .headers("Referer", "http://www.go1977.com/")
//                .params("wd","路西法")//
////                .params("passwd", MD5Utils.md5Password(userpasswd))//
//                .execute(new StringDialogCallback(this) {
//
//                    @Override
//                    public void onError(Response response) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Response response) {
//                        try {
//                            String bb= URLDecoder.decode(String.valueOf(response.body()),"utf-8");
////                            Log.e("返回的是", bb);
//                            System.out.println(bb);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
               OkGo.<String>get("http://www.go1977.com/index.php?m=vod-search")//
                .tag(this)//
                .headers("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.4620.400 QQBrowser/9.7.13014.400")//
                .params("wd","路西法")//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Response<String> response) {

                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String bb= response.body();

                            System.out.println(bb);
//                            Log.e("返回的是", bb);

                    }
                });


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                // 设置cookie和post上面的map数据
//                try {
//                    Connection con2 = Jsoup.connect("http://www.go1977.com/index.php?m=vod-search");
//                    con2.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
//                    con2.data("wd","路西法");
//                    Connection.Response response = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST).execute();
//                    System.out.println(response.body());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                // 打印，登陆成功后的信息
//                // parse the document from response
//
//            }
//        }).start();
    }
}
