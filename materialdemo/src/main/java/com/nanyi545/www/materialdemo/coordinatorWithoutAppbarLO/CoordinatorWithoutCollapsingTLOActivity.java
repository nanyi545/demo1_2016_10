package com.nanyi545.www.materialdemo.coordinatorWithoutAppbarLO;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
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
import com.nanyi545.www.materialdemo.utils.MyRVAdapter;

public class CoordinatorWithoutCollapsingTLOActivity extends AppCompatActivity {

    public static void start(Context c ){
        Intent i=new Intent(c,CoordinatorWithoutCollapsingTLOActivity.class);
        c.startActivity(i);
    }


    RecyclerView mRecyclerView;
    MyRVAdapter mAdapter;



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


//        final TextView title= (TextView) findViewById(R.id.tv_title);
//        final TextView subtitle= (TextView) findViewById(R.id.tv_subtitle);
//
//        AppBarLayout appBarLayout= (AppBarLayout) findViewById(R.id.app_bar);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                Log.i("mmm","verticalOffset:"+verticalOffset);
//
////                title.setScaleY( ( -58-verticalOffset+0f)/(-58)  );
////                subtitle.setScaleY( ( -58-verticalOffset+0f)/(-58)  );
//
////                if (subtitle.getHeight()!=0){
////                    subtitle.setHeight(0);
////                    appBarLayout.invalidate();
////                }
//                if (subtitle.getHeight()!=(39+verticalOffset)){
//                    subtitle.setHeight(39+verticalOffset);
//                    appBarLayout.invalidate();
//                }
//
//            }
//        });





    }


}
