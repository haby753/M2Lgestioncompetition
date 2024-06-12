package com.example.comapplication;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comapplication.dao.CompetitionDao;
import com.example.comapplication.entity.Competition;
import com.example.comapplication.entity.User;

import java.util.List;

public class ShowCompetitionAdapter  extends RecyclerView.Adapter<ShowCompetitionAdapter.CompetitionViewHolder>{

    private  List<Competition> competitionList;
    private final Context context;
    private final ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher;
  private User user ;
    public ShowCompetitionAdapter(List<Competition> competitionList, Context context, ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher , User user) {
       this.competitionList = competitionList;
        this.context = context;
        this.editCompetitionActivityResultLauncher = editCompetitionActivityResultLauncher;
        this.user = user ;
    }

    @NonNull
    @Override
    public ShowCompetitionAdapter.CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // if (role du user)
        View view = LayoutInflater.from(context).inflate(R.layout.competition_show_item, parent, false);
        return new ShowCompetitionAdapter.CompetitionViewHolder(view, this.editCompetitionActivityResultLauncher);
    }



    @Override
    public void onBindViewHolder(ShowCompetitionAdapter.CompetitionViewHolder holder, int position) {
        Competition competition = competitionList.get(position);
        holder.textViewDescription.setText(competition.getDescription());
        holder.textViewDateDebut.setText(competition.getDatededebut());
        holder.textViewDateFin.setText(competition.getDatedefin());
        holder.textViewNombreParticipants.setText(String.valueOf(competition.getNombredeparticipant()));
    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }

    public void setCompetitionList(List<Competition> updatedCompetitionList) {
        competitionList = updatedCompetitionList;
    }

    class CompetitionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDescription, textViewDateDebut, textViewDateFin, textViewNombreParticipants;
        ImageView  imageViewShow;
        ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher;

        CompetitionViewHolder(View itemView, ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewCompetitionDescription);
            textViewDateDebut = itemView.findViewById(R.id.textViewDateDebut);
            textViewDateFin = itemView.findViewById(R.id.textViewDateFin);
            textViewNombreParticipants = itemView.findViewById(R.id.textViewNombreParticipants);
            imageViewShow = itemView.findViewById(R.id.imageViewShow);
            Log.i("id",  String.valueOf(user.getId()));
            this.editCompetitionActivityResultLauncher = editCompetitionActivityResultLauncher;



            imageViewShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    Competition competitionToEdit = competitionList.get(position);

                    Intent editIntent = new Intent(context, ShowCompetitionActivity.class);
                    editIntent.putExtra("competition", competitionToEdit);
                    editIntent.putExtra("user1", user);
                    editCompetitionActivityResultLauncher.launch(editIntent);
                }
            });
        }
    }
}



