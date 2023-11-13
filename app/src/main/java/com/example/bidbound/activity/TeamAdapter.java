package com.example.bidbound.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bidbound.R;
import com.example.bidbound.entities.Team;

import java.util.List;

// TeamAdapter.java
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<Team> teamList;

    public TeamAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }
    public void setTeams(List<Team> teams) {
        this.teamList = teams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.teamNameTextView.setText(team.getTeamName());
        holder.expertiseTextView.setText(team.getExpertise());
        holder.teamMessageTextView.setText(team.getTeamMessage());
        holder.teamStatusTextView.setText(team.getTeamStatus());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamNameTextView;
        TextView expertiseTextView;
        TextView teamMessageTextView;
        TextView teamStatusTextView;
        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamNameTextView = itemView.findViewById(R.id.teamNameTextView);
            expertiseTextView = itemView.findViewById(R.id.expertiseTextView);
            teamMessageTextView = itemView.findViewById(R.id.teamMessageTextView);
            teamStatusTextView = itemView.findViewById(R.id.teamStatusTextView);

        }
    }
}
