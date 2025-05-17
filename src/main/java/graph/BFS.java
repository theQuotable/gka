package graph;

import org.graphstream.graph.*;

import java.util.*;

public class BFS {

    public static List<String> bfs(Graph graph, String startId, String goalId) {
        Map<String, String> predecessor = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Node start = graph.getNode(startId);
        Node goal = graph.getNode(goalId);

        if (start == null || goal == null) {
            throw new IllegalArgumentException("Start oder Zielknoten nicht im Graph vorhanden.");
        }

        queue.add(start);
        visited.add(start.getId());

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Edge edge : current.edges().toList()) {
                Node neighbor = edge.getOpposite(current);

                if (!visited.contains(neighbor.getId())) {
                    visited.add(neighbor.getId());
                    predecessor.put(neighbor.getId(), current.getId());
                    queue.add(neighbor);
                    if (neighbor.equals(goal)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        // Pfad rekonstruieren
        List<String> path = new LinkedList<>();
        String step = goalId;

        while (step != null && predecessor.containsKey(step)) {
            path.add(0, step);
            step = predecessor.get(step);
        }

        if (step != null && step.equals(startId)) {
            path.add(0, startId);
            return path;
        }

        return Collections.emptyList(); // Kein Pfad gefunden
    }
}
