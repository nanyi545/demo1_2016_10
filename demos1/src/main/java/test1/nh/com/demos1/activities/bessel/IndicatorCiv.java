package test1.nh.com.demos1.activities.bessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import test1.nh.com.demos1.customView.CircleImageView;
import test1.nh.com.demos1.utils.math.MathVector2D;

/**
 * Created by Administrator on 2016/10/13.
 */
public class IndicatorCiv extends CircleImageView {


    private static int currentState;
    private static final int STATE_AT_START=1;
    private static final int STATE_STRETCHING=2;
    private static final int STATE_RELEASING_TO_START=3;
    private static final int STATE_RELEASING_TO_END=4;
    private static final int STATE_AT_END=5;
    private static final int STATE_DISOLVING=6;




    public IndicatorCiv(Context context) {
        super(context);
        init();
    }

    public IndicatorCiv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndicatorCiv(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    TextPaint textP;
    Rect textRect;
    Paint bgPaint;

    Paint paintCircle;

    private void init(){
        textP = new TextPaint();
        textP.setTextSize(28);
        textP.setColor(Color.rgb(255,255,255));
        textP.setFlags(TextPaint.ANTI_ALIAS_FLAG);
        textP.setTextAlign(Paint.Align.CENTER);


        bgPaint=new Paint();
        bgPaint.setColor(Color.rgb(255,64,64));
        bgPaint.setAntiAlias(true);

        textRect=new Rect();

        paintCircle=new Paint();
        paintCircle.setColor(Color.rgb(20,130,20));
        paintCircle.setAntiAlias(true);

    }


    int textX;
    int textY;
    int stickPointX;
    int stickPointY;
    int stickerR=15;
    private boolean withSticker =false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawHint(canvas);


    }


    int digits=0;
    float circleRadius=0;

    private void drawHint(Canvas canvas){

        if (currentCount>0) {
            if (digits == 1) {
                canvas.drawCircle(textX, textY - textRect.height() / 2, circleRadius, bgPaint);
            } else {
                int halfWidth =  (textRect.width() - textRect.width() / digits)/2;
                textRect.offsetTo(textX - textRect.width() / 2, textY - textRect.height());
                canvas.drawRect(textX - halfWidth, (textRect.centerY() - circleRadius), textX + halfWidth, (textRect.centerY() + circleRadius), bgPaint);
                canvas.drawCircle(textX - halfWidth, textRect.centerY(), circleRadius, bgPaint);
                canvas.drawCircle(textX + halfWidth, textRect.centerY(), circleRadius, bgPaint);
            }
        }

        canvas.drawText(""+currentCount,textX,textY,textP);
    }


    int currentCount=12;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        resetTextPosition();
        stickPointX=getWidth()/4*3;
        stickPointY=getHeight()/4;
        mesureHintText();
    }

    private void resetTextPosition() {
        textX=getWidth()/4*3;
        textY=getHeight()/4;
    }


    private void mesureHintText() {
        textP.getTextBounds(""+currentCount,0,(""+currentCount).length(),textRect);
        digits=(""+currentCount).length();
        circleRadius= (float) Math.sqrt(textRect.width()*textRect.width()/digits/digits+textRect.height()*textRect.height())/2;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        switch (action){
            case MotionEvent.ACTION_DOWN:

                int touchX_= (int) event.getX();
                int touchY_= (int) event.getY();

                MathVector2D.Vector temp=new MathVector2D.Vector(touchX_-stickPointX,touchY_-stickPointY);

                if (temp.getLength()>(2*stickerR)){
                    withSticker =false;
                    return true;
                }  else {
                    withSticker =true;
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if(withSticker) {
                    currentState = STATE_STRETCHING;

                    int touchX = (int) event.getX();
                    int touchY = (int) event.getY();

                    textX = touchX;
                    textY= touchY;

//                    initPath();
                    invalidate();

                }

                break;

            case MotionEvent.ACTION_UP:
//                if (withSticker) {
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
