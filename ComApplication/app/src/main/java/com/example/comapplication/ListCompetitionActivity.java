package com.example.comapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comapplication.entity.Competition;
import com.example.comapplication.dao.CompetitionDao;

import java.util.ArrayList;
import java.util.List;

public class ListCompetitionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CompetitionAdapter competitionAdapter;
    private List<Competition> competitionList;
    public static final int EDIT_COMPETITION_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_competition);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser la liste des compétitions (cela pourrait venir d'une base de données)
        competitionList = new ArrayList<>();
        competitionList = CompetitionDao.findAllCompetitions();

        competitionAdapter = new CompetitionAdapter(competitionList, this, editCompetitionActivityResultLauncher);
        recyclerView.setAdapter(competitionAdapter);
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
        competitionAdapter.setCompetitionList(updatedCompetitionList);
        competitionAdapter.notifyDataSetChanged();
    }

    public static class ShowCompetitionActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_show_competition);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}