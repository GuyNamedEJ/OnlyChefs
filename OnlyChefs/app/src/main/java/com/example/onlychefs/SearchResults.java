package com.example.onlychefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {
    private RecyclerView recipeListRecyclerView;
    private ArrayList<Recipe> recipes;
    private Recipe myRecipe;

    private TextView searchTitle;
    private EditText searchBar;
    private Button searchButton;
    private String searchText;
    private Intent callingIntent;
    private String url;
    public static final String RECIPE_ARG = "SEARCH_TEXT";
    public static final String CATEGORY = "CATEGORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recipes = new ArrayList<Recipe>();

        searchTitle = findViewById(R.id.search_title);
        searchBar = findViewById(R.id.search_bar2);
        searchButton = findViewById(R.id.search_button2);

        callingIntent = getIntent();

            searchText = callingIntent.getStringExtra(SearchResults.RECIPE_ARG);
            url = "https://www.themealdb.com/api/json/v2/9973533/search.php?s=" + searchText;
            Log.i("URL","Search URL: " + url);
            Log.i("URL","Search Text: " + searchText);
            searchTitle.setText("Results for: " + searchText);
            searchForFood(url);




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText = searchBar.getText().toString();
                if(searchText.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Search bar can't be empty",Toast.LENGTH_LONG).show();
                }

                else{
                    searchText = searchBar.getText().toString();
                    searchText = searchText.replaceAll("\\s","%20");
                    String url = "https://www.themealdb.com/api/json/v2/9973533/search.php?s=" + searchText;
                    Log.i("URL","Search URL: " + url);
                    searchForFood(url);
                    searchText = searchText.replaceAll("\\b%20", " ");
                    searchTitle.setText("Results for: " + searchText);
                }


            }
        });


    }

    public void searchForFood(String url) {
        recipes = new ArrayList<Recipe>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Volley", response.toString());
                // Parse your JSON
                // Extract attributes and show them to the user as they expect (list of movies to RecyclerView, if it's a list)
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray mealArray = obj.getJSONArray("meals");
                    for (int i = 0; i < mealArray.length(); i++) {
                        JSONObject jsonRecipe = mealArray.getJSONObject(i);
                        String name = jsonRecipe.getString("strMeal");
                        String thumb = jsonRecipe.getString("strMealThumb");
                        Log.i("CookbookContent", "Recipe " + i + " from API: " + name);

                        String fullIngredient = "";

                        for (int j = 1; j < 21; j++) {
                            String measurement = "strMeasure" + (j);
                            String getMeasure = jsonRecipe.getString(measurement);
                            String ingredient = "strIngredient" + (j);
                            String getIngredient = jsonRecipe.getString(ingredient);

                            if (getMeasure.equals("") || getIngredient.equals("")) {
                                break;
                            } else {
                                fullIngredient = fullIngredient + getMeasure + " " + getIngredient + "\n";
                            }

                        }

                        myRecipe = new Recipe();
                        myRecipe.setName(jsonRecipe.getString("strMeal"));
                        myRecipe.setThumbnail(jsonRecipe.getString("strMealThumb"));
                        myRecipe.setId(jsonRecipe.getString("idMeal"));
                        myRecipe.setIngredients(fullIngredient);
                        myRecipe.setInstructions(jsonRecipe.getString("strInstructions"));
                        Log.i("CookbookContent", name + " Recipe Image url: " + myRecipe.getThumbnail());
                        Log.i("CookbookContent", name + " Ingredient List: " + myRecipe.getIngredients());
                        Log.i("CookbookContent", name + " Instructions: " + myRecipe.getInstructions());
                        Log.i("CookbookContent", "Adding " + name + " to search results");
                        recipes.add(myRecipe);
                        Log.i("CookbookContent", name + " Added to search results");
                        Log.i("CookbookContent", "Number of recipes added: " + recipes.size());
                    }
                    recipeListRecyclerView = findViewById(R.id.recycler_recipe_list);
                    recipeListRecyclerView.setHasFixedSize(true);
                    recipeListRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResults.this));
                    recipeListRecyclerView.setAdapter(new FoodListRecyclerAdapter(SearchResults.this, recipes));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley", "Something went wrong...");
            }
        });

        // Let's make the request!
        requestQueue.add(stringRequest); // This is where we contact the API
    }

    public void searchForCategory(String url) {
        recipes = new ArrayList<Recipe>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Volley", response.toString());
                // Parse your JSON
                // Extract attributes and show them to the user as they expect (list of movies to RecyclerView, if it's a list)
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray mealArray = obj.getJSONArray("meals");
                    for (int i = 0; i < mealArray.length(); i++) {
                        JSONObject jsonRecipe = mealArray.getJSONObject(i);
                        String name = jsonRecipe.getString("strMeal");
                        String thumb = jsonRecipe.getString("strMealThumb");
                        Log.i("CookbookContent", "Recipe " + i + " from API: " + name);

//                        String fullIngredient = "";
//
//                        for (int j = 1; j < 21; j++) {
//                            String measurement = "strMeasure" + (j);
//                            String getMeasure = jsonRecipe.getString(measurement);
//                            String ingredient = "strIngredient" + (j);
//                            String getIngredient = jsonRecipe.getString(ingredient);
//
//                            if (getMeasure.equals("") || getIngredient.equals("")) {
//                                break;
//                            } else {
//                                fullIngredient = fullIngredient + getMeasure + " " + getIngredient + "\n";
//                            }
//
//                        }

                        myRecipe = new Recipe();
                        myRecipe.setName(jsonRecipe.getString("strMeal"));
                        myRecipe.setThumbnail(jsonRecipe.getString("strMealThumb"));
                        myRecipe.setId(jsonRecipe.getString("idMeal"));
//                        myRecipe.setIngredients(fullIngredient);
//                        myRecipe.setInstructions(jsonRecipe.getString("strInstructions"));
                        Log.i("CookbookContent", name + " Recipe Image url: " + myRecipe.getThumbnail());
                        Log.i("CookbookContent", name + " Ingredient List: " + myRecipe.getIngredients());
                        Log.i("CookbookContent", name + " Instructions: " + myRecipe.getInstructions());
                        Log.i("CookbookContent", "Adding " + name + " to search results");
                        recipes.add(myRecipe);
                        Log.i("CookbookContent", name + " Added to search results");
                        Log.i("CookbookContent", "Number of recipes added: " + recipes.size());
                    }
                    recipeListRecyclerView = findViewById(R.id.recycler_recipe_list);
                    recipeListRecyclerView.setHasFixedSize(true);
                    recipeListRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResults.this));
                    recipeListRecyclerView.setAdapter(new FoodListRecyclerAdapter(SearchResults.this, recipes));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley", "Something went wrong...");
            }
        });

        // Let's make the request!
        requestQueue.add(stringRequest); // This is where we contact the API
    }
}