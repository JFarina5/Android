package com.example.jfarina.easypill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jfarina.easypill.model.and.db.directory.DatabaseHandler;
import com.example.jfarina.easypill.model.and.db.directory.MedModel;

public class AddMedicationActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);
    }

   public void touchAddMedication(View view) {
        MedModel medModel = new MedModel();
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        EditText txtMedicationName = findViewById(R.id.txtMedicationName);
        EditText txtFrequency = findViewById(R.id.txtFrequency);
        EditText txtHowManyDays = findViewById(R.id.txtHowManyDays);
        EditText txtNotes = findViewById(R.id.txtNotes);
        EditText txtDosage = findViewById(R.id.txtDosage);
        EditText txtInitial = findViewById(R.id.txtInitial);
        medModel.setMedName(txtMedicationName.getText().toString());
        medModel.setFrequency(Integer.parseInt(txtFrequency.getText().toString()));
        medModel.setNumDays(Integer.parseInt(txtHowManyDays.getText().toString()));
        medModel.setNotes(txtNotes.getText().toString());
        medModel.setDosage(txtDosage.getText().toString());
        medModel.setInitialAmount(txtInitial.getText().toString());
        Boolean isAdded = databaseHandler.insertMed(medModel);
        if (isAdded) {
            Toast.makeText(AddMedicationActivityController.this, "Medication is registered successfully", Toast.LENGTH_LONG).show();
            Log.d("Insertion", "med data  is inserted  successfully");
            //An intent is a messaging object you can use to request an action from another app component.
            Intent intent = new Intent(this, HomePageActivityController.class);
            startActivity(intent);
        }
    }


}
