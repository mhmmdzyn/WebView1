package com.example.zein.webview1;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private WebView webView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyBrowser());
        webView.loadUrl("https://idezein.wordpress.com");

        //fungsi refresh juga
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(this);

    }

    //fungsi utk swipe refresh web page
    @Override
    public void onRefresh() {
      webView.reload();
      refreshLayout.setRefreshing(false);
    }


    //fungsi utk supaya aplikasi tdk membuka browser
    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        //fungsi utk menampilkan pesan ktika internet disconnect
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            view.loadData("Maaf koneksi internet anda hilang", "text/html", "utf-8");
            super.onReceivedError(view, request, error);
        }

        //@Override
        //public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
          //  view.loadData("Maaf koneksi internet anda hilang", "text/html", "utf-8");
            //super.onReceivedHttpError(view, request, errorResponse);
        //}
    }

    //kelas utk perintah back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
          webView.goBack();
          return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
