/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Jose Manuel Perez Zamorano Ederson Funes Castillo
 */
public class Graph {

    public ArrayList<Integer> infectedPeople;
    public ArrayList<Integer> healthyPeople;
    public ArrayList<Integer> recoveredPeople;
    public int tam;

    public Graph(ArrayList<Integer> mejoresTodo, int tam,
             ArrayList<Integer> mejoresIt, ArrayList<Integer> mediaPobIt) {
        infectedPeople = mejoresTodo;
        healthyPeople = mejoresIt;
        recoveredPeople = mediaPobIt;
        this.tam = tam;
    }

    public String toString() {
        return this.infectedPeople.toString() + " " + this.healthyPeople.toString()
                + " " + this.recoveredPeople.toString();
    }

}
