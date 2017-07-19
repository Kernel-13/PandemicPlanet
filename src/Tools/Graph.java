/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.ArrayList;

/**
 *
 * @author Jose Manuel Perez Zamorano Ederson Funes Castillo
 */
public class Graph {

    private ArrayList<Integer> infectedPeople;
    private ArrayList<Integer> healthyPeople;
    private ArrayList<Integer> recoveredPeople;
    private int count;

    public Graph() {
        this.infectedPeople = new ArrayList<>();
        this.healthyPeople = new ArrayList<>();
        this.recoveredPeople = new ArrayList<>();
        this.count = 0;
    }
    
    public Graph(ArrayList<Integer> infectedPeople, ArrayList<Integer> healthyPeople) {
        this.infectedPeople = infectedPeople;
        this.healthyPeople = healthyPeople;
        this.count = healthyPeople.size();
    }
    
    public Graph(ArrayList<Integer> infectedPeople,
            ArrayList<Integer> healthyPeople, ArrayList<Integer> recoveredPeople) {
        this.infectedPeople = infectedPeople;
        this.healthyPeople = healthyPeople;
        this.recoveredPeople = recoveredPeople;
        this.count = healthyPeople.size();
    }

    

    /**
     * @return the infectedPeople
     */
    public ArrayList<Integer> getInfectedPeople() {
        return infectedPeople;
    }

    /**
     * @return the healthyPeople
     */
    public ArrayList<Integer> getHealthyPeople() {
        return healthyPeople;
    }

    /**
     * @return the recoveredPeople
     */
    public ArrayList<Integer> getRecoveredPeople() {
        return recoveredPeople;
    }
    
    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

}
