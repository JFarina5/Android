package com.example.jfarina.easypill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jfarina.easypill.model.and.db.directory.DatabaseHandler;
import com.example.jfarina.easypill.model.and.db.directory.MedModel;

import java.util.ArrayList;
import java.util.List;

public class MedicationHistoryActivityController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_history);

        final ArrayList<String> medicationName = new ArrayList<>();
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        final List<MedModel> medicationList = dbHandler.getAllMedications();
        for (MedModel med : medicationList) {
            medicationName.add
                    ("Medication Name: " + med.getMedName() + " \n" +
                            "Frequency: " + med.getFrequency() + " per day\n" +
                            "Number of Days: " + med.getNumDays() + " \n" +
                            "Notes: " + med.getNotes() + " \n" +
                            "Dosage: " + med.getDosage());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, medicationName);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}

