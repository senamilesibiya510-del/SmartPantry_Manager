package com.402306602.smartpantry_manager.activities;


import android.content.intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.appcompat.app.AppCompatActivity;
import androidx.recycleview.widget.LinearLayoutManager;
import androidx.recycleview.widget.RecycleView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.402306602.smartpantry_manager.R;
import com.402306602.smartpantry_manager.adapters.PantryAdabpter;
import com.402306602.smartpantry_manager.models.PantryItem;
import java.util.List;

public class PantryListActivity extends AppCombatActivity {

    private RecycleView recycleView;
    private TextView textEmpty;
    private FloatingActionButton fabAdd;
    private DatabaseHelper  dbHelper;
    private PantryAdpter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        recycleView = findViewById(R.id.recyclePantry);
        txtEmpty = findViewById(R.id.txtEmptyPantry);
        fabAdd = findViewById(R.id.fabAddingredient);
        View btnSettings = findViewById(R.id.btnGoSettings);

        dbHelper = new DatabaseHelper(this);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        fabAdd.setOnClickListener(v->{
            Intent intent = new Intet(PantryListActivity.this,
                    AddEditingIngredientActivity.class);
            startActivity(intent);
        });

        btnSuggested.seOnClickListener(v->{
            Intent intent = new Intent(PantryListActivity.this,
                    SuggestRecipesAtivity.class);
            startActivity(intent);
        });

        btnSettings.setOnClickListener(v->{
            Intent intent = new Intent(PantryListActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResum() {
        Super.onResume();
        loadPantry();
    }

    private void loadPantry() {
        List<PantryItem> items = dbHelper.getAllPantryItems();

        if (items.isEmpty()) {
            recycleView.setVisibility(View.GONE);
            textEmpty.setVisibility(View.VISIBLE);
            txtEmpty.setText("Your pantry is empty. Tap + to add ingredients.");

        } else {
            recycleView.setVisibility(View.VISIBLE);
            txtEmpty.setVisibility(View.GONE);
            adapter = new PantryAdapter(this, items);
            recycleView.setAdapter(adapter);
        }
    }
}

