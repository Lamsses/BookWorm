package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.models.Book;

import java.util.ArrayList;

public class Horizontal_RVA extends RecyclerView.Adapter<Horizontal_RVA.MyViewHolder2>{
    Context context;
    ArrayList<Book> Books;
    private final RecyclerViewInterface recyclerViewInterface;

    public Horizontal_RVA(Context context,ArrayList<Book> Books,RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.Books = Books;
    }
    public void setFilteredList (ArrayList<Book> filteredList){
        this.Books = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_horizontal,parent,false);

        return new Horizontal_RVA.MyViewHolder2(view,recyclerViewInterface,Books);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.bookTitle.setText(Books.get(position).getTitle());
        holder.authorName.setText(Books.get(position).getAuthorName());
        holder.imageView.setImageResource(Books.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return Books.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView bookTitle;
        TextView authorName;
        public MyViewHolder2(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface,ArrayList<Book> Books) {
            super(itemView);
            imageView = itemView.findViewById(R.id.book_img);
            bookTitle = itemView.findViewById(R.id.book_title);
            authorName = itemView.findViewById(R.id.authorName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){

                        int p = view.getId();
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onSearchItemClick(bookTitle.getText().toString(),authorName.getText().toString());
                        }

                    }
                }
            });
        }
    }
}
