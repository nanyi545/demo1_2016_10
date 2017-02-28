package com.nanyi545.www.materialdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nanyi545.www.materialdemo.behaviour2.BehaviourActivity2;
import com.nanyi545.www.materialdemo.testPullToRefresh.TestPullRefreshActivity;
import com.nanyi545.www.materialdemo.testPullToRefresh.TestPullRefreshActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpBehaviour1(View v){
        BehaviourActivity1.start(this);
    }

    public void jumpBehaviour2(View v){
        BehaviourActivity2.start(this);
    }

    public void jumpPullToRefresh(View v){
        TestPullRefreshActivity.start(this);
    }

    public void jumpPullToRefresh2(View v){
        TestPullRefreshActivity2.start(this);
    }

}
