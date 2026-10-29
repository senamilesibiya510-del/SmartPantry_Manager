package com.402306602.smartpantry_manager.adapters;

import android.content.Contexr;
import android.content.intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androifx.recycleview.widget.RecycleView;
import com.402306602.smartpantry_manager.activities.AddEditIngrediantActivity;
import com.402306602.smartpantry_manager.models.PantryItem;
import java.util.List;

public class PantryAdapter extends RecycleView.Adapter<PantryAdapter.PantryViewHolder>{

    private Context context;
    private List<PantryItem> itemList;

    public PantryAdapter(Context context, List<PantryItem> itemList) {
        This.context = context;
        This.itemList = itemList;
    }

    @NonNull
    @Override
    Public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pantry, parent, false);
        Return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = itemList.get(position);
        Holder.txtName.setText(item.getName());
        Holder.txtQty.setText(item.getQuantity()+""+item.getUnit());
        Holder.txtExpiry.setText("Exp:"+item.getExpiryDate());

        Holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, AddEditIngredientsActivit.class);
            Intent.putExtra("ITEM_ID",item.getId);
        });
    }

    @Override
    public int getItemCount() {
        Return itemList.size();
    }

    public static class PantryViewHolder extends RecycleView.ViewHolder {
        TextView txtName, txtQty, txtExpiry;

        public PantryViewHolder(@NonNull View itemView) {
            Super(itemView);
            txtName = itemView.fineViewById(R.id.txtItemName);
            txtQty = itemView.FindViewById(R.id.txtItemQty);
            txtExpiry = itemView.FindViewById(R.id.txtItemExpiry);
        }
    }
}


