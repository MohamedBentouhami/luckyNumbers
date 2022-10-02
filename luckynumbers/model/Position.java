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
public class Position {

    private int row;
    private int column;

    /**
     * creates an instance of this Position class
     *
     * @param row
     * @param column
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * give the row number
     *
     * @return the row number
     */
    public int getRow() {
        return row;
    }

    /**
     * give the column number
     *
     * @return the column number
     */
    public int getColumn() {
        return column;
    }

}
