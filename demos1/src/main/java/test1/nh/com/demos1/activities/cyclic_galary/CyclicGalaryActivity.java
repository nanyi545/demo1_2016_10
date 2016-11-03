package test1.nh.com.demos1.activities.cyclic_galary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test1.nh.com.demos1.R;

public class CyclicGalaryActivity extends AppCompatActivity {

    public static void start(Context c){
        Intent i=new Intent(c,CyclicGalaryActivity.class);
        c.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyclic_galary);
    }
}
