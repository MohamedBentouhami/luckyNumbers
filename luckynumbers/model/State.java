/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56387.luckynumbers.model;

/**
 *
 * @author mohamed
 */
/**
 *
 *
 * PLACE_TILE TURN_END GAME_OVER
 *
 */
public enum State {
    /**
     * start state whose only possible action in the game is to apply the start
     * method
     */
    NOT_STARTED,
    /**
     * phase where the tile is drawn
     */
    PICK_TILE,
    /**
     * phase where we place a tile
     */
    PLACE_TILE,
    /**
     * phase where the change of player takes place
     */
    TURN_END,
    /**
     * state of end of the game
     */
    GAME_OVER;

}
