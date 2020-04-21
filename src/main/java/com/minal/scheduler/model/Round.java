package com.minal.scheduler.model;

import java.util.Collection;

public class Round {
    private int roundId;
    private String competency;

    public Round(int roundId, String competency) {
        this.roundId = roundId;
        this.competency = competency;
    }

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId +
                ", competency='" + competency + '\'' +
                '}';
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }

}
