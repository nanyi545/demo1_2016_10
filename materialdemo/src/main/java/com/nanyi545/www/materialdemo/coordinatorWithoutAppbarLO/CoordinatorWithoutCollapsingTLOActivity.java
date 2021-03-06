package com.nanyi545.www.materialdemo.coordinatorWithoutAppbarLO;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanyi545.www.materialdemo.R;
import com.nanyi545.www.materialdemo.collapse_layout.CollapsHolder;
import com.nanyi545.www.materialdemo.utils.MyRVAdapter;

public class CoordinatorWithoutCollapsingTLOActivity extends AppCompatActivity {

    public static void start(Context c ){
        Intent i=new Intent(c,CoordinatorWithoutCollapsingTLOActivity.class);
        c.startActivity(i);
    }


    RecyclerView mRecyclerView;
    MyRVAdapter mAdapter;


    CollapsHolder.CollapsHolderManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);   // use this to draw below status bar

        setContentView(R.layout.activity_coordinator_without_appbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvToDoList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] myDataset={"item1","item2","item3","item4","item5","item1","item2","item3","item4","item5","item1","item2","item3","item4","item5"};
        mAdapter = new MyRVAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.i("mmm","dy:"+dy);
            }
        });



        manager=CollapsHolder.CollapsHolderManager.getInstance(getWindow().getDecorView().getRootView(),R.id.collapse_holder2,R.id.collapse_holder1);



        CustomHeaderScrollingViewBehavior behavior= (CustomHeaderScrollingViewBehavior) ((CoordinatorLayout.LayoutParams)mRecyclerView.getLayoutParams()).getBehavior();
        behavior.setManager(manager);


    }


}
