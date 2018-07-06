package com.example.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logindemo.model.UserInfo;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText passwd;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        name=findViewById(R.id.et_name);
        passwd=findViewById(R.id.et_passwd);
        login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        String username=name.getText().toString();
        String userpasswd=passwd.getText().toString();
        UserInfo info=new UserInfo();
        info.setId(1);
        info.setName("李四");
        info.setPwd("1234567890");
        Gson gson = new Gson();
        // 简单的bean转为json
        String bean2str = gson.toJson(info);
//        OkGo.<String>get("http://192.168.1.102:8080/DengluServlet")//
//        OkGo.<String>post("http://192.168.1.103:8080/FileUploadServlet")//
//                .tag(this)//
////                .headers("header1", "headerValue1")//
//                .params("userInfo", bean2str)//
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
//                            String bb=URLDecoder.decode(String.valueOf(response.body()),"utf-8");
//                            Log.e("返回的是", bb);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
        OkGo.<String>post("http://www.go1977.com/index.php?m=vod-search")//
                .tag(this)//
                .headers("Referer", "http://www.go1977.com/")
                .params("wd","%E8%B7%AF%E8%A5%BF%E6%B3%95")//
                .params("submit","search")//
//                .params("passwd", MD5Utils.md5Password(userpasswd))//
                .execute(new StringDialogCallback(this) {

                    @Override
                    public void onError(Response response) {

                    }

                    @Override
                    public void onSuccess(Response response) {
                        try {
                            String bb= URLDecoder.decode(String.valueOf(response.body()),"utf-8");
//                            Log.e("返回的是", bb);
                            System.out.println(bb);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                    }
                });
        /**
         * 可行
         */
//        RequestParams params = new RequestParams("http://192.168.1.102:8080/DengluServlet");
////        params.setMultipart(true);
//        params.addBodyParameter("name", username);
//        params.addBodyParameter("pwd", userpasswd);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//
//            @Override
//            public void onSuccess(String result) {
//                Log.e("返回的是",result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });

    }
}
