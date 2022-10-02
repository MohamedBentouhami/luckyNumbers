/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56387.luckynumbers.main;

import g56387.luckynumbers.controller.Controller;
import g56387.luckynumbers.model.Game;
import g56387.luckynumbers.model.Model;
import g56387.luckynumbers.view.MyView;

/**
 *
 * @author mohamed
 */
public class LuckyNumbers {
    
    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game,new MyView(game));
        controller.play();
    }
    
}
