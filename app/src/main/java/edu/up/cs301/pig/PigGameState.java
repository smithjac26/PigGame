package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {

    private int currentPlayer;
    private int player0Score;
    private int player1Score;
    private int runningTotal;
    private int dieValue;

    public PigGameState() {
        currentPlayer = 0;
        player0Score = 0;
        player1Score = 0;
        runningTotal = 0;
        dieValue = 0;
    }

    public PigGameState(PigGameState gameState) {
        currentPlayer = gameState.getCurrentPlayer();
        player0Score = gameState.getPlayer0Score();
        player1Score = gameState.getPlayer1Score();
        runningTotal = gameState.getRunningTotal();
        dieValue = gameState.getDieValue();
    }

    public int getCurrentPlayer() {return currentPlayer;}
    public int getPlayer0Score() {return player0Score;}
    public int getPlayer1Score() {return player1Score;}
    public int getRunningTotal() {return runningTotal;}
    public int getDieValue() {return dieValue;}

    public void setCurrentPlayer(int currPlayer) { currentPlayer = currPlayer;}
    public void setPlayer0Score(int score) {player0Score = score;}
    public void setPlayer1Score(int score) {player1Score = score;}
    public void setRunningTotal(int num) {runningTotal = num;}
    public void setDieValue(int val) {dieValue = val;}
}
