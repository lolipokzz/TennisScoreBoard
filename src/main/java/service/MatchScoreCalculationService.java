package service;

import model.MatchScore;

import java.util.UUID;

public class MatchScoreCalculationService {
    private final CurrentMatchesService currentMatchesService = new CurrentMatchesService();
    public MatchScore calculateMatchScore(int score, UUID uuid) {
        MatchScore matchScore = currentMatchesService.findMatchScoreByUuid(uuid);
        calculatePoints(matchScore, score);
        calculateGames(matchScore);
        calculateSet(matchScore);
        checkMatchStatus(matchScore);
        return matchScore;
    }


    private static void calculatePoints(MatchScore matchScore, int score) {
        if (matchScore.getFirstPlayerScore().getGames() == 6 && matchScore.getSecondPlayerScore().getGames() == 6) {
            calculateTieBreak(matchScore, score);
        }else if (score ==0) {
            matchScore.getFirstPlayerScore().setPoints(matchScore.getFirstPlayerScore().getPoints()+15);
            if (matchScore.getFirstPlayerScore().getPoints() == 45) {
                matchScore.getFirstPlayerScore().setPoints(40);
            }
        }else if (score ==1) {
            matchScore.getSecondPlayerScore().setPoints(matchScore.getSecondPlayerScore().getPoints()+15);
            if (matchScore.getSecondPlayerScore().getPoints() == 45) {
                matchScore.getSecondPlayerScore().setPoints(40);
            }
        }



    }


    private static void calculateGames(MatchScore matchScore) {
        if (matchScore.getFirstPlayerScore().getPoints() == 70 && matchScore.getSecondPlayerScore().getPoints()==40){
            matchScore.getFirstPlayerScore().setPoints(0);
            matchScore.getSecondPlayerScore().setPoints(0);
            matchScore.getFirstPlayerScore().setGames(matchScore.getFirstPlayerScore().getGames()+1);
        }
        if (matchScore.getFirstPlayerScore().getPoints()==40 && matchScore.getSecondPlayerScore().getPoints()==70){
            matchScore.getFirstPlayerScore().setPoints(0);
            matchScore.getSecondPlayerScore().setPoints(0);
            matchScore.getSecondPlayerScore().setGames(matchScore.getSecondPlayerScore().getGames()+1);
        }
        if (matchScore.getFirstPlayerScore().getPoints() == 55 && matchScore.getSecondPlayerScore().getPoints() ==55){
            matchScore.getFirstPlayerScore().setPoints(40);
            matchScore.getSecondPlayerScore().setPoints(40);
        }
        if (matchScore.getFirstPlayerScore().getPoints() == 55 && matchScore.getSecondPlayerScore().getPoints() != 40) {
            matchScore.getFirstPlayerScore().setPoints(0);
            matchScore.getSecondPlayerScore().setPoints(0);
            matchScore.getFirstPlayerScore().setGames(matchScore.getFirstPlayerScore().getGames()+1);
        }
        if (matchScore.getSecondPlayerScore().getPoints() == 55 && matchScore.getFirstPlayerScore().getPoints() != 40) {
            matchScore.getSecondPlayerScore().setPoints(0);
            matchScore.getFirstPlayerScore().setPoints(0);
            matchScore.getSecondPlayerScore().setGames(matchScore.getSecondPlayerScore().getGames()+1);
        }
    }


    private static void calculateSet(MatchScore matchScore) {
        if (matchScore.getFirstPlayerScore().getGames() == 6 && matchScore.getFirstPlayerScore().getGames()-matchScore.getSecondPlayerScore().getGames()>=2) {
            matchScore.getFirstPlayerScore().setSet(matchScore.getFirstPlayerScore().getSet()+1);
            matchScore.getFirstPlayerScore().setGames(0);
            matchScore.getSecondPlayerScore().setGames(0);
        }
        if (matchScore.getSecondPlayerScore().getGames() == 6 && matchScore.getSecondPlayerScore().getGames()-matchScore.getFirstPlayerScore().getGames()>=2) {
            matchScore.getSecondPlayerScore().setSet(matchScore.getSecondPlayerScore().getSet()+1);
            matchScore.getFirstPlayerScore().setGames(0);
            matchScore.getSecondPlayerScore().setGames(0);
        }
        if(matchScore.getFirstPlayerScore().getGames() == 7) {
            matchScore.getFirstPlayerScore().setSet(matchScore.getFirstPlayerScore().getSet()+1);
            matchScore.getFirstPlayerScore().setGames(0);
            matchScore.getSecondPlayerScore().setGames(0);
        }
        if (matchScore.getSecondPlayerScore().getGames() == 7) {
            matchScore.getSecondPlayerScore().setSet(matchScore.getSecondPlayerScore().getSet()+1);
            matchScore.getFirstPlayerScore().setGames(0);
            matchScore.getSecondPlayerScore().setGames(0);
        }
    }


    private static void checkMatchStatus(MatchScore matchScore) {
        if (matchScore.getFirstPlayerScore().getSet() == 2 || matchScore.getSecondPlayerScore().getSet() == 2) {
            matchScore.setMatchEnded(true);
        }
    }


    private static void calculateTieBreak(MatchScore matchScore, int score) {
        if(score == 0){
            matchScore.getFirstPlayerScore().setPoints(matchScore.getFirstPlayerScore().getPoints()+1);
            if (matchScore.getFirstPlayerScore().getPoints()>6 && matchScore.getFirstPlayerScore().getPoints()-matchScore.getSecondPlayerScore().getPoints()>=2) {
                matchScore.getSecondPlayerScore().setPoints(0);
                matchScore.getFirstPlayerScore().setPoints(0);
                matchScore.getFirstPlayerScore().setGames(matchScore.getFirstPlayerScore().getGames()+1);
            }else if (matchScore.getFirstPlayerScore().getPoints() == 7 && matchScore.getFirstPlayerScore().getPoints()-matchScore.getSecondPlayerScore().getPoints()>=2) {
                matchScore.getSecondPlayerScore().setPoints(0);
                matchScore.getFirstPlayerScore().setPoints(0);
                matchScore.getFirstPlayerScore().setGames(matchScore.getFirstPlayerScore().getGames()+1);
            }
        }

        if (score ==1) {
            matchScore.getSecondPlayerScore().setPoints(matchScore.getSecondPlayerScore().getPoints()+1);
            if(matchScore.getSecondPlayerScore().getPoints()> 6 && matchScore.getSecondPlayerScore().getPoints()-matchScore.getFirstPlayerScore().getPoints()>=2) {
                matchScore.getSecondPlayerScore().setPoints(0);
                matchScore.getFirstPlayerScore().setPoints(0);
                matchScore.getSecondPlayerScore().setGames(matchScore.getSecondPlayerScore().getGames()+1);
            }else if(matchScore.getSecondPlayerScore().getPoints() == 7  && matchScore.getSecondPlayerScore().getPoints()-matchScore.getFirstPlayerScore().getPoints()>=2 ) {
                matchScore.getSecondPlayerScore().setPoints(0);
                matchScore.getFirstPlayerScore().setPoints(0);
                matchScore.getSecondPlayerScore().setGames(matchScore.getSecondPlayerScore().getGames()+1);
            }
        }
    }
}
