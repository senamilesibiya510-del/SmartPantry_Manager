package com.402306602.smartpantry_manager.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recycleview.widget.LinearLayoutManager;
import android.recycleview.widget.RecycleView;

import com.PantryItem;

import com.402306602.smartpantry_manager.R;
import com.402306602.smartpantry_manager.adapters.RecipeAdapter;
import com.402306602.smartpantry_manager.database.DatabaseHelper;
import com.402306602.smartpantry_manager.models.PantryItem;
import com.402306602.smartpantry_manager.models.Recipe;
import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecycleView recycleView;
    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        recycleView = findViewById(R.id.recycleViewRecipes) {
            txtInfo = findViewById(R.id.txtRecipeInfo);
            recycleView.setLayoutManger(new LinearLayoutManager(this));

            DatabaseHelper db = new DatabaseHelper(this);
            List<PantryItem> pantryItems = db.getExpiryItem();

            List<Recipe> suggested = generateRecipe(pantryItems);

            if(suggested.isEmpty()) {
                txtInfo.setText("No expiring items. All good! Pantry has" + db.getAllPantryItems().size()+"item.");

            } else {
                txtInfo.setText("Suggested recipes using" + pantryItems.size() + "expiring items: ")

            }

            RecipeAdapter adapter = new RecipeAdapter(suggested);
            recyleView.setAdapter(adapter);

        }

        private List<Recipe> generateRecipe(List<PantryItem> expiring ) {

            List<Recipe> recipes = new ArrayList<>();

            if (expiring.isEmpty()) return recipes;

            for(PantryItem item: expiring) {
                String nameLower = item.getName().toLowerCase();
                if(nameLower.contains("Tomato") || nameLower.contains(onion)) {
                    recipes.add(new Recipe("Quick Tomato Stew", "Tomato, Onion, Oil","Fry onion, add tomato, simmer 10 min."));

                } else if(nameLower.contains("milk")|| nameLower.contains("eggs")) {
                    recipes.add(new Recipe("Simple Omelette", "Eggs, Milk, Salt", "Beat eggs with milk, fry in pan."));

                }else if(nameLower.contains("bread")) {
                    recipes.add(new Recipe("French Toast", "Bread, Eggs, Milk", "Dip breadin egg/milk mix, fry."));

                } else {
                    recipes.add(new Recipe("Use up: " + item.getName(), item.getName(), "Use"+item.getName()+"before"+item.getExpiryDate()+"to avoid waste.");
                }
            }
            return recipes;
        }
    }
}
