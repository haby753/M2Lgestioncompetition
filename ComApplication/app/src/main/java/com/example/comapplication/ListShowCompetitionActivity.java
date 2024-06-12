package com.example.comapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comapplication.dao.CompetitionDao;
import com.example.comapplication.entity.Competition;
import com.example.comapplication.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ListShowCompetitionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    private ShowCompetitionAdapter showcompetitionAdapter;
    private List<Competition> competitionList;
    public static final int EDIT_COMPETITION_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_list_show_competition);

        recyclerView = findViewById(R.id.recyclerViewS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser la liste des compétitions (cela pourrait venir d'une base de données)
        competitionList = new ArrayList<>();
        competitionList = CompetitionDao.findAllCompetitions();

        User user = (User) getIntent().getSerializableExtra("user");

        showcompetitionAdapter = new ShowCompetitionAdapter(competitionList, this, editCompetitionActivityResultLauncher ,user);
        recyclerView.setAdapter(showcompetitionAdapter);


    }


    private final ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Le code ici sera exécuté si le résultat de EditCompetitionActivity est RESULT_OK
                    // Par exemple, recharger les données de la compétition et mettre à jour le RecyclerView
                    reloadCompetitionData();
                }
            });

    private void reloadCompetitionData() {
        List<Competition> updatedCompetitionList = CompetitionDao.findAllCompetitions();
        showcompetitionAdapter.setCompetitionList(updatedCompetitionList);
        showcompetitionAdapter.notifyDataSetChanged();
    }
}