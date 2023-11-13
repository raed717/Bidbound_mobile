package com.example.bidbound.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

import kotlin.jvm.Transient;

@Entity(tableName = "team")
public class Team {
    @PrimaryKey(autoGenerate = true)
    private int gid;
    @ColumnInfo(name = "team_name")
    private String teamName;
    @ColumnInfo(name = "team_expertise")
    private String expertise;
    @ColumnInfo(name = "team_project")
    private String teamProject;
    @ColumnInfo(name = "team_message")
    private String teamMessage;
    @ColumnInfo(name = "team_status")
    private String teamStatus;
    @ColumnInfo(name = "team_conflict")
    private String conflict;
    public Team() {
    }

   /* public Team(String teamName, String teamMessage, String teamStatus) {
        this.teamName = teamName;
        this.teamMessage = teamMessage;
        this.teamStatus = teamStatus;
    }

    public Team(String teamName, String expertise, String teamMessage, String teamStatus) {
        this.teamName = teamName;
        this.expertise = expertise;
        this.teamMessage = teamMessage;
        this.teamStatus = teamStatus;
    }
*/
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamProject() {
        return teamProject;
    }

    public void setTeamProject(String teamProject) {
        this.teamProject = teamProject;
    }

    public String getTeamMessage() {
        return teamMessage;
    }

    public void setTeamMessage(String teamMessage) {
        this.teamMessage = teamMessage;
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getConflict() {
        return conflict;
    }

    public void setConflict(String conflict) {
        this.conflict = conflict;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "Team{" +
                "gid=" + gid +
                ", teamName='" + teamName + '\'' +
                ", teamProject='" + teamProject + '\'' +
                ", teamMessage='" + teamMessage + '\'' +
                ", teamStatus='" + teamStatus + '\'' +
                '}';
    }


}
