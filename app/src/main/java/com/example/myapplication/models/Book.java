package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private String content;
    private String authorName;

    private String categoryName;

    public Book(int id, String title, String content, String authorName, String categoryName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


}
