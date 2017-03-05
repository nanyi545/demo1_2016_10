package com.nanyi545.www.materialdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nanyi545.www.materialdemo.behaviour2.BehaviourActivity2;
import com.nanyi545.www.materialdemo.coordinatorWithoutAppbarLO.CoordinatorWithoutAppbarActivity;
import com.nanyi545.www.materialdemo.nestedScroll.TestNestedScrollActivity;
import com.nanyi545.www.materialdemo.nestedScroll.TestNestedScrollActivity2;
import com.nanyi545.www.materialdemo.nestedScroll.no_coordinator_test.testWithCostumView.NoCoorNestedScrollTestActivity;
import com.nanyi545.www.materialdemo.nestedScroll.no_coordinator_test.NoCoordinatorNestedScrollTestActivity;
import com.nanyi545.www.materialdemo.nestedScroll.use_nested_scrollview.TestNestedScrollActivity3;
import com.nanyi545.www.materialdemo.quickRet.QuickRetActivity;
import com.nanyi545.www.materialdemo.testPullToRefresh.TestPullRefreshActivity;
import com.nanyi545.www.materialdemo.testPullToRefresh.TestPullRefreshActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        delay.sendEmptyMessageDelayed(0,1000);
    }


    /**
     *   use the framework ....
     */
    Handler delay=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Button btn1= (Button) findViewById(R.id.btn1);
            ViewCompat.offsetLeftAndRight(btn1,60);
            Button btn2= (Button) findViewById(R.id.btn2);
            ViewCompat.offsetLeftAndRight(btn2,120);
        }
    };




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

    public void jumpNestedScroll(View v){
        TestNestedScrollActivity.start(this);
    }
    public void jumpNestedScroll2(View v){
        TestNestedScrollActivity2.start(this);
    }

    public void jumpQuickRet(View v){
        QuickRetActivity.start(this);
    }

    public void jumpNestedScroll3(View v){
        TestNestedScrollActivity3.start(this);
    }

    public void jumpNestedScrollNoCoordinator(View v){
        NoCoordinatorNestedScrollTestActivity.start(this);
    }

    public void jumpNestedScrollNoCoordinator2(View v){
        NoCoorNestedScrollTestActivity.start(this);
    }

    public void jumpCoordinatorNoAppbar(View v){
        CoordinatorWithoutAppbarActivity.start(this);
    }


}
