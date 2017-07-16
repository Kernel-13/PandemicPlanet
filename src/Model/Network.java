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

    protected HashMap<String, Node> airports;	// Collection of nodes
    protected ArrayList<String> inQuarantine;
    protected int infected;			// Number of infected nodes
    protected int healthy;			// Number of healthy nodes
    protected double infectionRate;		// Infection Rate
    protected double recoveryRate;		// Recovery Rate
    protected String nodeFile;
    protected String edgeFile;
    protected ArrayList<String> bigHubs;
    protected ArrayList<String> smallHubs;

    public Network(double rate, double reco, String nodeFile, String edgeFile) {
        this.infected = 0;
        this.healthy = 0;
        this.infectionRate = rate;
        this.recoveryRate = reco;
        this.airports = new HashMap<>();
        this.inQuarantine = new ArrayList<>();
        this.nodeFile = nodeFile;
        this.edgeFile = edgeFile;
        this.bigHubs = new ArrayList<>();
        this.smallHubs = new ArrayList<>();
    }

    public void doAll(int iter, boolean RW, int RW_F, boolean cheats,
            double Q, int grad_q, boolean SM, boolean BG, String directory, String fileName) {
        //ABSTRACT
    }

    /**
     * * Inicializacion de Nuestra Red **
     */
    protected void initializeCollection() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nodeFile));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                airports.put(parts[0], new Node(parts[0]));
                healthy++;
            }
            reader.close();
        } catch (IOException e) {
            // WAT
        }
    }

    protected void initializeRelations() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(edgeFile));
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                Node one = addingFriends(parts[0], parts[1]);
                Node two = addingFriends(parts[1], parts[0]);
                airports.put(parts[0], one);
                airports.put(parts[1], two);

                if (one.getFriendsSize() > 170 && !bigHubs.contains(one.getId())) {
                    bigHubs.add(one.getId());
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            // WAT
        }
    }

    protected void InitQuarantineList(int grad_q) {
        for (Entry<String, Node> entry : airports.entrySet()) {
            if (entry.getValue().getFriendsSize() > grad_q) {
                inQuarantine.add(entry.getKey());
            }
        }
    }

    protected void ActivateQuarantine() {
        for (int i = 0; i < inQuarantine.size(); i++) {
            if (airports.get(inQuarantine.get(i)).getState() != State.INFECTED && airports.get(inQuarantine.get(i)).getState() != State.RECOVERED) {
                Node nodo = airports.get(inQuarantine.get(i));
                nodo.setState(State.QUARANTINE);
                airports.put(inQuarantine.get(i), nodo);
                healthy--;
            }
        }
    }

    private Node addingFriends(String s1, String s2) {
        Node node = airports.get(s1);
        int pos = node.getFriends().indexOf(s2);	// Buscamos el otro nodo en la lista
        if (pos == -1) {								// Si no esta en su lista de amigos ...
            node.addFriend(s2);						// ... Nos aseguramos de incluirlo
        }
        return node;
    }

    /**
     * * Primer Contagio
     *
     **
     * @param left
     */
    protected void pickUnfortunate(ArrayList<String> left) {
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

    protected boolean chance(boolean inf) {
        boolean ok = false;
        Random rand = new Random();
        int chance = rand.nextInt(1000);
        double res = (double) chance / (double) 1000;
        if (inf && res < infectionRate) {
            ok = true;
        } else if (!inf && res < recoveryRate) {
            ok = true;
        }
        return ok;
    }

    /**
     * * Resto del Mundo **
     */
    protected void transmission() {
        HashMap<String, Node> aux = deepCopy();
        for (Entry<String, Node> entry : aux.entrySet()) {                      // Iteramos sobre la copia de la lista
            String id = entry.getKey();						// Obtenemos el id DEL MAPA AUXILIAR
            Node node = aux.get(id);						// Obtenemos el nodo asociado DEL MAPA AUXILIAR
            if (node.getState() == State.INFECTED) {				// Si el NODO AUXILIAR esta infectado
                node = airports.get(id);					// Si lo es, entonces actuamos sobre el NODO ORIGINAL	
                ArrayList<String> friends = node.getFriends();                  // Obtenemos los vecinos del NODO ORIGINAL
                for (int i = 0; i < friends.size(); i++) {                      // Iteramos sobre sus vecinos
                    Node friend = airports.get(friends.get(i));                 // Obtenemos uno de los vecinos
                    if (chance(true) && friend.getState() == State.HEALTHY) {   // Infectamos si no lo esta
                        friend.setState(State.INFECTED);			// Infectamos el vecino
                        airports.put(friends.get(i), friend);                   // Lo volvemos a colocar en el mapa
                        infected++;						// Actualizamos la poblacion	
                        healthy--;
                    }
                }
            }
        }
    }

    protected void recovery() {
        //ABSTRACT
    }

    protected HashMap<String, Node> deepCopy() {
        HashMap<String, Node> copy = new HashMap<>();
        airports.entrySet().forEach((entry) -> {
            Node node = new Node(entry.getKey());
            node.setState(entry.getValue().getState());
            for (int i = 0; i < entry.getValue().getFriends().size(); i++) {
                node.addFriend(entry.getValue().getFriends().get(i));
            }
            copy.put(entry.getKey(), node);
        });
        return copy;
    }

    protected void printStatus(int i) {
        //ABSTRACT
    }

    protected void smallHubs() {
        for (Entry<String, Node> entry : airports.entrySet()) {
            if (entry.getValue().getFriendsSize() < 6) {
                smallHubs.add(entry.getKey());
            }
            if (smallHubs.size() > 50) {
                break;
            }
        }
    }
}
