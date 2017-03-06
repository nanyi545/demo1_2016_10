package com.nanyi545.www.materialdemo.collapse_layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nanyi545.www.materialdemo.R;

public class TestCollapseLayoutActivity extends AppCompatActivity {

    public static void start(Context c){
        Intent i=new Intent(c,TestCollapseLayoutActivity.class);
        c.startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_collapse_layout);
    }
}
