 package com.402306602.smartpantry_manager.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActtivity;

 import com.PantryItem;
 import com.example.smartpantry_manager.R;

 import java.util.Calendar;

 import com.402306602.smartpantry_manager.R;
import com.402306602.smartpantry_manager.database.DatabaseHelper;
import com.402306603.smartpantry_manager.models.PantryItem;
iport java.util.Calendar;

public class AddEditIngredientsActivity extends AppCompatActivity {
    private EditText edtName, edtQty, edtExpiry;
    private Spinner spinnerUnit, spinnerCategory;
    private Button btnSave, btnDelete;
    private DatabaseHelper dbHelper;
    private int editingItemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        edtName = findViewById(R.id.edtIngredientName);
        edtQty = findViewById(R.id.edtQuantity);
        edtExpiry = findViewById(R.id.edtExpiry);
        spinnerUnit = findViewById(R.id.spinnerUnit);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnSaveIngredient);
        btnDelete = findViewById(R.id.btnDeleteIngredient);
        dbHelper = new DatabaseHelper(this);

        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown-item);
        spinnerUnit.setAdapter(unitAdapter);

        ArrayAdapter<CharSequence>catAdapter = ArrayAdapter.createFromResource(this,R.array.categories_array, android.R.layout.simple_spinner_item);
        catAdapter.setDropDownViewResource(abdroid.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(catAdapter);

        edtExpiry.setOnClickListener(v->showDatePicker());

        if(getIntent().hasExtra("ITEM_ID")) {
            editingItermId = getIntent().getIntExtra("ITEM_ID", -1);
            loadItemForEdit();
        } else {
            btnDetele.setEnabled(false);
        }

        btnSave.setOnClickListener(v-> saveItem());
        btnDetele.setOnClickListener(v-> deleteItem());
    }

    private void showDatePicker() {
        Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth)-> {
            String date = year + "-"+ String.format("%02d", month + 1) +"-" + String.format("%02d",dayOfMonth);
            edtExpiry.setText(date);
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void loadItemForEdit(){
        PantryItem item = dbHelper.getPantryItemById(editingItemId);
        if(item !=null) {
            edtName.setText(item.getName());
            edtQty.setText(String.valueOf(item.getQuantity()));
            edtExpiry.setText(item.getExpiryDate());

        }
    }

    private void saveItem() {
        String name = edtName.getText().toString().trim();
        String qtyStr = edtQty.getText().toString().trim();
        String expiry = edtExpiry.getText().toString().trim();
        String unit = spinnerUnit.getSelectedItem().toString();
        String category = spinnerCategory.getSelectedItem().toString();

        if (name.isEmpty()) {
            edtQty.setError("Quantity required");
            return;
        }

        double qty = Double.parseDouble(qtyStr);

        if(editingItemId ==-1) {
            PantryItem newItem = new PantryItem(name,qty, unit, expiry, category);
            dbHelper.addPantryItem(newItem);
            Toast.makeText(this, "Ingredient added", Toast.LENGTH_SHORT).show();
        } else {
            PantryItem updated = new PantryItem(editingItemId, name, qty, unit,expiry, category);
            dbHelper.updatePantryItem(updated);
            Toast.makeText(this, "Ingredients updated", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private  vvoid deleteItem() {
        if (editingItemId !=-1) {
            dbHelper.deletedPantryItem(editingItemId);
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}