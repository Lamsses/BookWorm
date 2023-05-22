package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.adapters.ForYou_RecyclerViewAdapter;
import com.example.myapplication.models.Book;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements RecyclerViewInterface {
    TextView SeeAll;
    ArrayList<Book> Books = new ArrayList<>();
    private Random random;
    private int randomNumber;
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
        // Initialize the random number generator
        random = new Random();

        // Generate the initial random number
        generateRandomNumber();
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
        RecyclerView newReleaseRV = view.findViewById(R.id.new_release_rv);

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
        Bundle bundle = this.getArguments();
        if (bundle != null){

        ArrayList<String> selectedCategory = bundle.getStringArrayList("SELECTED_CATEGORY");
        for (Book book:Books) {
            if (selectedCategory.contains(book.getCategoryName())){
                forYou.add(book);
            }
        }
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                generateRandomNumber();
            }
        };
        timer.schedule(task, 24 * 60 * 60 * 1000, 24 * 60 * 60 * 1000);

        Book randomBook = Books.get(randomNumber);
        TextView bookTitle = view.findViewById(R.id.blind_book_title);
        TextView bookAuthor = view.findViewById(R.id.blind_book_authorName);
        ImageView bookImg = view.findViewById(R.id.blind_book_img);
        bookTitle.setText(randomBook.getTitle());
        bookAuthor.setText(randomBook.getAuthorName());
        bookImg.setImageResource(randomBook.getImg());

        MaterialCardView card = view.findViewById(R.id.blindReadId);
        card.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), BookPageActivity.class);
            intent.putExtra("BOOK_NAME",randomBook.getTitle());
            intent.putExtra("BOOK_CONTENT",randomBook.getContent());
            intent.putExtra("BOOK_IMAGE",randomBook.getImg());
            startActivity(intent);
        });


        ForYou_RecyclerViewAdapter adapter = new ForYou_RecyclerViewAdapter(getContext(), forYou, this);
        forYouRecyclerView.setAdapter(adapter);

        ForYou_RecyclerViewAdapter adapter1 = new ForYou_RecyclerViewAdapter(getContext(),Books,this);
        newReleaseRV.setAdapter(adapter1);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onItemClick(int position, ArrayList<Book> Books) {
        Intent intent = new Intent(getActivity(), BookPageActivity.class);
        intent.putExtra("BOOK_NAME",Books.get(position).getTitle());
        intent.putExtra("BOOK_CONTENT",Books.get(position).getContent());
        intent.putExtra("BOOK_IMAGE",Books.get(position).getImg());
        startActivity(intent);
    }
    private void generateRandomNumber() {
        randomNumber = random.nextInt(6);
    }
    @Override
    public void onSearchItemClick(String title, String authorName) {

    }
}