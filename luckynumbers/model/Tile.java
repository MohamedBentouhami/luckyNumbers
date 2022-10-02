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
public class Tile {

    private int value;

    /**
     * creates an instance of this Tile class
     *
     * @param value
     */
    public Tile(int value) {

        this.value = value;
    }

    /**
     * give the value of the Tile
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }

}
