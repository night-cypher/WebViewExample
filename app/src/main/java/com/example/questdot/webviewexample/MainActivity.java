package com.example.questdot.webviewexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    private WebView webview;
    private static final String TAG = "Main";
    private ProgressDialog progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.webview = (WebView)findViewById(R.id.webview1);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        progressBar = ProgressDialog.show(this, "WebView Example", "Loading...");

        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished");
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();

            }
        });
        webview.loadUrl("http://www.questdot.com");
    }
}
