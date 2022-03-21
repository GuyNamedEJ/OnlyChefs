package com.example.onlychefs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FoodListRecyclerAdapter extends RecyclerView.Adapter {
    public FoodListRecyclerAdapter(Context context, ArrayList<Recipe> recipes)
    {
        this.context = context;
        this.listOfRecipes = recipes;
    }

    private Context context;
    private ArrayList<Recipe> listOfRecipes;

   public static class FoodRowHolder extends RecyclerView.ViewHolder {
       public FoodRowHolder(View rowView){
           super(rowView);
           recipeRowTitle = rowView.findViewById(R.id.recipe_name);
           recipeThumbnail = rowView.findViewById(R.id.recipe_thumbnail_image);
       }
       public TextView recipeRowTitle;
       public ImageView recipeThumbnail;
   }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View myView = inflater.inflate(R.layout.recipe_view,parent,false);
        return new FoodRowHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        FoodRowHolder foodRowHolder = (FoodRowHolder) holder;
//        foodRowHolder.recipeRowTitle.setText(meals.getMeals().get(position).getStrMeal());
        foodRowHolder.recipeRowTitle.setText(listOfRecipes.get(position).getName());
        String url = listOfRecipes.get(position).getThumbnail();
        Glide.with(context).load(url).into(foodRowHolder.recipeThumbnail);

        TextView recipeItem = foodRowHolder.recipeRowTitle;
        recipeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // First, we create a new intent, specifying the class of the new activity
                // Simple MovieInfoActivity: Intent newMovieInfoActivity = new Intent(CatalogActivity.this, MovieInfoActivity.class);
                Intent newRecipeInfoActivity = new Intent(context, RecipeInfoActivity.class); // Calling a FancyMovieInfoActivity

                // We add Key-Value pairs that will reach the new activity
                // Simple MovieInfoActivity: newMovieInfoActivity.putExtra("SELECTED_MOVIE", tempMovie);
//                newRecipeInfoActivity.putExtra(RecipeInfoActivity.RECIPE_ARG, meals.getMeals().get(position)); // Adding the data to the fancy intent
                newRecipeInfoActivity.putExtra(RecipeInfoActivity.RECIPE_ARG,listOfRecipes.get(position));

                // Start the new activity
                // Simple MovieInfoActivity: startActivity(newMovieInfoActivity);
                context.startActivity(newRecipeInfoActivity); // Starting the fancy activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfRecipes.size();
    }
}
