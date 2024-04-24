import java.util.*;

public class EveryPath {
    private int vertices;
    private LinkedList<Edge>[] adjList;

    public EveryPath(int V) {
        this.vertices = V;
        adjList = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjList[src].add(new Edge(dest, weight));
    }

    public void printPathsOfLength(int src, int dest, int length) {
        ArrayList<Integer> path = new ArrayList<>();
        path.add(src);
        printPathsOfLengthUtil(src, dest, length, 0, path);
    }

    private void printPathsOfLengthUtil(int u, int v, int weight, int currWeight, ArrayList<Integer> path) {
        if (u == v && currWeight == weight) {
            System.out.println("Path of weight 5: " + path);
            return;
        }

        for (Edge edge : adjList[u]) {
            if (currWeight + edge.weight <= weight) {
                path.add(edge.dest);
                printPathsOfLengthUtil(edge.dest, v, weight, currWeight + edge.weight, path);
                path.remove(path.size() - 1); // Backtrack
            }
        }
    }

    private static class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        EveryPath graph = new EveryPath(6);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 0, 4);
        graph.addEdge(4, 1, 4);
        graph.addEdge(4, 5, 6);

        int u = 0;
        int v = 5;
        int length = 18;

        System.out.println("Paths of weight 5 between u and v:");
        System.out.println("u = " + u);
        System.out.println("v = " + v + "\n");

        graph.printPathsOfLength(u, v, length);
    }
}
