package test1.nh.com.demos1.activities.cyclic_galary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/7.
 */
public class VpIndicator extends View {


    public VpIndicator(Context context) {
        super(context);
        init();
    }

    public VpIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VpIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    float mDensity;

    int totalCounts=3;
    private static final int squareSizeInDp=20;
    private static final int circleRadiusInDp=6;
    int circleRadiusInPx=10;



    Paint bgCirclePaint;

    private void init(){
        mDensity = getResources().getDisplayMetrics().density;
        circleRadiusInPx= (int) (circleRadiusInDp*mDensity);

        bgCirclePaint= new Paint();
        bgCirclePaint.setAntiAlias(true);
        bgCirclePaint.setColor(Color.rgb(255,255,255));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Get the width measurement
        int widthSize = View.resolveSize(getDesiredWidth(), widthMeasureSpec);
        viewWidth=widthSize;
        //Get the height measurement
        int heightSize = View.resolveSize(getDesiredHeight(), heightMeasureSpec);
        viewHeight=heightSize;

        //MUST call this to store the measurements
        setMeasuredDimension(widthSize, heightSize);

    }

    int viewWidth,viewHeight;


    private int getDesiredHeight() {
        return (int) (squareSizeInDp*mDensity);
    }

    private int getDesiredWidth() {
        return (int) (squareSizeInDp*mDensity)*totalCounts;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(viewWidth/6,viewHeight/2,circleRadiusInPx,bgCirclePaint);
        canvas.drawCircle(viewWidth/6*3,viewHeight/2,circleRadiusInPx,bgCirclePaint);
        canvas.drawCircle(viewWidth/6*5,viewHeight/2,circleRadiusInPx,bgCirclePaint);

    }
}




