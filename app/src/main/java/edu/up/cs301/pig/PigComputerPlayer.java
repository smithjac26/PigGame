package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.R;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    private Random RNG;
    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {

        super(name);
        RNG = new Random();
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof PigGameState)) {
            return;
        }

        PigGameState pigGameState = (PigGameState) info;
        if(pigGameState.getCurrentPlayer() != this.playerNum) {
            return;
        }
        if(RNG.nextBoolean()) {
            PigHoldAction pigHoldAction = new PigHoldAction(this);
            game.sendAction(pigHoldAction);
        }
        else {
            PigRollAction pigRollAction = new PigRollAction(this);
            game.sendAction(pigRollAction);
        }
    }//receiveInfo

}
