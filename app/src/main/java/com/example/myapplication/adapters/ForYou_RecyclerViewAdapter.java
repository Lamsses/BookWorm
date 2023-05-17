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

public class ForYou_RecyclerViewAdapter extends RecyclerView.Adapter<ForYou_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Book> Books;
    private final RecyclerViewInterface recyclerViewInterface;
    public ForYou_RecyclerViewAdapter(Context context, ArrayList<Book> Books, RecyclerViewInterface recyclerViewInterface)
    {
        this.context = context;
        this.Books = Books;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public ForYou_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card,parent,false);

        return new ForYou_RecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ForYou_RecyclerViewAdapter.MyViewHolder holder, int position) {
    holder.bookTitle.setText(Books.get(position).getTitle());
    holder.authorName.setText(Books.get(position).getAuthorName());
    holder.imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return Books.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView bookTitle;
        TextView authorName;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mv_book);
            bookTitle = itemView.findViewById(R.id.tv_book_title);
            authorName = itemView.findViewById(R.id.tv_author_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }

                    }
                }
            });
        }
    }
}
