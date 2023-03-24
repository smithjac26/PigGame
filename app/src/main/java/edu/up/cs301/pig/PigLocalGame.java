package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */

public class PigLocalGame extends LocalGame {

    public PigGameState pigGameState;
    public PigLocalGame() {
        pigGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == pigGameState.getCurrentPlayer();
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        int currPlayer = pigGameState.getCurrentPlayer();
        if(action instanceof PigHoldAction) {
            if(currPlayer == 0) {
                pigGameState.setPlayer0Score(pigGameState.getPlayer0Score()+pigGameState.getRunningTotal());
                pigGameState.setCurrentPlayer(1);
            }
            else {
                pigGameState.setPlayer1Score(pigGameState.getPlayer1Score()+pigGameState.getRunningTotal());
                pigGameState.setCurrentPlayer(0);
            }
            pigGameState.setRunningTotal(0);
            return true;
        }
        else if(action instanceof PigRollAction) {
            Random RNG = new Random();
            int dieRoll = RNG.nextInt(6) + 1;
            pigGameState.setDieValue(dieRoll);
            if(pigGameState.getDieValue()==1) {
                pigGameState.setRunningTotal(0);
                if(pigGameState.getCurrentPlayer() == 1) {
                    pigGameState.setCurrentPlayer(0);
                }
                else {
                    pigGameState.setCurrentPlayer(1);
                }
            }
            else {
                pigGameState.setRunningTotal(pigGameState.getRunningTotal() + pigGameState.getDieValue());
            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState currGameState = new PigGameState(pigGameState);
        p.sendInfo(currGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if(pigGameState.getPlayer0Score() > 49) {
            return ("Congrats, the first player won" + pigGameState.getPlayer0Score() + " points");
        }
        else if(pigGameState.getPlayer1Score() > 49) {
            return ("Congrats, the second player won with " + pigGameState.getPlayer1Score() + " points");
        }
        return null;
    }

}// class PigLocalGame
