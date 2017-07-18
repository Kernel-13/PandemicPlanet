package Model;

import java.util.ArrayList;

/**
 *
 * @author Ederson
 */
public class Node {

    private String id;
    private State state;
    private final ArrayList<String> neighbors;

    public Node(String id) {
        this.id = id;
        this.state = State.HEALTHY;
        this.neighbors = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public State getState(){
        return this.state;
    }
    
    public void setState(State s){
        this.state = s;
    }
    
    public void addNeighbor(String f) {
        this.neighbors.add(f);
    }

    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    public int getNeighborSize() {
        return this.neighbors.size();
    }

}
