import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirWeightGraph {
    private Map<String, List<Edge>> adjacencyList;

    public DirWeightGraph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String source, String destination, int weight) {
        List<Edge> edges = adjacencyList.getOrDefault(source, new ArrayList<>());
        edges.add(new Edge(destination, weight));
        adjacencyList.put(source, edges);
    }

    public void findPaths(String u, String w) {
        List<String> path = new ArrayList<>();
        path.add(u);
        dfs(u, w, path, 0);
    }

    private void dfs(String current, String destination, List<String> path, int length) {
        if (length == 5) {
            if (current.equals(destination)) {
                System.out.println(path);
            }
            return;
        }
        List<Edge> neighbors = adjacencyList.getOrDefault(current, new ArrayList<>());
        for (Edge neighbor : neighbors) {
            if (!path.contains(neighbor.destination)) {
                List<String> newPath = new ArrayList<>(path);
                newPath.add(neighbor.destination);
                dfs(neighbor.destination, destination, newPath, length + 1);
            }
        }
    }


}