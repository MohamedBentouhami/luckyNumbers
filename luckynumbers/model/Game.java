/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56387.luckynumbers.model;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamed
 */
public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerGame;
    private Board[] boards;

    public Board[] getBoards() {
        return boards;
    }
    private Tile pickedTile;

    public Game() {

        this.state = State.NOT_STARTED;

    }

    @Override
    public void start(int playerCount) {

        if (this.state != State.NOT_STARTED && this.state != State.GAME_OVER) {
            System.out.println(getState());

            throw new IllegalStateException("The state must be Not_started or  game_Over");
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("the count player must be include 2 and 4 ");

        }
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];

        for (int i = 0; i < boards.length; i++) {
            boards[i] = new Board();

        }
        this.currentPlayerGame = 0;
        state = State.PICK_TILE;

    }

    @Override
    public int getBoardSize() {
        return 4;
    }

    @Override
    public Tile pickTile() {
        if (this.state != State.PICK_TILE) {
            
            throw new IllegalStateException("the state must be PICK_Tile");
        }

        int value = randomNumber(1, 20);
        
        Tile tile = new Tile(value);
        this.state = State.PLACE_TILE;
        this.pickedTile = tile;
        return tile;

    }
    
    /**
     * generate a random number between min and max
     * @param min minimal number
     * @param max maximum number
     * @return a set number between min and max
     */
        private int randomNumber ( int min,int max){
            Random rand = new Random();
            return  rand.nextInt(max-min+1)+min;
            
        }

    @Override
    public void putTile(Position pos) {
        if (this.state != State.PLACE_TILE) {
            throw new IllegalStateException("The state must be Place tile");

        }

        if (!boards[currentPlayerGame].isInside(pos) || !boards[currentPlayerGame].canBePut(pickedTile, pos)) {
            throw new IllegalArgumentException("Position outside the board");
        }
        boards[currentPlayerGame].put(pickedTile, pos);
        this.state = State.TURN_END;
        if (boards[currentPlayerGame].isFull()) {
            
            state = State.GAME_OVER;

        }

    }
    


    @Override
    public void nextPlayer() {
        if (state != State.TURN_END) {
            throw new IllegalStateException("The state must be Turn end");

        }

        currentPlayerGame++;
        if (currentPlayerGame == playerCount) {
            currentPlayerGame = 0;

        }

        this.state = State.PICK_TILE;
    }

    @Override
    public int getPlayerCount() {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("The state must be Not_started");
        }
        return this.playerCount;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (this.state == State.NOT_STARTED && this.state == State.GAME_OVER) {
            throw new IllegalStateException("the state must be Not_started or Game_over");
        }
        return currentPlayerGame;
    }

    @Override
    public Tile getPickedTile() {

        if (state != State.PLACE_TILE) {
            throw new IllegalStateException("the state must be Place_tile");
        }
        return this.pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {

        return boards[currentPlayerGame].isInside(pos);

    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (state != State.PLACE_TILE) {
            throw new IllegalArgumentException("the state muste be Place_Tile");
        }

        if (!isInside(pos)) {
            throw new IllegalArgumentException("the position is outside of the board");
        }

        return boards[currentPlayerGame].canBePut(pickedTile, pos);

    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("the state must be Not_start");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("the position is outside of the board");

        }

        if (boards[playerNumber] == null) {
            return null;
        }
        return boards[playerNumber].getTile(pos);
    }

    @Override
    public int getWinner() {
        if (state != State.GAME_OVER) {
            throw new IllegalStateException("the state must be Game_over");

        }

        

        return currentPlayerGame+1;
    }

    protected Tile pickTile(int value) {

        if (this.state != State.PICK_TILE) {
            throw new IllegalArgumentException("the state must be PICK_Tile");
        }

        Tile tile = new Tile(value);
        this.pickedTile = tile;
        this.state = State.PLACE_TILE;

        return tile;

    }

    public static void main(String[] args) {

        Game game = new Game();
        game.start(2);
        int value = 1;
        int line = 0;
        int col = 0;
        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickTile(value);
                game.putTile(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickTile(20);
        game.putTile(new Position(line, col));

    }

}
