package com.example.comapplication;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder> {

    private static List<Competition> competitionList;
    private final Context context;
    private final ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher;

    public CompetitionAdapter(List<Competition> competitionList, Context context, ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher) {
        CompetitionAdapter.competitionList = competitionList;
        this.context = context;
        this.editCompetitionActivityResultLauncher = editCompetitionActivityResultLauncher;
    }

    @NonNull
    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // if (role du user)
        View view = LayoutInflater.from(context).inflate(R.layout.competition_item, parent, false);
        return new CompetitionViewHolder(view, this.editCompetitionActivityResultLauncher);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, int position) {
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
        ImageView imageViewDelete, imageViewEdit;
        ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher;

        CompetitionViewHolder(View itemView, ActivityResultLauncher<Intent> editCompetitionActivityResultLauncher) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewCompetitionDescription);
            textViewDateDebut = itemView.findViewById(R.id.textViewDateDebut);
            textViewDateFin = itemView.findViewById(R.id.textViewDateFin);
            textViewNombreParticipants = itemView.findViewById(R.id.textViewNombreParticipants);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            this.editCompetitionActivityResultLauncher = editCompetitionActivityResultLauncher;

            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    Competition competition = competitionList.get(position);
                    CompetitionDao.deleteCompetition(competition.getId());
                    competitionList.remove(position);
                    notifyItemRemoved(position);
                }
            });

            imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    Competition competitionToEdit = competitionList.get(position);

                    Intent editIntent = new Intent(context, EditCompetitionActivity.class);
                    editIntent.putExtra("competition", competitionToEdit);
                    editCompetitionActivityResultLauncher.launch(editIntent);
                }
            });
        }
    }
}

