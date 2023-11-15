package com.zhangjian.开箱即用.Lambda表达式;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class Lambda {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Comparator<Score> comparator = (score1, score2) -> {
            double diff = score1.getScore() - score2.getScore();
            if (diff > 0) return 1;
            if (diff < 0) return -1;
            return 0;
        };

        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score(99.8));
        scores.add(new Score(101.1));
        scores.add(new Score(87.4));
        scores.add(new Score(93.5));

        scores.sort(comparator);

        scores.forEach(score -> {
            System.out.print(score.getScore() + " ");
        });
    }
}

class Score{
    private double score;

    public Score(double score) {
        this.score = score;
    }

    public double getScore(){
        return this.score;
    }
}
