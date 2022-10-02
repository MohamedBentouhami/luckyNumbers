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
public class Board {

    private Tile[][] tiles;

    /**
     * creates an instance of the array class and creates an empty array of
     * Tiles
     */
    public Board() {
        this.tiles = new Tile[4][4];

    }

    /**
     * Get the value of getSize
     *
     * @return the value of getSize
     */
    public int getSize() {
        return tiles.length;
    }

    /**
     * checks whether a given position is a array position or not
     *
     * @param pos a position of the board
     * @return true or false
     */
    public boolean isInside(Position pos) {

        if ((pos.getRow() < 0) || pos.getColumn() >= getSize()
                || pos.getRow() >= getSize() || pos.getColumn() < 0) {
            return false;
        }
        return true;

    }

    /**
     * gives the tile to the given position
     *
     * @param pos a position of the board
     * @return
     */
    public Tile getTile(Position pos) {

        return tiles[pos.getRow()][pos.getColumn()];

    }

    /**
     * indicates whether the given tile can be placed at the given position
     *
     * @param tile
     * @param pos a position of the board
     * @return
     */
    public boolean canBePut(Tile tile, Position pos) {

   

        return ligneBrowsing(pos, tile) && columnBrowsing(pos, tile);
    }

    /**
     * browse to the row in the 2D table that match to the current player's board
     * @param pos Position where the tile should be placed
     * @param tile Tile to be placed
     * @return true if the tile can be placed on the line according to the game rules
     */
    
    private boolean ligneBrowsing( Position pos, Tile tile) {
        for (int row = 0; row < getSize(); row++) {
   
            if (row < pos.getRow() && tiles[row][pos.getColumn()] != null
                    && tiles[row][pos.getColumn()].getValue() >= tile.getValue()) {
                return false;

            }
            if (row > pos.getRow() && tiles[row][pos.getColumn()] != null 
                    && tiles[row][pos.getColumn()].getValue() <= tile.getValue()) {
                return false;
            }
           

        }

        return true;
    }
    
    /**
     * browse the column in the 2D table that match to the current player's board
     * @param pos Position where the tile should be placed
     * @param tile Tile to be placed
     * @return true if the tile can be placed on the column according to the game rules
     */

    private boolean columnBrowsing(Position pos, Tile tile) {
 
        for (int col = 0; col < getSize(); col++) {

        
            if (col < pos.getColumn() && tiles[pos.getRow()][col] != null
                    && tiles[pos.getRow()][col].getValue() >= tile.getValue()) {
                return false;

            }
            if (col > pos.getColumn() && tiles[pos.getRow()][col] != null
                    && tiles[pos.getRow()][col].getValue() <= tile.getValue()) {
                return false;

            }
        }
       
        return true;

    }

    /**
     * places the given tile in the given position
     *
     * @param tile
     * @param pos a position of the board
     */
    public void put(Tile tile, Position pos) {
        this.tiles[pos.getRow()][pos.getColumn()] = tile;

    }

    /**
     * check if the board is completely filled with tiles
     *
     * @return true or false
     */
    public boolean isFull() {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] == null) {
                    return false;

                }

            }

        }
        return true;

    }

    public static void main(String[] args) {
        Board board = new Board();
    }

}
