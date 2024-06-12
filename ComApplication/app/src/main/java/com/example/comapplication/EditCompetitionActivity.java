package com.example.comapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comapplication.entity.Competition;
import com.example.comapplication.dao.CompetitionDao;

public class EditCompetitionActivity extends AppCompatActivity {

    private EditText editTextDescription, editTextDateDebut, editTextDateFin, editTextNombreParticipants;
    private Button buttonSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_competition);

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDateDebut = findViewById(R.id.editTextDateDebut);
        editTextDateFin = findViewById(R.id.editTextDateFin);
        editTextNombreParticipants = findViewById(R.id.editTextNombreParticipants);
        buttonSaveChanges = findViewById(R.id.buttonUpdate);

        // Récupérer l'ID de la compétition depuis l'Intent
      Competition competition = (Competition) getIntent().getSerializableExtra("competition");

        // Charger les données de la compétition depuis la base de données


        // Afficher les données existantes de la compétition dans les EditText
        if (competition != null) {
            editTextDescription.setText(competition.getDescription());
            editTextDateDebut.setText(competition.getDatededebut());
            editTextDateFin.setText(competition.getDatedefin());

        }

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les valeurs modifiées
                String description = editTextDescription.getText().toString().trim();
                String dateDebut = editTextDateDebut.getText().toString().trim();
                String dateFin = editTextDateFin.getText().toString().trim();
                int nombreParticipants = Integer.parseInt(editTextNombreParticipants.getText().toString().trim());

                // Valider les champs et mettre à jour la compétition dans la base de données
                competition.setDescription(description);
                competition.setDatededebut(dateDebut);
                competition.setDatedefin(dateFin);


                CompetitionDao.updateCompetition(competition);

                // Retourner le résultat à l'activité précédente si nécessaire
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}