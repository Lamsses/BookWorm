package com.example.myapplication;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.adapters.ForYou_RecyclerViewAdapter;
import com.example.myapplication.models.Book;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements RecyclerViewInterface {
    TextView SeeAll;
    ArrayList<Book> Books = new ArrayList<>();

    ArrayList<Book> forYou = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        SeeAll = view.findViewById(R.id.seeall_foryou_text);
//        SeeAll.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ForYouPageActivity.class)));
        RecyclerView forYouRecyclerView = view.findViewById(R.id.foryou_rv);

        Books.add(new Book(1, "Book1", "","Me",  "fiction"));
        Books.add(new Book(2, "Book2", "", "Me","Non-fiction"));
        Books.add(new Book(3, "Book3", "", "Me",  "fiction"));
        Books.add(new Book(4, "Book4", "", "Me",  "Non-fiction"));
        Books.add(new Book(5, "Book5", "", "Me",  "Romance"));
//        Bundle bundle = this.getArguments();
//        ArrayList<String> selectedCategory = bundle.getStringArrayList("SELECTED_CATEGORY");
//        for (Book book:Books) {
//            if (selectedCategory.contains(book.getCategoryName())){
//                forYou.add(book);
//            }
//        }
//        if (bundle != null){
//
//        }
        MainActivity main = new MainActivity();
        forYou = main.forYou;

        ForYou_RecyclerViewAdapter adapter = new ForYou_RecyclerViewAdapter(getContext(), forYou, this);
        forYouRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int position, ArrayList<Book> Books) {
        Intent intent = new Intent(getActivity(), BookPageActivity.class);
        intent.putExtra("BOOK_NAME",Books.get(position).getTitle());
        intent.putExtra("BOOK_CONTENT",Books.get(position).getContent());
        intent.putExtra("BOOK_IMAGE",R.drawable.ic_launcher_background);
        startActivity(intent);
    }
}