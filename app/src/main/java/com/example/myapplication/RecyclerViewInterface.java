package com.example.myapplication;

import com.example.myapplication.models.Book;

import java.util.ArrayList;

public interface RecyclerViewInterface  {
    void onItemClick(int position, ArrayList<Book>Books);
}
