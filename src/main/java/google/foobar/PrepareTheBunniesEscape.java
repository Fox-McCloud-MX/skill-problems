package google.foobar;

import java.util.ArrayList;
import java.util.List;

public class PrepareTheBunniesEscape {

    public static final String isPassable = "0";

    public int solve(String [][] space) {

        List<Node> nodes  = generateNodes(space);
        //System.out.println(nodes);
        nodes.stream().forEach(s -> System.out.println(s));

        return 0;
    }

    public List<Node> generateNodes(String [][] space) {
        int yBoundary = space.length-1;
        int xBoundary = space[0].length-1;

        List<Node> nodes = new ArrayList<>();

        for (int x=0; x<space.length; x++) {

            int up = x-1;
            int down = x+1;

            for (int y=0; y<space[x].length; y++) {
                System.out.println("doing: " + x +","+y);

                int left = y-1;
                int right = y+1;

                List<Transition> _transitions = new ArrayList<>();

                if (up >= 0) {
                    Transition transition = new Transition(up, y, "up", space[up][y]);
                    _transitions.add(transition);
                }
                if (down <= yBoundary) {
                    Transition transition = new Transition(down, y, "down", space[down][y]);
                    _transitions.add(transition);
                }
                if (left >= 0) {
                    Transition transition = new Transition(x, left, "left", space[x][left]);
                    _transitions.add(transition);
                }
                if (right <= xBoundary) {
                    Transition transition = new Transition(x, right, "right", space[x][right]);
                    _transitions.add(transition);
                }

                Node node = new Node(x+"_"+y, _transitions, space[x][y]);
                nodes.add(node);

                left++;
                right--;
            }
        }
        return nodes;
    }
}

class Node {
    private String id;
    private List<Transition> transitions;
    private boolean visited = false;
    private String value;

    public Node(String id, List<Transition> transitions, String value) {
        this.id = id;
        this.transitions = transitions;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", transitions=" + transitions +
                ", visited=" + visited +
                '}';
    }
}


class Transition {
    private int x;
    private int y;
    private int cost = 1;
    private String type;
    private boolean passable;
    private String value;

    public Transition(int x, int y, String type, String passable) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.passable = passable.equals(PrepareTheBunniesEscape.isPassable);
        this.value = passable;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(String passable) {
        this.passable = passable.equals(PrepareTheBunniesEscape.isPassable);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", passable=" + passable +
                ", value=" + value +
                '}';
    }
}