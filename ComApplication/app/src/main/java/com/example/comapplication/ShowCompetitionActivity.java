package com.example.comapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.comapplication.dao.InscriptionDao;
import com.example.comapplication.entity.Competition;
import com.example.comapplication.entity.Inscription;
import com.example.comapplication.entity.User;

public class ShowCompetitionActivity extends AppCompatActivity {

    private TextView editTextDescription, editTextDateDebut, editTextDateFin, editTextNombreParticipants;
    private Button buttonInscription;
    Competition competition ;
    User  user;
    Inscription inscription ;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_competition);

        editTextDescription = findViewById(R.id.editTextDescriptionS);
        editTextDateDebut = findViewById(R.id.editTextDateDebutS);
        editTextDateFin = findViewById(R.id.editTextDateFinS);
        editTextNombreParticipants = findViewById(R.id.editTextNombreParticipantsS);
        buttonInscription = findViewById(R.id.buttonInscriptionS);

        // Récupérer l'ID de la compétition depuis l'Intent
        competition = (Competition) getIntent().getSerializableExtra("competition");
        user = (User) getIntent().getSerializableExtra("user1");

        // Charger les données de la compétition depuis la base de données

        // Afficher les données existantes de la compétition dans les EditText
        if (competition != null) {
            editTextDescription.setText(competition.getDescription());
            editTextDateDebut.setText(competition.getDatededebut());
           editTextDateFin.setText(competition.getDatedefin());
            editTextNombreParticipants.setText(competition.getNombredeparticipant());

        }

         buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id_competition = editTextDescription.getText().toString().trim();
                String dateDebut = editTextDateDebut.getText().toString().trim();
                String dateFin = editTextDateFin.getText().toString().trim();
                String nombreParticipants = editTextNombreParticipants.getText().toString().trim();

                // Créer une nouvelle instance de Inscription

                inscription = new Inscription();
                inscription.setDate(dateDebut);
                inscription.setStatut(dateFin);

                inscription.setId_user(user);
                inscription.setId_competition(competition);
                 Long id =InscriptionDao.saveInscription(inscription);
                 if ( id != -1){
                     showAlert("Inscription réussie", "Vous êtes bien inscrit à la compétition.");
                 } else {
                     showAlert("Échec de l'inscription", "Une erreur s'est produite lors de l'inscription. Veuillez réessayer.");
                 }

                setResult(RESULT_OK);
                finish();
            }
     private void showAlert(String title, String message) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(ShowCompetitionActivity.this);
                 builder.setTitle(title);
                 builder.setMessage(message);
                 builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                     }
                 });
                 AlertDialog alert = builder.create();
                 alert.show();
             }
        });
    }



}