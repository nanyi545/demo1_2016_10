package test1.nh.com.demos1.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 16-9-4.
 */
public class TestMeasureView extends View {


    public TestMeasureView(Context context) {
        super(context);
    }

    public TestMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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


    public int measureDimension(int defaultSize, int measureSpec,String type) {
        int result=0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);


        String specModeStr="";



        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                specModeStr="EXACTLY";
                result = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                specModeStr="AT_MOST";
                result = Math.min(defaultSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                specModeStr="EXACTLY";
                result=specSize;
                break;
        }


        Log.i("ccc",type+"   specMode:"+specModeStr+"   specSize:"+specSize);
        return result;
    }


}

