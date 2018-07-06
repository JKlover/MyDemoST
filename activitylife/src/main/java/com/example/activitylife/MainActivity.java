package com.example.activitylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.activitylife.utils.LogUtil;

public class MainActivity extends AppCompatActivity {
    private Button mBtnJump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.e("1","onCreate--");
    }

    private void initView() {
        mBtnJump=findViewById(R.id.bt_jump);
        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        Log.e("1","onStart--");
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.e("1","onResume--");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("1","onRestart--");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("1","onPause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("1","onStop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("1","onDestroy--");
    }

}
