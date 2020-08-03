package com.example.cs478_proj2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // set the list of websites
        ArrayList<String> websiteList = new ArrayList<>();
        makeWebsiteList(websiteList);

        // get the extra
        Bundle extras = getIntent().getExtras();

        // get the webview by id and open up the website for the specified phone
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        // load the url
        webView.loadUrl(websiteList.get(extras.getInt("phone_pos")));

    }

    // function adds websites to the declared array
    private void makeWebsiteList(ArrayList<String> tmp){
        tmp.add("https://www.apple.com/iphone-11-pro/");
        tmp.add("https://www.samsung.com/global/galaxy/galaxy-s10/");
        tmp.add("https://www.apple.com/iphone-11/");
        tmp.add("https://www.samsung.com/global/galaxy/galaxy-note10/");
        tmp.add("https://www.oneplus.com/7t");
        tmp.add("https://store.google.com/product/pixel_3a");
    }
}
