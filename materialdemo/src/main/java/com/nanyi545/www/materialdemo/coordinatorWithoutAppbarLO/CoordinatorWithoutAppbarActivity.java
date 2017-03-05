package com.nanyi545.www.materialdemo.coordinatorWithoutAppbarLO;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.nanyi545.www.materialdemo.R;
import com.nanyi545.www.materialdemo.utils.MyRVAdapter;

public class CoordinatorWithoutAppbarActivity extends AppCompatActivity {

    public static void start(Context c ){
        Intent i=new Intent(c,CoordinatorWithoutAppbarActivity.class);
        c.startActivity(i);
    }


    RecyclerView mRecyclerView;
    MyRVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_without_appbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvToDoList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] myDataset={"item1","item2","item3","item4","item5","item1","item2","item3","item4","item5","item1","item2","item3","item4","item5"};
        mAdapter = new MyRVAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }


}
