/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.planet;

import Controller.Controller;
import View.Interfaz;

/**
 *
 * @author Ederson
 */
public class PandemicPlanet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Controller controller = new Controller();
        Interfaz view = new Interfaz(controller);
        view.setVisible(true);

        /*
        Controller c = new Controller();
        Interfaz i = new Interfaz(c);
        i.setVisible(true);
         */
    }
    
}
