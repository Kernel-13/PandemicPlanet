package Model;

import java.util.ArrayList;

/**
 *
 * @author Ederson
 */
public class Node {

    private String id;
    private State state;
    private final ArrayList<String> friends;

    public Node(String id) {
        this.id = id;
        this.state = State.HEALTHY;
        this.friends = new ArrayList<>();
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
    
    public void addFriend(String f) {
        this.friends.add(f);
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public int getFriendsSize() {
        return this.friends.size();
    }

}
