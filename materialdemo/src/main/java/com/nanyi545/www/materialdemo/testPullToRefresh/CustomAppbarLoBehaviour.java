package com.nanyi545.www.materialdemo.testPullToRefresh;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/3/1.
 */
public class CustomAppbarLoBehaviour extends AppBarLayout.Behavior {


    public CustomAppbarLoBehaviour() {
    }

    public CustomAppbarLoBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        boolean ret=super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
        Log.i("aaa","extended AppBarLayout.Behavior---- onStartNestedScroll  default ret:"+ret+"  directTargetChild:"+directTargetChild.getClass().getName()+"  target:"+target.getClass().getName());
        return ret;
    }


}


