package com.nanyi545.www.materialdemo.nestedScroll.no_coordinator_test.testWithCostumView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class NestedScrollingChildView2 extends View implements NestedScrollingChild, OnGestureListener {


  private final NestedScrollingChildHelper mNestedScrollingChildHelper;
  private GestureDetectorCompat mDetector;

  public NestedScrollingChildView2(Context context) {
    this(context, null);
  }

  public NestedScrollingChildView2(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public NestedScrollingChildView2(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
    mDetector = new GestureDetectorCompat(context, this);
    setNestedScrollingEnabled(true);
    paint1=new TextPaint();
    paint1.setColor(Color.RED);
    paint1.setTextSize(50);
    paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
  }

  @Override
  public void setNestedScrollingEnabled(boolean enabled) {
    mNestedScrollingChildHelper.setNestedScrollingEnabled(true);
  }

  @Override
  public boolean isNestedScrollingEnabled() {
    return mNestedScrollingChildHelper.isNestedScrollingEnabled();
  }

  @Override
  public boolean startNestedScroll(int axes) {
    return mNestedScrollingChildHelper.startNestedScroll(axes);
  }

  @Override
  public void stopNestedScroll() {
    mNestedScrollingChildHelper.stopNestedScroll();
  }

  @Override
  public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
    return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
  }

  @Override
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
  }

  @Override
  public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
    return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
  }

  @Override
  public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
    return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
  }

  @Override
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mNestedScrollingChildHelper.onDetachedFromWindow();
  }

  @Override
  public boolean hasNestedScrollingParent() {
    return mNestedScrollingChildHelper.hasNestedScrollingParent();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event){
    final boolean handled = mDetector.onTouchEvent(event);
//    if (!handled && event.getAction() == MotionEvent.ACTION_UP) {
//      stopNestedScroll();
//    }
    return handled;
  }

  @Override
  public boolean onDown(MotionEvent e) {
    startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
    return true;
  }

  @Override
  public void onShowPress(MotionEvent e) {
  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//    Log.i("bbb","Nested scroll child -- onscroll");
//    dispatchNestedPreScroll(0, (int) distanceY, null, null);
//    dispatchNestedScroll(0, 0, 0, 0, null);
    Log.i("mmm","hasNestedScrollingParent:"+mNestedScrollingChildHelper.hasNestedScrollingParent());
    scrollBy(0, (int) distanceY);
    return true;
  }

  @Override
  public void onLongPress(MotionEvent e) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return true;
  }


  TextPaint paint1;
  @Override
  protected void onDraw(Canvas canvas) {
     for(int ii=-10;ii<10;ii++){
       canvas.drawText(""+ii,0,50*ii,paint1);
     }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int widthSize=measureDimension(getMinWidth(),widthMeasureSpec);
    int heightSize = measureDimension(getMinHeight(), heightMeasureSpec);
    setMeasuredDimension(widthSize, heightSize);
  }

  private int getMinHeight() {
    return 150;
  }
  private int getMinWidth() {
    return 150;
  }



  private int measureDimension(int defaultSize, int measureSpec) {
    int result=0;

    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);

    String specModeStr="";

    switch (specMode){
      case MeasureSpec.UNSPECIFIED:
        specModeStr="UNSPECIFIED";
        result = defaultSize;
        break;
      case MeasureSpec.AT_MOST:   //  -----> wrap_content   !!!!!
        specModeStr="AT_MOST";
        result = Math.min(defaultSize, specSize);
        break;
      case MeasureSpec.EXACTLY:   // ---->  1  specifying size    2  match_parent  !!!!!!
        specModeStr="EXACTLY";
        result=specSize;   // spec Size is   in unit px  !!!
        break;
    }

    return result;
  }








}
