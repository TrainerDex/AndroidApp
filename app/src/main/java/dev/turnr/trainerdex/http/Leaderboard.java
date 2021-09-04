package dev.turnr.trainerdex.http;

import java.time.ZonedDateTime;

public class Leaderboard {
    protected String title;

    public Leaderboard(String generated, String stat, String title, String aggregations, String leaderboard) {
        this.title = title;
    }

    // no-arg constructor, getters, and setters
}

public class LeaderboardAggregations {
    protected Number avg;
    protected int count;
    protected int min;
    protected int max;
    protected int sum;

    public LeaderboardAggregations(Number avg, int count, int min, int max, int sum) {
        this.avg = avg;
        this.count = count;
        this.min = min;
        this.max = max;
        this.sum = sum;
    }

    // no-arg constructor, getters, and setters
}