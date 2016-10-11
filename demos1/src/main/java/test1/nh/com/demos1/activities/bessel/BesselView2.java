package test1.nh.com.demos1.activities.bessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import test1.nh.com.demos1.utils.math.MathVector2D;

/**
 * Created by Administrator on 2016/10/11.
 */
public class BesselView2 extends View {


    public BesselView2(Context context) {
        super(context);
        initPath();

    }

    public BesselView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPath();

    }

    public BesselView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPath();
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
        return 300;
    }

    private int getDesiredWidth() {
        return 300;
    }

    Path path;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawCircle( p_1[0].x/2+p_1[1].x/2,p_1[0].y/2+p_1[1].y/2,(int)(r1/Math.sqrt(2)), paint);
        canvas.drawCircle( p_2[2].x/2+p_2[3].x/2,p_2[2].y/2+p_2[3].y/2,(int)(r2/Math.sqrt(2)), paint);


        canvas.drawPath(path,paint);

//        canvas.drawCircle( p_center1.x,p_center1.y,r1, paint);
//        canvas.drawCircle( p_center2.x,p_center2.y,r2, paint);


//        canvas.drawCircle( p_1[0].x,p_1[0].y,1, paintCircle);
//        canvas.drawCircle( p_1[1].x,p_1[1].y,1, paintCircle);
//        canvas.drawCircle( p_2[3].x,p_2[3].y,1, paintCircle);
//        canvas.drawCircle( p_2[2].x,p_2[2].y,1, paintCircle);
//        canvas.drawCircle( p_ctrl1.x,p_ctrl1.y,1, paintCircle);
//        canvas.drawCircle( p_ctrl2.x,p_ctrl2.y,1, paintCircle);
    }

    Paint paint,paintCircle;

    Point p_center1=new Point(50,50);
    Point p_center2=new Point(60,80);

    Point p_ctrl1,p_ctrl2;

    private void resetCtrlPoints(){
        float n=2f;
        int x1= (int) (p_center1.x+(p_center2.x-p_center1.x)/n);
        int y1= (int) (p_center1.y+(p_center2.y-p_center1.y)/n);
        p_ctrl1=new Point(x1,y1);

        MathVector2D.Vector v1=new MathVector2D.Vector( p_center2.x-p_center1.x , p_center2.y-p_center1.y );
        v1.scaleTo(8);
        v1.addAngle(-90);
        p_ctrl1.offset(v1.dx,v1.dy);

        int x2= (int) (p_center2.x-(p_center2.x-p_center1.x)/n);
        int y2= (int) (p_center2.y-(p_center2.y-p_center1.y)/n);
        p_ctrl2=new Point(x2,y2);

        v1.addAngle(-180);
        p_ctrl2.offset(v1.dx,v1.dy);

    }

    int r1=10,r2=20;

    Point[] p_1=new Point[4];
    Point[] p_2=new Point[4];


    private void initPath(){
        resetCtrlPoints();

        path=new Path();

        p_1[0]=new Point(p_center1.x,p_center1.y);
        MathVector2D.Vector v1=new MathVector2D.Vector( p_center2.x-p_center1.x , p_center2.y-p_center1.y );
        v1.scaleTo(r1);
        v1.addAngle(-45);
        p_1[0].offset(v1.dx,v1.dy);
        p_1[1]=new Point(p_center1.x,p_center1.y);
        v1.addAngle(90);
        p_1[1].offset(v1.dx,v1.dy);
        p_1[2]=new Point(p_center1.x,p_center1.y);
        v1.addAngle(90);
        p_1[2].offset(v1.dx,v1.dy);
        p_1[3]=new Point(p_center1.x,p_center1.y);
        v1.addAngle(90);
        p_1[3].offset(v1.dx,v1.dy);

        MathVector2D.Vector v2=new MathVector2D.Vector( p_center2.x-p_center1.x , p_center2.y-p_center1.y );
        v2.scaleTo(r2);
        v2.addAngle(-45);

        p_2[0]=new Point(p_center2.x,p_center2.y);
        p_2[0].offset(v2.dx,v2.dy);

        p_2[1]=new Point(p_center2.x,p_center2.y);
        v2.addAngle(90);
        p_2[1].offset(v2.dx,v2.dy);
        p_2[2]=new Point(p_center2.x,p_center2.y);
        v2.addAngle(90);
        p_2[2].offset(v2.dx,v2.dy);
        p_2[3]=new Point(p_center2.x,p_center2.y);
        v2.addAngle(90);
        p_2[3].offset(v2.dx,v2.dy);


        path.moveTo(p_1[0].x,p_1[0].y);
//        path.cubicTo(p_ctrl1.x,p_ctrl1.y,p_ctrl2.x,p_ctrl2.y,p_2[3].x,p_2[3].y);
        path.quadTo(p_ctrl1.x,p_ctrl1.y,p_2[3].x,p_2[3].y);
        path.lineTo(p_2[2].x,p_2[2].y);

//        path.cubicTo(p_ctrl2.x,p_ctrl2.y,p_ctrl1.x,p_ctrl1.y,p_1[1].x,p_1[1].y);
        path.quadTo(p_ctrl2.x,p_ctrl2.y,p_1[1].x,p_1[1].y);
        path.close();




        // ---init paint
        paint=new Paint();
        paint.setColor(Color.rgb(120,30,20));
        paint.setAntiAlias(true);


        paintCircle=new Paint();
        paintCircle.setColor(Color.rgb(20,130,20));
        paintCircle.setAntiAlias(true);

    }





}
