package com.example.egebe.worldcup2018;

import android.support.annotation.NonNull;

public class TeamPosition implements  Comparable<TeamPosition> {

    private Team positionedTeam;
    private int numberOfMatch;
    private int numberOfWin;
    private int numberOfDraw;
    private int numberOfLose;
    private int totalGoalScored;
    private int totalGoalConceded;
    private int totalAverage;
    private int totalPoint;

    public TeamPosition(Team positionedTeam, int numberOfMatch, int numberOfWin, int numberOfDraw, int numberOfLose, int totalGoalScored, int totalGoalConceded, int totalAverage, int totalPoint) {
        this.positionedTeam = positionedTeam;
        this.numberOfMatch = numberOfMatch;
        this.numberOfWin = numberOfWin;
        this.numberOfDraw = numberOfDraw;
        this.numberOfLose = numberOfLose;
        this.totalGoalScored = totalGoalScored;
        this.totalGoalConceded = totalGoalConceded;
        this.totalAverage = totalAverage;
        this.totalPoint = totalPoint;
    }

    public Team getPositionedTeam() {
        return positionedTeam;
    }

    public void setPositionedTeam(Team positionedTeam) {
        this.positionedTeam = positionedTeam;
    }

    public int getNumberOfMatch() {
        return numberOfMatch;
    }

    public void setNumberOfMatch(int numberOfMatch) {
        this.numberOfMatch = numberOfMatch;
    }

    public int getNumberOfWin() {
        return numberOfWin;
    }

    public void setNumberOfWin(int numberOfWin) {
        this.numberOfWin = numberOfWin;
    }

    public int getNumberOfDraw() {
        return numberOfDraw;
    }

    public void setNumberOfDraw(int numberOfDraw) {
        this.numberOfDraw = numberOfDraw;
    }

    public int getNumberOfLose() {
        return numberOfLose;
    }

    public void setNumberOfLose(int numberOfLose) {
        this.numberOfLose = numberOfLose;
    }

    public int getTotalGoalScored() {
        return totalGoalScored;
    }

    public void setTotalGoalScored(int totalGoalScored) {
        this.totalGoalScored = totalGoalScored;
    }

    public int getTotalGoalConceded() {
        return totalGoalConceded;
    }

    public void setTotalGoalConceded(int totalGoalConceded) {
        this.totalGoalConceded = totalGoalConceded;
    }

    public int getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(int totalAverage) {
        this.totalAverage = totalAverage;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    @Override
    public int compareTo(@NonNull TeamPosition o) {
        return Integer.compare(getTotalPoint(),o.getTotalPoint() );
    }
}
