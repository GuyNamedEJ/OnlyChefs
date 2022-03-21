package com.example.onlychefs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RecipeInfoActivity extends AppCompatActivity {
    private Recipe myRecipe;

    public static final String RECIPE_ARG = "incomingRecipe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);

        Intent callingIntent = getIntent();

        Recipe selectedRecipe = (Recipe) callingIntent.getSerializableExtra(RecipeInfoActivity.RECIPE_ARG);

        TextView recipeName = findViewById(R.id.recipe_name_view);
        recipeName.setText(selectedRecipe.getName());

        TextView instructions = findViewById(R.id.recipe_instructions_view);
        instructions.setText(selectedRecipe.getInstructions());

        TextView ingredients = findViewById(R.id.recipe_ingredients_view);
        ingredients.setText(selectedRecipe.getIngredients());

        ImageView recipeImg = findViewById(R.id.recipe_thumbnail);
        String url = selectedRecipe.getThumbnail();
        Glide.with(this).load(url).into(recipeImg);

        Button quitButton = findViewById(R.id.quit_recipe_info);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}