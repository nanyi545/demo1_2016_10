package test1.nh.com.demos1.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import test1.nh.com.demos1.R;

public class VectorDrawableActivity extends AppCompatActivity {

    public static void start(Context c){
        Intent i=new Intent(c,VectorDrawableActivity.class);
        c.startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);

        ImageView androidImageView = (ImageView) findViewById(R.id.dynamic_imag);
        Drawable drawable = androidImageView.getDrawable();

        String classname=drawable.getClass().getName();
        Log.i("AAA","class_name:"+classname);


        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

    }
}
