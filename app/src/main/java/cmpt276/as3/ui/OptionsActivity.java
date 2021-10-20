package cmpt276.as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import cmpt276.as3.R;
import cmpt276.as3.model.Options;

/*
    This activity represents the Options menu.
    It allows the user to adjust the grid size and the amount of mushrooms that appear.
 */
public class OptionsActivity extends AppCompatActivity {
    private ImageButton backButton;
    private Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        options = Options.getInstance();

        Spinner spinnerGrid = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grid_sizes, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerGrid.setAdapter(adapter);

        Spinner spinnerMushrooms = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.num_mushrooms, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerMushrooms.setAdapter(adapter2);

        spinnerGrid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onGridSizeSelected(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });

        spinnerMushrooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onNumMushroomsSelected(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });

        // get the string representing the grid size
        String selectedGridSize = options.getRowSize() + " x " + options.getColumnSize();
        // get array of grid size values from the resources file
        String[] gridSizeOptions = getResources().getStringArray(R.array.grid_sizes);
        // find the index of the selected grid size in the array, so we can set the selected value in the ui
        for (int i = 0; i < gridSizeOptions.length; i++) {
            if (gridSizeOptions[i].equals(selectedGridSize)) {
                // set the selected grid size by referring to the selected grid size's index in the array
                spinnerGrid.setSelection(i);
                break;
            }
        }

        String selectedMushrooms = String.valueOf(options.getNumMushrooms());
        String[] numMushroomsOptions = getResources().getStringArray(R.array.num_mushrooms);
        for (int i = 0; i < numMushroomsOptions.length; i++) {
            if (numMushroomsOptions[i].equals(selectedMushrooms)) {
                spinnerMushrooms.setSelection(i);
                break;
            }
        }

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((View view) -> openBackButton());
    }

    public void onGridSizeSelected(AdapterView<?> parent, View view, int pos, long id) {
        String gridVal = String.valueOf(parent.getItemAtPosition(pos)); // pos is index of array
        String[] gridParts = gridVal.split("x");
        String row = gridParts[0].trim();
        String col = gridParts[1].trim();

        options = Options.getInstance();
        options.setRowSize(Integer.parseInt(row));
        options.setColumnSize(Integer.parseInt(col));
    }

    public void onNumMushroomsSelected(AdapterView<?> parent, View view, int pos, long id) {
        String numMushrooms = String.valueOf(parent.getItemAtPosition(pos)); // pos is index of array
        options = Options.getInstance();
        options.setNumMushrooms(Integer.parseInt(numMushrooms));
    }

    public void openBackButton() {
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}