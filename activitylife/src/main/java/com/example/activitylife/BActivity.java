package com.example.activitylife;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.example.activitylife.utils.LogUtil;

/**
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                           O\  =  /O
 * //                        ____/`---'\____
 * //                      .'  \\|     |//  `.
 * //                     /  \\|||  :  |||//  \
 * //                    /  _||||| -:- |||||-  \
 * //                    |   | \\\  -  /// |   |
 * //                    | \_|  ''\---/''  |   |
 * //                    \  .-\__  `-`  ___/-. /
 * //                  ___`. .'  /--.--\  `. . __
 * //               ."" '<  `.___\_<|>_/___.'  >'"".
 * //              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //              \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //                      Buddha Bless, No Bug !
 * /**
 * Created by st on 2028/6/30
 */
public  class BActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        initView();
        Log.e("2","onCreate--");
    }

    private void initView() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //隐藏初始的title就是appname
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /**
         * 返回箭头
         */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.e("2","onStart--");
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.e("2","onResume--");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("2","onRestart--");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("2","onPause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("2","onStop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("2","onDestroy--");
    }
}
