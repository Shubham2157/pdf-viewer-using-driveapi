package com.shubham.dkgoelvol2sol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ViewActivity extends AppCompatActivity {

    WebView webView;
//    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);

        webView = findViewById(R.id.webview);
//        textView = findViewById(R.id.text);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String str = intent.getStringExtra("booklink");
        String url = null;
        try {
            url = "https://drive.google.com/viewerng/viewer?embedded=true&url="+ URLEncoder.encode(str, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setPluginState(WebSettings.PluginState.ON);

        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                getSupportActionBar().setTitle("Loading...");
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                    getSupportActionBar().setTitle(R.string.app_name);
                }
            }
        });

        //https://stackoverflow.com/questions/27717214/android-webview-remove-pop-out-option-in-google-drive-doc-viewer/36026500  (to remove the icon)

        webView.setWebViewClient(new WebViewClient() {

            //once the page is loaded get the html element by class or id and through javascript hide it.
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('ndfHFb-c4YZDc-GSQQnc-LgbsSe ndfHFb-c4YZDc-to915-LgbsSe VIpgJd-TzA9Ye-eEGnhe ndfHFb-c4YZDc-LgbsSe')[0].style.display='none'; })()");
            }
        });

        webView.loadUrl(url);
    }
}
