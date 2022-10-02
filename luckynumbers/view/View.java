/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56387.luckynumbers.view;

import g56387.luckynumbers.model.Position;

/**
 *
 * @author mohamed
 */
public interface View {

    /**
     * display at the beginning of the game: name of the game, author,
     * version...
     */
    void displayWelcome();

    /**
     * display of the game status: current player, his board and tile to place
     */
    void displayGame();

    /*
      displays the number of the winner player
     */
    void displayWinner();

    /**
     * asks for the number of players in the game from 2 to 4.
     *
     * @return the number of players
     */
    int askPlayerCount();

    /**
     * Asks the user to enter the row and column number and returns them as a
     * position
     *
     * @return a position
     */
    Position askPosition();

    /**
     * displays an error message
     *
     * @param message
     */
    void displayError(String message);

}
