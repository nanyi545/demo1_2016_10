package com.nanyi545.www.materialdemo.testPullToRefresh;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
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


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        Log.i("bbb","----nested scroll::::"+dy+"  child:"+child.getClass().getName()+"   target:"+target.getClass().getName());  //
        if((dy<0)&&(target instanceof RecyclerView)&&(((RecyclerView) target).computeVerticalScrollOffset()==0)){
//            coordinatorLayout.scrollBy(0,dy);
            ((CoordinatorPullToRefresh)coordinatorLayout).dragDown(dy);
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }


    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
//        int dy=coordinatorLayout.getScrollY();
//        coordinatorLayout.scrollBy(0,-dy);
//        Log.i("bbb","coordinatorLayout dy:"+dy);
        ((CoordinatorPullToRefresh)coordinatorLayout).releaseDrag();

        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }


}


