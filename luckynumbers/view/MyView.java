/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luckynumbers.Myview;

import g56387.luckynumbers.model.Game;
import g56387.luckynumbers.model.Model;

import g56387.luckynumbers.model.Position;
import java.util.Scanner;

/**
 * displays and requests readings from users
 *
 * @author mohamed
 */
public class MyView implements View {

    private Model model;

    @Override
    public void displayWelcome() {
        System.out.println("");
        System.out.println("<<<<<<<< Lucky Numbers by Bentouhami Mohamed " + " version 5.2 >>>>>>>>");
        System.out.println();
    }

    @Override
    public void displayGame() {
        System.out.println("Joueur n° " + (model.getCurrentPlayerNumber() + 1));
        System.out.println("");
        System.out.println("     1  2  3  4 ");
        System.out.println("     ----------------");
        displayBoard();
        System.out.println("");
        System.out.println("Tuile choisie ->  " + model.getPickedTile().getValue());

    }

    /**
     * displays the current player's board
     */
    private void displayBoard() {
        for (int i = 0; i < model.getBoardSize(); i++) {
            System.out.print(i + 1 + "|  ");
            for (int j = 0; j < model.getBoardSize(); j++) {
                Position pos = new Position(i, j);
                if (model.getTile(model.getCurrentPlayerNumber(), pos) == null) {
                    System.out.print(" . ");
 
                } else if (model.getTile(model.getCurrentPlayerNumber(), pos).getValue() > 10) {
                    System.out.print( model.getTile(model.getCurrentPlayerNumber(), pos).getValue()+ " ");
                } else {
                    System.out.print(" "+ model.getTile(model.getCurrentPlayerNumber(), pos).getValue() + " ");
                }

            }

            System.out.println("  ");

        }

    }

    @Override
    public void displayWinner() {
        System.out.println(model.getWinner()+1);
    }

    @Override
    public int askPlayerCount() {
        int playerCount = readInt("Veuillez entrer le nombre de joueurs", 2, 4);

        return playerCount;
    }

    @Override
    public Position askPosition() {

        Position pos;

        int lg = readInt("Veuillez entrer le numéro de la ligne", 1, 4);
        int col = readInt("Veuillez entrer le numéro de la colonne", 1, 4);
        
        pos = new Position(lg - 1, col - 1);

        while (!model.canTileBePut(pos)) {
            displayError("Position non validé par les régles");
            lg = readInt("Veuillez entrer le numéro de la ligne", 1, 4);
            col = readInt("Veuillez entrer le numéro de la colonne", 1, 4);
             pos = new Position(lg - 1, col - 1);

        }
        return pos;

    }

    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    public MyView(Model model) {
        this.model = model;

    }

    /**
     * Reads an integer from the standard input. If the input does not match an
     * integer, an error message is displayed and the method asks the question
     * again until it reads an integer
     *
     * @param msg to display to the user.
     * @return the integer read
     */
    public static int readInt(String msg) {
        Scanner clavier = new Scanner(System.in);
        System.out.println(msg + ": ");
        while (!clavier.hasNextInt()) {
            System.err.print(" Ce n'est pas un entier! ");
            clavier.next();
            System.out.println(msg + ":");
        }
        return clavier.nextInt();
    }

    /**
     * Reads an integer between a min and a max. If the value entered is not an
     * integer in the correct range, an error message is displayed ∗ and the
     * method asks the question again until it is correct.
     *
     * @param msg the message to be displayed to the user.
     * @param min the minimum value allowed.
     * @param max the maximum allowable value.
     * @return the integer read.
     */
    public static int readInt(String msg, int min, int max) {
        int value;
        String msgWithLimits = msg + " (" + min + " à " + max + ")";
        value = readInt(msgWithLimits);
        while (value < min || value > max) {
            System.out.println("La valeur " + value + " n’est pas dans l’intervalle [" + min + " - " + max + "]");
            value = readInt(msgWithLimits);
        }
        return value;
    }
    

    public static void main(String[] args) {

        Model game = new Game();
        View vue = new MyView(game);

        //  vue.askPlayerCount();
        game.start(vue.askPlayerCount());
        game.pickTile();
        View test = new MyView(game);
        vue.displayWelcome();
        vue.displayGame();

    }

}
