/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 *
 * @author Ederson
 */
public class Network {

    protected int infected;                     // Number of infected nodes
    protected int healthy;                      // Number of healthy nodes
    protected HashMap<String, Node> airports;   // All the Nodes
    protected ArrayList<String> quarantine;     // Nodes that may go into Quarantine
    protected ArrayList<String> bigHubs;        // Nodes with > 170 Edges
    protected ArrayList<String> smallHubs;      // Nodes with < 6 edges

    public Network() {
        this.infected = 0;
        this.healthy = 0;
        this.airports = new HashMap<>();
        this.quarantine = new ArrayList<>();
        this.bigHubs = new ArrayList<>();
        this.smallHubs = new ArrayList<>();
    }

    public void doAll(int iter, boolean RW, int RW_F, boolean cheats,
            double Q, int grad_q, boolean SM, boolean BG, String directory, String fileName) {
        //ABSTRACT
    }

    /**
     * * Network Initialization
     *
     * Method that fills our 'airport' map with data from 2 text files provided
     * by the user
     */
    public void initializeNetwork(String nodeFile, String edgeFile) {
        try {
            BufferedReader readerNodes = new BufferedReader(new FileReader(nodeFile));
            BufferedReader readerEdges = new BufferedReader(new FileReader(edgeFile));

            String lineNodes = readerNodes.readLine();
            while ((lineNodes = readerNodes.readLine()) != null) {
                String[] parts = lineNodes.split(";");
                airports.put(parts[0], new Node(parts[0]));
                healthy++;
            }

            String lineEdges = readerEdges.readLine();
            while (lineEdges != null) {
                String[] parts = lineEdges.split(";");
                Node one = addingNeighbors(parts[0], parts[1]);
                Node two = addingNeighbors(parts[1], parts[0]);
                airports.put(parts[0], one);
                airports.put(parts[1], two);

                if (one.getNeighborSize() > 170 && !bigHubs.contains(one.getId())) {
                    bigHubs.add(one.getId());
                }

                lineEdges = readerEdges.readLine();
            }

            readerNodes.close();
            readerEdges.close();
        } catch (IOException e) {
            // WAT
        }
    }

    /**
     * * Quarantine Initialization
     *
     * Method that fills our 'quarantine' List with nodes that meet some
     * requirements
     *
     * @param num_edges
     */
    public void initializeQuarantineList(int num_edges) {
        for (Entry<String, Node> entry : airports.entrySet()) {
            if (entry.getValue().getNeighborSize() > num_edges) {
                quarantine.add(entry.getKey());
            }
        }
    }

    /**
     * * Quarantine Activation
     *
     * Method that changes the 'state' of the nodes from the 'quarantine' list
     * which haven't been infected yet.
     */
    public void activateQuarantine() {
        for (int i = 0; i < quarantine.size(); i++) {
            if (airports.get(quarantine.get(i)).getState() != State.INFECTED
                    && airports.get(quarantine.get(i)).getState() != State.REMOVED) {
                Node nodo = airports.get(quarantine.get(i));
                nodo.setState(State.QUARANTINE);
                airports.put(quarantine.get(i), nodo);
                healthy--;
            }
        }
    }

    /**
     * * Random Infection
     *
     **
     * @param left
     */
    public void pickUnfortunate(ArrayList<String> left) {
        List<String> keys;
        if (left == null) {
            keys = new ArrayList<>(airports.keySet());
        } else {
            keys = left;
        }
        Random rn = new Random();
        int ran = rn.nextInt(keys.size());
        String id = keys.get(ran);

        Node node = airports.get(id);
        if (node.getState() == State.HEALTHY) {
            node.setState(State.INFECTED);
            airports.put(id, node);
            infected++;
            healthy--;
        }
    }

    /**
     * * Pathogen Transmission
     * Method that will spread the pathogen through the network
     **
     * @param rate
     */
    public void transmission(Double rate) {
        ArrayList<String> newInfected = new ArrayList<>();
        //HashMap<String, Node> aux = deepCopy();
        for (Entry<String, Node> entry : airports.entrySet()) {                 // Iterate over our nodes
            Node node = airports.get(entry.getKey());				// We get one node
            if (node.getState() == State.INFECTED) {				// Check if its infected
                ArrayList<String> friends = node.getNeighbors();                  // If so, then we get his neighbors
                for (int i = 0; i < friends.size(); i++) {                      // Iterate over the neighbors
                    Node friend = airports.get(friends.get(i));                 // We work with each neighbor
                    if (luck(rate)
                            && friend.getState() == State.HEALTHY
                            && !newInfected.contains(friend.getId())) {
                        newInfected.add(friend.getId());                        // We add it so we can infect it later
                        infected++;						// One More Infected
                        healthy--;                                              // One Healthy Less
                    }
                }
            }
        }

        for (String node : newInfected) {
            Node aux = airports.get(node);
            aux.setState(State.INFECTED);
            airports.put(node, aux);
        }

    }

    public void recovery() {
        //ABSTRACT
    }

    /**
     * Method that fills our SmallHubs List
     */
    protected void smallHubs() {
        for (Entry<String, Node> entry : airports.entrySet()) {
            if (entry.getValue().getNeighborSize() < 6) {
                smallHubs.add(entry.getKey());
            }
            if (smallHubs.size() > 50) {
                break;
            }
        }
    }

    /**
     * * Adding Neighbors
     *
     * Method that links 2 different nodes
     *
     */
    private Node addingNeighbors(String s1, String s2) {
        Node node = airports.get(s1);
        int pos = node.getNeighbors().indexOf(s2);    // Buscamos el otro nodo en la lista
        if (pos == -1) {                            // Si no esta en su lista de amigos ...
            node.addNeighbor(s2);                     // ... Nos aseguramos de incluirlo
        }
        return node;
    }

    private boolean luck(Double rate) {
        boolean ok = false;
        Random rand = new Random();
        double res = rand.nextDouble();
        if (res < rate) {
            ok = true;
        }
        return ok;
    }

}
