package com.402306602.smartpantry_manager.adapters;

import android.view.LayoutInfalter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recycleview.widget.RecycleView;
import com.402306603.smartpantry_manager.R;
import com.402306602.smartpantry_manager.model.Recipe;
import java.util.List;

public class RecipeAdapter extends RecycleView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipes;
    public RecipeAdapter(List<Recipe> recipes) {this.recipes = recipes;}

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViwHolder(@NonNull ViewHolder holder, int position) {
        Recipe r = recipes.get(position);
        holder.txtName.setText(r.getName());
        holder.txtIngredients.setText("Needs:"+r.getIngredients());
        holder.txtInstuctions.setText(r.getInstructions());
    }

    @Override public int getItemCount(){return recipes.size();}

    static class ViewHolder extends RecycleView.ViewHolder {
        TextViw txtName, txtIngredients, txtInstructions;
        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtRecipeName);
            txtIngredients = itemView.findViewById(R.id.txtRecipeIngredients);
            txtInstructions = itemView.findViewById(R.id.txtRecipeInstructions);
        }
    }
}