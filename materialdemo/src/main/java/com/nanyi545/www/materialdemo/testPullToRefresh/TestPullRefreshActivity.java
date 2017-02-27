package com.nanyi545.www.materialdemo.testPullToRefresh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nanyi545.www.materialdemo.R;

public class TestPullRefreshActivity extends AppCompatActivity {


    public static void start(Context c){
        Intent i=new Intent(c,TestPullRefreshActivity.class);
        c.startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pull_refresh);
    }


}
