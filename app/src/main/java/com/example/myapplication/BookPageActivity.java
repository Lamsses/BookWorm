package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_book);

        String bookName = getIntent().getStringExtra("BOOK_NAME");
        String bookContent = getIntent().getStringExtra("BOOK_CONTENT");
        int bookImage = getIntent().getIntExtra("BOOK_IMAGE",0);

        TextView bookNameTV = findViewById(R.id.text_title);
        TextView bookContentTV = findViewById(R.id.text_content);
        ImageView bookImageView = findViewById(R.id.image_book);

        bookNameTV.setText(bookName);
        bookContentTV.setText(bookContent);
        bookImageView.setImageResource(bookImage);

    }
}
