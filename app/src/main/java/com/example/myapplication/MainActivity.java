package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.adapters.ForYou_RecyclerViewAdapter;
import com.example.myapplication.models.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    TextView SeeAll;
    ArrayList<Book> Books = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeeAll = findViewById(R.id.seeall_foryou_text);
        SeeAll.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ForYouPageActivity.class)));
        RecyclerView forYouRecyclerView = findViewById(R.id.foryou_rv);

        Books.add(new Book(1, "Book1", "noice book", "MyAss", 1));
        Books.add(new Book(2, "Book2", "noice book", "MyAss", 1));
        Books.add(new Book(3, "Book3", "noice book", "MyAss", 1));
        Books.add(new Book(4, "Book4", "noice book", "MyAss", 1));
        Books.add(new Book(5, "Book5", "noice book", "MyAss", 1));

        ForYou_RecyclerViewAdapter adapter = new ForYou_RecyclerViewAdapter(this, Books, this);
        forYouRecyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, BookPageActivity.class);
        intent.putExtra("BOOK_NAME",Books.get(position).getTitle());
        intent.putExtra("BOOK_CONTENT",Books.get(position).getDescription());
        intent.putExtra("BOOK_IMAGE",R.drawable.ic_launcher_background);
        startActivity(intent);


    }
}
