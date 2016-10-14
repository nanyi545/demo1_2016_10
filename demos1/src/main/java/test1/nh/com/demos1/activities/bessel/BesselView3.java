package test1.nh.com.demos1.activities.bessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import test1.nh.com.demos1.utils.math.MathVector2D;

/**
 * Created by Administrator on 2016/10/14.
 */
public class BesselView3 extends View {


    private static int currentState ;
    private static final int STATE_AT_START=1;
    private static final int STATE_STRETCHING=2;
    private static final int STATE_RELEASING_TO_START=3;
    private static final int STATE_RELEASING_TO_END=4;
    private static final int STATE_AT_END=5;
    private static final int STATE_DISOLVING=6;



    public BesselView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BesselView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BesselView3(Context context) {
        super(context);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Get the width measurement
        int widthSize = View.resolveSize(getDesiredWidth(), widthMeasureSpec);

        //Get the height measurement
        int heightSize = View.resolveSize(getDesiredHeight(), heightMeasureSpec);

        //MUST call this to store the measurements
        setMeasuredDimension(widthSize, heightSize);

    }

    private int getDesiredHeight() {
        return 150;
    }

    private int getDesiredWidth() {
        return 150;
    }



    PointF stickPoint=new PointF(30f,30f);
    PointF endPoint=new PointF(80f,120f);
    PointF ctrlP;

    PointF stickPoint_1,stickPoint_2,endPoint_1,endPoint_2;

    float r1=10f,r2=20f;

    Path path;
    Paint bgPaint , circlePaint ;


    private void init(){

        currentState= STATE_AT_START;

        path=new Path();
        bgPaint=new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.RED);

        circlePaint =new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.GREEN);


        initPath();
    }



    private void initPath(){

        stickPoint_1=new PointF(stickPoint.x,stickPoint.y);
        stickPoint_2=new PointF(stickPoint.x,stickPoint.y);
        endPoint_1=new PointF(endPoint.x,endPoint.y);
        endPoint_2=new PointF(endPoint.x,endPoint.y);


        MathVector2D.VectorF v1=new MathVector2D.VectorF( endPoint.x-stickPoint.x , endPoint.y-stickPoint.y );

        v1.scaleTo(r1);
        v1.addAngle(-90);
        stickPoint_1.offset(v1.dx,v1.dy);

        v1.addAngle(180);
        stickPoint_2.offset(v1.dx,v1.dy);


        MathVector2D.VectorF v2=new MathVector2D.VectorF( endPoint.x-stickPoint.x , endPoint.y-stickPoint.y );
        v2.scaleTo(r2);
        v2.addAngle(-90);
        endPoint_1.offset(v2.dx,v2.dy);

        v2.addAngle(180);
        endPoint_2.offset(v2.dx,v2.dy);

        ctrlP=new PointF( stickPoint.x/2 + endPoint.x/2 , stickPoint.y/2 + endPoint.y/2  );


        path.moveTo(stickPoint_1.x,stickPoint_1.y);
        path.quadTo(ctrlP.x,ctrlP.y,endPoint_1.x,endPoint_1.y);
        path.lineTo(endPoint_2.x,endPoint_2.y);
        path.quadTo(ctrlP.x,ctrlP.y,stickPoint_2.x,stickPoint_2.y);
        path.lineTo(stickPoint_1.x,stickPoint_1.y);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, bgPaint);

        canvas.drawCircle(  stickPoint.x,stickPoint.y  ,2, circlePaint);
        canvas.drawCircle(  endPoint.x,endPoint.y  ,2, circlePaint);
        canvas.drawCircle(  ctrlP.x,ctrlP.y  ,2, circlePaint);
        canvas.drawCircle(  stickPoint_1.x,stickPoint_1.y  ,2, circlePaint);
        canvas.drawCircle(  stickPoint_2.x,stickPoint_2.y  ,2, circlePaint);
        canvas.drawCircle(  endPoint_1.x,endPoint_1.y  ,2, circlePaint);
        canvas.drawCircle(  endPoint_2.x,endPoint_2.y  ,2, circlePaint);

        if (currentState==STATE_STRETCHING){
            canvas.drawCircle(  stickPoint.x,stickPoint.y  ,2, circlePaint);
            canvas.drawCircle(  endPoint.x,endPoint.y  ,2, circlePaint);
            canvas.drawCircle(  ctrlP.x,ctrlP.y  ,2, circlePaint);

            canvas.drawCircle(  stickPoint_1.x,stickPoint_1.y  ,2, circlePaint);
            canvas.drawCircle(  stickPoint_2.x,stickPoint_2.y  ,2, circlePaint);

            canvas.drawCircle(  endPoint_1.x,endPoint_1.y  ,2, circlePaint);
            canvas.drawCircle(  endPoint_2.x,endPoint_2.y  ,2, circlePaint);

        }

    }

    private boolean withInStart=false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        switch (action){
            case MotionEvent.ACTION_DOWN:

                float touchX_= event.getX();
                float touchY_= event.getY();

                MathVector2D.VectorF temp=new MathVector2D.VectorF(touchX_-stickPoint.x,touchY_-stickPoint.y);

                if (temp.getLength()>(2*r1)){
                    withInStart=false;
                    return true;
                }  else {
                    withInStart=true;
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if(withInStart) {

                    currentState = STATE_STRETCHING;

                    float touchX =  event.getX();
                    float touchY =  event.getY();

                    endPoint.x = touchX;
                    endPoint.y = touchY;
                    initPath();
                    invalidate();

                }

                break;

            case MotionEvent.ACTION_UP:
//                if (withInStart) {
//                    int dy = p_center1.x - p_center2.x;
//                    int dx = p_center1.y - p_center2.y;
//                    MathVector2D.Vector v = new MathVector2D.Vector(dx, dy);
//                    if (v.getLength() < 150) {
//                        dx = p_center1.x - p_center2.x;
//                        dy = p_center1.y - p_center2.y;
//                        toStartScroller.startScroll(p_center2.x, p_center2.y, dx, dy);
//                        currentState = STATE_RELEASING_TO_START;
//                        Log.i("BBB", "STATE_RELEASING_TO_START");
//                    } else {
//                        dx = p_center2.x - p_center1.x;
//                        dy = p_center2.y - p_center1.y;
//                        toEndScroller.startScroll(p_center1.x, p_center1.y, dx, dy);
//                        currentState = STATE_RELEASING_TO_END;
//                        Log.i("BBB", "STATE_RELEASING_TO_END");
//                    }
//
//                    invalidate();
//                }
                break;

        }


        return true;
    }





}
