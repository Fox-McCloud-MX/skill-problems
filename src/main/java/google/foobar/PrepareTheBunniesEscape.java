package google.foobar;

import java.util.*;
import java.util.stream.Collectors;

public class PrepareTheBunniesEscape {

    public static final String isPassable = "0";
    public static final boolean breakWall = true;
    public static final int wallsAllowedToBreak = 1;

    public static int solve(int[][] space) {

        String[][] _space = Arrays.stream(space).map(a ->
                Arrays.stream(a).mapToObj(i ->
                        Integer.toString(i)).toArray(String[]::new)).toArray(String[][]::new);

        Graph graph = new Graph();
        graph.generateGraph(_space, "PrepareTheBunniesEscape");
        String end = (space.length-1) + "_" + (space[0].length-1);
        String start = "0_0";
        graph.dfs(start);

        graph.generateRoute(end);

        graph.route.stream().forEach(n -> {
            System.out.println(n.getId());
        });

        List<Node> _nodes = graph.calculateBreakWalls();
        System.out.println("_nodes: " + (_nodes.size()+1));

        int solution = graph.route.size() < _nodes.size()+1 ? graph.route.size() : _nodes.size()+1;

        return solution;
    }
}

class Graph {

    private String id;
    public HashMap<String, Node> nodes = new LinkedHashMap<>();
    public List<Node> route = new ArrayList<>();

    public HashMap<String, Node> generateGraph(String [][] space, String id) {
        this.id = id;
        int yBoundary = space.length-1;
        int xBoundary = space[0].length-1;

        for (int x=0; x<space.length; x++) {

            int up = x-1;
            int down = x+1;

            for (int y=0; y<space[x].length; y++) {
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
                nodes.put(x+"_"+y, node);

                left++;
                right--;
            }
        }
        return nodes;
    }

    public void dfs(String end) {
        this.dfsVisit(end);
    }

    public void dfsVisit(String end) {
        Node node = this.nodes.get(end);
        node.setVisited(true);

        for (Transition transition : node.getTransitions()) {
            if (transition.isPassable()) {
                Node _node = this.nodes.get(transition.getX()+"_"+ transition.getY());
                if (!_node.isVisited()) {
                    _node.setPredecessor(node.getId());
                    _node.setVisited(true);
                    this.dfsVisit(transition.getX()+"_"+ transition.getY());
                };
            }
        };
    }

    public void generateRoute(String end) {
        Node node = this.nodes.get(end);
        if (node.getPredecessor().equals("NONE")) {
            this.route.add(this.nodes.get(end));
        }
        else {
            this.route.add(this.nodes.get(end));
            this.generateRoute(node.getPredecessor());
        }

        Collections.reverse(this.route);
    }

    public void generateRoute(String end, Node node) {
        List<Node> _nodes = new ArrayList<>();
        if (node.getPredecessor().equals("NONE")) {
            _nodes.add(this.nodes.get(end));
        }
        else {
            _nodes.add(this.nodes.get(end));
            this.generateRoute(node.getPredecessor());
        }

        Collections.reverse(this.route);
    }

    public List<Node> calculateBreakWalls() {
        List<BreakWall> breakWallPath = new ArrayList<>();
        List<List<Node>> _breakWallPath = new ArrayList<>();
        for (int i=0; i<this.route.size(); i++) {
            for (Transition transition : this.route.get(i).getTransitions()) {
                if (!transition.isPassable()) {
                    int x = transition.getX();
                    int y = transition.getY();


                    String originalTransition   = (x) + "_" + (y);
                    String nextTransitionUp     = (x-1) + "_" + (y);
                    String nextTransitionDown   = (x+1) + "_" + (y);
                    String nextTransitionLeft   = (x)   + "_" + (y-1);
                    String nextTransitionRight  = (x)   + "_" + (y+1);

                    //System.out.println(transition +" " + originalTransition +" "+ nextTransitionUp +" "+ nextTransitionDown +" "+ nextTransitionLeft +" "+ nextTransitionRight);

                     _breakWallPath.add(this.route.stream().filter( _node ->
                            !_node.getId().equals(originalTransition)   &&
                            (_node.getId().equals(nextTransitionUp)     ||
                            _node.getId().equals(nextTransitionDown)    ||
                            _node.getId().equals(nextTransitionLeft)    ||
                            _node.getId().equals(nextTransitionRight))
                    ).collect(Collectors.toList()));
                }
            }
        }

        //System.out.println("_breakWallPath.size: " + _breakWallPath.size());
        /*_breakWallPath.stream().forEach(s -> {
            System.out.println(s.size());
            s.stream().forEach(t -> System.out.println(t));
        });*/

        for (int i=0; i<_breakWallPath.size(); i++) {
            if (_breakWallPath.get(i).size() == 2) {
                Node node1 = _breakWallPath.get(i).get(0);
                Node node2 = _breakWallPath.get(i).get(1);
                breakWallPath.add(new BreakWall(node1.getId(), node2.getId()));
            }
        }

        List<List<Node>> lNodes = new ArrayList<>();


        for (BreakWall b : breakWallPath) {
            List<Node> _lNodes = new ArrayList<>();
            boolean ommit = false;
            for (Node node : this.route) {

                if (node.getId().equals(b.getStart())) {
                    ommit = true;
                    _lNodes.add(node);
                }

                if (node.getId().equals(b.getEnd())) {
                    ommit = false;
                }

                if (!ommit) {
                    _lNodes.add(node);
                }

            }
            lNodes.add(_lNodes);
        }

        /*lNodes.stream().forEach(l -> {
            System.out.println(l.size() +" ");
            l.stream().forEach(g -> System.out.println(g));
        });*/

        return lNodes.stream().min(Comparator.comparingInt(List::size)).get();
    }
}

class Node {
    private String id;
    private List<Transition> transitions;
    private boolean visited = false;
    private String value;
    private String predecessor = "NONE";

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
                ", predecessor=" + predecessor +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
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
class BreakWall {
    private String start;
    private String end;

    public BreakWall(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "BreakWall{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}