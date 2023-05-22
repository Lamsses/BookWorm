package com.example.myapplication;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BookPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_book);

        String bookName = getIntent().getStringExtra("BOOK_NAME");
        String bookContent = getIntent().getStringExtra("BOOK_CONTENT");
        int bookImage = getIntent().getIntExtra("BOOK_IMAGE",0);

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        TextView bookNameTV = findViewById(R.id.text_title);
//        TextView bookContentTV = findViewById(R.id.text_content);
        ImageView bookImageView = findViewById(R.id.image_book);


        webView.loadUrl(bookContent);
        bookNameTV.setText(bookName);
//        bookContentTV.setText(bookContent);
        bookImageView.setImageResource(bookImage);

    }


}

