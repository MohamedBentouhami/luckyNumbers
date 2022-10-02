/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import luckynumbers.model.Game;
import luckynumbers.model.Model;
import g56387.luckynumbers.model.Position;
import g56387.luckynumbers.view.MyView;
import g56387.luckynumbers.view.View;

import java.util.Scanner;

/**
 *
 * @author mohamed
 */
public class Controller {

    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;
    }

    /**
     * manage a game from start to finish. It uses the different states of the
     * game
     */
    public void play() {
        

        view.displayWelcome();
        while (true) {
            switch (model.getState()) {
                case NOT_STARTED:
                    int playerCount = view.askPlayerCount();
                    model.start(playerCount);
                    view.displayWelcome();
                    break;

                case PICK_TILE:
                    model.pickTile();
                    view.displayGame();

                    break;
                case PLACE_TILE:
                    Position pos = view.askPosition();
                    model.putTile(pos);

                    break;

                case GAME_OVER:

                    System.out.println("Le joueur numéro n°" + model.getWinner() + " a gagné");
                    if (stopOrContinue("Voulez vous recommencer une partie [o/n]?")) {
                        playerCount = view.askPlayerCount();
                        model.start(playerCount);
                        view.displayWelcome();
                    } else {

                     System.exit(0);
                    }
                    break;
                case TURN_END:
                    model.nextPlayer();
                    break;

            }

        }

    }

    private boolean stopOrContinue(String question) {
        Scanner clavier = new Scanner(System.in);
        String mot = "";
        boolean result = false;
        do {
            System.out.println(question);
            mot = clavier.nextLine();

        } while (!(mot.isEmpty() || mot.matches("[OoNn]")));
        if (mot.equals("o") || mot.equals("O")) {
            result = true;

        }

        return result;

    }

    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }

}
