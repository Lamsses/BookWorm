package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapters.ForYou_RecyclerViewAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.models.Book;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    ActivityMainBinding binding;
    TextView SeeAll;
    ArrayList<Book> Books = new ArrayList<>();

        ArrayList<Book> forYou = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> selectedCategory = getIntent().getStringArrayListExtra("SELECTED_CATEGORY");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        HomeFragment fragment = new HomeFragment();
        bundle.putStringArrayList("SELECTED_CATEGORY",selectedCategory);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

        binding.bottomNavView.setOnNavigationItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.home:
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    Bundle bundle1 = new Bundle();
                    HomeFragment fragment1 = new HomeFragment();
                    bundle1.putStringArrayList("SELECTED_CATEGORY",selectedCategory);
                    fragment1.setArguments(bundle1);
                    fragmentTransaction1.replace(R.id.frame_layout,fragment1);
                    fragmentTransaction1.commit();
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;

            }

            return true;
        });

//        SeeAll = findViewById(R.id.seeall_foryou_text);
//        SeeAll.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ForYouPageActivity.class)));
//        RecyclerView forYouRecyclerView = findViewById(R.id.foryou_rv);
//
//        Books.add(new Book(1, "Book1", "","Me",  "fiction"));
//        Books.add(new Book(2, "Book2", "", "Me","Non-fiction"));
//        Books.add(new Book(3, "Book3", "", "Me",  "fiction"));
//        Books.add(new Book(4, "Book4", "", "Me",  "Non-fiction"));
//        Books.add(new Book(5, "Book5", "", "Me",  "Romance"));
//        for (Book book:Books) {
//            if (selectedCategory.contains(book.getCategoryName())){
//                forYou.add(book);
//            }
//        }
//
//        ForYou_RecyclerViewAdapter adapter = new ForYou_RecyclerViewAdapter(this, forYou, this);
//        forYouRecyclerView.setAdapter(adapter);





    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(int position, ArrayList<Book> Books) {
        Intent intent = new Intent(MainActivity.this, BookPageActivity.class);
        intent.putExtra("BOOK_NAME",Books.get(position).getTitle());
        intent.putExtra("BOOK_CONTENT",Books.get(position).getContent());
        intent.putExtra("BOOK_IMAGE",R.drawable.ic_launcher_background);
        startActivity(intent);


    }

    @Override
    public void onSearchItemClick(String title, String authorName) {

    }

}
