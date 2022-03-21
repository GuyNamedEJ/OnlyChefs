package com.example.onlychefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private String APIKEY = "9973533";
    // Create Grid Items on the main Screen
    public RecyclerView mRecyclerView;
    private List<String> titles;
    private List<Integer> mImages;
    private MyAdapter adapter;

    //Get the Search Bar and button
    private EditText searchBar;
    private Button searchButton;
    private String searchText;

    private ArrayList<Recipe> recipes;
    private RecyclerView recipeListRecyclerView;
    private Recipe myRecipe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creates Grid Items for the categories on the menu
        createMenuGrid();

        searchBar = findViewById(R.id.search_bar);
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText = searchBar.getText().toString();
                searchText = searchText.replaceAll("\\s","%20");
                if(searchText.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Search bar can't be empty",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), SearchResults.class);
                    intent.putExtra("SEARCH_TEXT",searchText);
                    startActivity(intent);
                }


            }
        });


    }

    public void createMenuGrid()
    {
        mRecyclerView = findViewById(R.id.categoryRecyclerView);
        titles = new ArrayList<>();
        mImages = new ArrayList<>();

        mImages.add(R.drawable.chicken);
        mImages.add(R.drawable.beef);
        mImages.add(R.drawable.seafood);
        mImages.add(R.drawable.pork);
        mImages.add(R.drawable.vegetarian);
        mImages.add(R.drawable.dessert);

        titles.add("Chicken");
        titles.add("Beef");
        titles.add("Seafood");
        titles.add("Pork");
        titles.add("Vegetarian");
        titles.add("Dessert");

        adapter = new MyAdapter(this,titles,mImages);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(adapter);
    }

}