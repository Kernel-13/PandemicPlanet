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
    private String log;

    public Graph(ArrayList<Integer> infectedPeople,
            ArrayList<Integer> healthyPeople, ArrayList<Integer> recoveredPeople,
            String log) {
        this.infectedPeople = infectedPeople;
        this.healthyPeople = healthyPeople;
        this.recoveredPeople = recoveredPeople;
        this.count = healthyPeople.size();
        this.log = log;
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

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }

}
