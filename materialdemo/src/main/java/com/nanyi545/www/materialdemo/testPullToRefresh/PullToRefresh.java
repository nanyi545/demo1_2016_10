package com.nanyi545.www.materialdemo.testPullToRefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/2/27.
 */
public class PullToRefresh extends LinearLayout {


    public PullToRefresh(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PullToRefresh(Context context) {
        this(context,null);
    }

    public PullToRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }



    private Scroller mScroller;


    // last position on touch screen
    private float mLastX=0;
    private float mLastY=0;


    private void initView(Context context){
        mScroller=new Scroller(context);
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xTouch = event.getX();
        float yTouch = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("ccc","onTouchEvent-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = xTouch-mLastX;
                float deltaY = yTouch-mLastY;
                float scrollByStart = deltaY;

                scrollBy(0, (int) -scrollByStart);
                Log.i("ccc","onTouchEvent-ACTION_MOVE   yTouch:"+yTouch+"    ------  getScrollY:"+getScrollY() +"----- deltaY:"+deltaY );
                //    event.getX()  : position of the touch event ...
                //    getScrollX()  : total scroll of the view     left-->plus,  right-->minus
                //    deltaX  :  scroll left --> minus      scroll right --> plus

                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                //-- full screen scroll --
//                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
//                int dx = targetIndex * getWidth() - getScrollX();


                int dy =  - getScrollY();


                //  ---scroll to with out animation
//                scrollTo(getScrollX()+dx,0);
                // ---use scroller, scroll with animation ---init scroller ...
                mScroller.startScroll(0, getScrollY(), 0, dy,400);
                invalidate();

                Log.i("ccc","onTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }

        mLastX = xTouch;  // this gets updated in MotionEvent.ACTION_DOWN
        mLastY = yTouch;
        Log.i("ccc","---------mLastX:"+mLastX+"    mLastY:"+mLastY+"------------");

//        return super.onTouchEvent(event);
        return true;
    }



    @Override
    public void computeScroll() {
        Log.i("ccc","--on computeScroll---");
        // 第三步，重写computeScroll()方法，在其内部调用scrollTo或ScrollBy方法，完成滑动过程
        if (mScroller.computeScrollOffset()) {
            Log.i("ccc","to x:"+mScroller.getCurrX()+"  to y:"+mScroller.getCurrY()+"----in:"+Thread.currentThread().getName());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }




}
