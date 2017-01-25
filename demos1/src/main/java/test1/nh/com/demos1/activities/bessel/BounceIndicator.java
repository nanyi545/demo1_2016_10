package test1.nh.com.demos1.activities.bessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Scroller;

import test1.nh.com.demos1.R;
import test1.nh.com.demos1.utils.math.MathVector2D;

/**
 * Created by Administrator on 2017/1/24.
 */
public class BounceIndicator extends View {



    private static int currentState ;
    private static final int STATE_AT_START=1;
    private static final int STATE_STRETCHING=2;
    private static final int STATE_RELEASING_TO_START=4;
    private static final int STATE_RELEASING_TO_END=5;
    private static final int STATE_AT_END=6;
    private static final int STATE_DISOLVING=7;


    private void initState(){
        currentState=STATE_AT_START;
    }

    private boolean needToDrawConnectingPath(){
        return ((currentState==STATE_STRETCHING)||(currentState==STATE_RELEASING_TO_START)||(currentState==STATE_RELEASING_TO_END));
    }

    private void setState(int newState){
        currentState=newState;
    }


    private void modifyStateFromDrag(){
        float dy = startY - endY;
        float dx = startX - endX;
        MathVector2D.VectorF v = new MathVector2D.VectorF(dx, dy);
        if (v.getLength() < distanceThreshold ) {
            setState(STATE_STRETCHING);
        } else {
            setState(STATE_RELEASING_TO_END);
        }
    }


    private void toStart(){
        if (currentState==STATE_STRETCHING){
            float dx = stickX - mLastX;
            float dy = stickY - mLastY;
            toStartScroller.startScroll((int)mLastX, (int)mLastY, (int)dx, (int)dy);
        }
    }

    private void toEnd(){
        if (currentState==STATE_RELEASING_TO_END){
            float dx = endX - startX;
            float dy = endY - startY;
            toEndScroller.startScroll((int) startX, (int) startY, (int) dx, (int) dy,10);
        }
    }




    public BounceIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BounceIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BounceIndicator(Context context) {
        super(context);
        init();
    }


    private void init(){

        textP=new TextPaint();
        int pixel= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());
        textP.setTextSize(pixel);
        textP.setColor(Color.rgb(255,255,255));
        textP.setFlags(TextPaint.ANTI_ALIAS_FLAG);
        textP.setTextAlign(Paint.Align.CENTER);

        bgPaint=new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.RED);

        testPaint=new Paint();
        testPaint.setAntiAlias(true);
        testPaint.setColor(Color.GREEN);


        toStartScroller=new Scroller(getContext(),new OvershootInterpolator(2));
        toEndScroller=new Scroller(getContext());
        disolveController=new Scroller(getContext());


        connectingPath =new Path();
        initState();

        initConnectingPath();
    }



    private void initConnectingPath(){

        float distance= (float) Math.sqrt((startX-endX)*(startX-endX)+(startY-endY)*(startY-endY)) / (distanceThreshold);

        float ratio=initialRatio/(distance+1f);

        startR=endR*ratio;

        connectingPath =new Path();

        PointF startPoint_1 =new PointF(startX,startY);
        PointF startPoint_2 =new PointF(startX,startY);
        PointF endPoint_1=new PointF(endX,endY);
        PointF endPoint_2=new PointF(endX,endY);

        MathVector2D.VectorF v1=new MathVector2D.VectorF( endX-startX, endY-startY );

        v1.scaleTo(startR);
        v1.addAngle(-90);
        startPoint_1.offset(v1.dx,v1.dy);
        v1.addAngle(180);
        startPoint_2.offset(v1.dx,v1.dy);


        MathVector2D.VectorF v2=new MathVector2D.VectorF( endX-startX, endY-startY );

        v2.scaleTo(endR*0.9f);
        v2.addAngle(-90);
        endPoint_1.offset(v2.dx,v2.dy);

        v2.addAngle(180);
        endPoint_2.offset(v2.dx,v2.dy);

        PointF ctrlP=new PointF( endX/2+startX/2 , endY/2 + startY/2  );

        connectingPath.moveTo(startPoint_1.x, startPoint_1.y);
        connectingPath.quadTo(ctrlP.x,ctrlP.y,endPoint_1.x,endPoint_1.y);
        connectingPath.lineTo(endPoint_2.x,endPoint_2.y);
        connectingPath.quadTo(ctrlP.x,ctrlP.y, startPoint_2.x, startPoint_2.y);
        connectingPath.lineTo(startPoint_1.x, startPoint_1.y);

    }



    private int vWidth,vHeight;
    Rect bgRect;
    private boolean drawBg=true;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureHintText();

        int widthSize=measureDimension(getMinWidth(),widthMeasureSpec);
        int heightSize=measureDimension(getMinHeight(), heightMeasureSpec);
        vWidth=widthSize;
        vHeight=heightSize;
        bgRect=new Rect(0,0,vWidth,vHeight);

        mLastX=vWidth/2;
        mLastY=vHeight/2;

        stickX=vWidth/2;
        stickY=vHeight/2;

        startX=stickX;
        startY=stickY;
        startR=vHeight/2;

        endX=stickX;
        endY=stickY;
        endR=vHeight/2;

        distanceThreshold=endR*3;

        setMeasuredDimension(widthSize, heightSize);
    }

    private int getMinHeight() {
        if (textRect!=null) return textRect.height()*2;
        else return 50;
    }
    private int getMinWidth() {
        return getMinHeight();
    }


    private int measureDimension(int defaultSize, int measureSpec) {
        int result=0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result = defaultSize;
                break;
            case MeasureSpec.AT_MOST:   //  -----> wrap_content   !!!!!
                result = Math.min(defaultSize, specSize);
                break;
            case MeasureSpec.EXACTLY:   // ---->  1  specifying size    2  match_parent  !!!!!!
                result=specSize;   // spec Size is   in unit px  !!!
                break;
        }
        return result;
    }



    private void measureHintText(){
        textRect=new Rect();
        textP.getTextBounds(""+currentCount,0,(""+currentCount).length(),textRect);
    }


    int currentCount=127;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawBg){
            if (bgRect != null) {
                canvas.save();
                canvas.clipRect(bgRect);
                canvas.drawColor(ContextCompat.getColor(getContext(), R.color.Blue400));
                canvas.restore();
            }
        }

        if (needToDrawConnectingPath()){
            canvas.drawCircle(startX,startY,startR,bgPaint);
            canvas.drawPath(connectingPath, bgPaint);
        }

        drawTextAtXY(canvas,endX,endY);
    }


    private void drawTextAtXY(Canvas canvas,float x,float y){
        if ((currentCount+"").length()==1){
            canvas.drawCircle(x,y,vHeight/2,bgPaint);
        } else if ((currentCount+"").length()>1){
            float textWidth=textRect.width()/(currentCount+"").length()*((currentCount+"").length()-1.7f);
            canvas.drawCircle(x-textWidth,y,vHeight/2,bgPaint);
            canvas.drawCircle(x+textWidth,y,vHeight/2,bgPaint);
            canvas.drawRect(new RectF(x-textWidth,y-vWidth/2,x+textWidth,y+vHeight/2),bgPaint);
        }
        canvas.drawText(""+currentCount,x,y+textRect.height()/2,textP);
    }


    TextPaint textP;
    Rect textRect;
    Paint bgPaint,testPaint;


    float mLastX,mLastY;  // last position of touch
    float stickX,stickY;  //  center of the view , should not change
    float startX,startY,startR,endX,endY,endR;   // start circle  --> around stick point,   end circle --> around touch point
    float distanceThreshold;
    float initialRatio=1f;  //  ratio= startR / endR

    Path connectingPath;   // path connecting the start and end circle


    private Scroller toEndScroller,toStartScroller,disolveController;




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        float xTouch = event.getX();
        float yTouch = event.getY();
        mLastX = xTouch;
        mLastY = yTouch;
        endX=mLastX;
        endY=mLastY;

        modifyStateFromDrag();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                float deltaX = xTouch-mLastX;
                float deltaY = yTouch-mLastY;


                toEnd();
                if (needToDrawConnectingPath()){
                    initConnectingPath();
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:

//                float dx = stickX - mLastX;
//                float dy = stickY - mLastY;
//                toStartScroller.startScroll((int)mLastX, (int)mLastY, (int)dx, (int)dy);

                toStart();
                invalidate();

                break;

        }


        return true;
    }






    @Override
    public void computeScroll() {
        if (toStartScroller.computeScrollOffset()) {
            mLastX=toStartScroller.getCurrX();
            mLastY=toStartScroller.getCurrY();
            endX=mLastX;
            endY=mLastY;
            if (needToDrawConnectingPath()) initConnectingPath();
            invalidate();
        }
        if (toEndScroller.computeScrollOffset()) {
            startX=toEndScroller.getCurrX();
            startY=toEndScroller.getCurrY();
            if (needToDrawConnectingPath()) initConnectingPath();
            invalidate();
        }



    }





}
