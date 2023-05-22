package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.adapters.Horizontal_RVA;
import com.example.myapplication.models.Book;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements RecyclerViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    ArrayList<Book> Books = new ArrayList<>();
    RecyclerView recyclerView;
    Horizontal_RVA adapter;
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Books.add(new Book(1, "Lord Lister", "file:///android_asset/Lord Lister.html","Blankensee", R.drawable.lordlister, "History"));
        Books.add(new Book(2, "Darwin", "file:///android_asset/Darwin.html", "Gamaliel Bradford", R.drawable.darwin, "Science"));
        Books.add(new Book(3, "A forgotten Prince of Wales", "file:///android_asset/A forgotten Prince of Wales.html", "Henry Curties", R.drawable.forgottenprinceofwales, "fiction"));
        Books.add(new Book(4, "Novelleja", "file:///android_asset/Novelleja.html", "Gottfried Keller", R.drawable.novelleja, "fiction"));
        Books.add(new Book(5, "Daddy Jake the Runaway", "file:///android_asset/Daddy Jake the Runaway.html", "Joel Chandler Harris", R.drawable.daddyjaketherunaway, "Horror"));
        Books.add(new Book(6, "Sam Bass", "file:///android_asset/Sam Bass.html", "Eugene Cunningham", R.drawable.sambass, "Adventure"));
        Books.add(new Book(7, "The skeleton crew", "file:///android_asset/The skeleton crew.html", "Wildfire Ned", R.drawable.theskeletoncrew, "Adventure"));
        Books.add(new Book(8, "Valerius Terminus: Of the Interpretation of Nature", "file:///android_asset/Valerius Terminus.html", "Francis Bacon", R.drawable.valeriusterminus, "History"));
        Books.add(new Book(9, "Journal of a tour in Marocco and the Great Atlas", "file:///android_asset/Journal in Marocco.html", "Joel Chandler Harris", R.drawable.journalinmarocco, "Adventure"));
        Books.add(new Book(10, "The Lost Princess of Oz", "file:///android_asset/The Lost Princess of Oz.html", "L. Frank Baum", R.drawable.thelostprincess, "fiction"));

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();

         recyclerView = view.findViewById(R.id.searchRV);
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         adapter = new Horizontal_RVA(getContext(),Books,this);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        return view;
    }

    private void filterList(String text) {
        ArrayList<Book> filteredList = new ArrayList<>();
        for (Book book: Books) {
            if (book.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(book);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "No items Found", Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onItemClick(int position, ArrayList<Book> Books) {
        Intent intent = new Intent(getActivity(), BookPageActivity.class);
        intent.putExtra("BOOK_NAME",Books.get(position).getTitle());
        intent.putExtra("BOOK_CONTENT",Books.get(position).getContent());
        intent.putExtra("BOOK_IMAGE",R.drawable.ic_launcher_background);
        startActivity(intent);
    }

    @Override
    public void onSearchItemClick(String title, String authorName) {
        Book theBook = null;
        for (Book book:Books) {
            if (book.getTitle().equals(title)){
                theBook = book;
            }
        }
        Intent intent = new Intent(getActivity(), BookPageActivity.class);
        intent.putExtra("BOOK_NAME",theBook.getTitle());
        intent.putExtra("BOOK_CONTENT",theBook.getContent());
        intent.putExtra("BOOK_IMAGE",theBook.getImg());
        startActivity(intent);
    }
}