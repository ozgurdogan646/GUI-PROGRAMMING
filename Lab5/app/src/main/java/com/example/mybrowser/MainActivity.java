package com.example.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private Button btnGo;
    private EditText txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.webview);
        btnGo = findViewById(R.id.btnGo);
        txtAddress = findViewById(R.id.adress);


        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);

        webview.setWebViewClient(new WebViewClient(){

            @Override

            public boolean shouldOverrideUrlLoading(WebView view,String url){

                view.loadUrl(url);
                CookieManager.getInstance().setAcceptCookie(true);
                return true;

            }

        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webview.loadUrl("http://"+ txtAddress.getText());
            }
        });

        if(getIntent() != null && getIntent().getData() != null)

        {
            txtAddress.setText(getIntent().getData().toString());
            webview.loadUrl(getIntent().getData().toString());
        }
    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
