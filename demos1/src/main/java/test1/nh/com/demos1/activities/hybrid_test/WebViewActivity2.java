package test1.nh.com.demos1.activities.hybrid_test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import test1.nh.com.demos1.R;

public class WebViewActivity2 extends AppCompatActivity {

    public static void start(Context c){
        Intent i=new Intent(c,WebViewActivity2.class);
        c.startActivity(i);
    }

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);

        webView= (WebView) findViewById(R.id.my_web_view);
        webView.setHorizontalScrollBarEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());  // this is needed to trigger alert !!
        webView.setWebViewClient(new WebViewClient());

        // ----  enable JS
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/www/jqm2_transition.html");


    }
}