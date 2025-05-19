package graph;
import org.graphstream.graph.*;
import java.util.*;

public class BFS {

    public static List<String> bfs(Graph graph, String startId, String goalId) {
        //Map jeden Knoten zu seiner Stufe λ(v) bzw. Länge aus dem Startknoten
        Map<String, Integer> label = new HashMap<>();
        //Map jeden Knoten zu seinem Vorgänger
        Map<String, String> predecessor = new HashMap<>();
        //BFS Queue
        Queue<Node> queue = new LinkedList<>();

        //get Start- und Endknoten und validieren
        Node start = graph.getNode(startId);
        Node goal = graph.getNode(goalId);

        if (start == null || goal == null) {
            throw new IllegalArgumentException("Start or goal node not found in the graph.");
        }
        // Initialisierung BFS:
        //Startknoten mit Label 0 in Stufenmap eintragen und enqueue in Queue
        label.put(startId, 0);
        queue.add(start);

        //BFS Loop
        while (!queue.isEmpty()) {

            //Dequeue aktuellen Knoten
            Node current = queue.poll();
            //Stufe des aktuellen Knotens
            int currentLabel = label.get(current.getId());

            //alle Nachbarn des aktuellen Knotens anhand seine Kanten besuchen
            for (Edge edge : current.edges().toList()) {
                Node neighbor = edge.getOpposite(current); // Über die Kante adjazent Knoten
                String neighborId = neighbor.getId();

                //Kontrollieren, ob der Nachbarnknoten schon besucht wurde bzw. zu einer Stufe gehört. Wenn nicht, wird dann
                //in label und predecessor gemapt
                if (!label.containsKey(neighborId)) {
                    label.put(neighborId, currentLabel + 1);
                    //aus current zu neighbor
                    predecessor.put(neighborId, current.getId());
                    //enqueue Neighbor
                    queue.add(neighbor);

                    //Wenn Zielknoten erreicht, abbrechen
                    if (neighborId.equals(goalId)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }
        // Path Rekonstruktion
        //Wenn Zielknoten nicht erreicht wurde, dann kein Path gefunden
        if (!label.containsKey(goalId)) {
            return Collections.emptyList();
        }

        List<String> path = new LinkedList<>();
        //Aus Zielknoten rückwärts
        String step = goalId;

        //Solange Step existiert und bis zum Anfangsknoten, welcher kein Vorgänger hat
        while (step != null && predecessor.containsKey(step)) {
            //Am Anfang der Liste einfügen
            path.add(0, step);
            //step mit Vorgängerknoten aktualisieren
            step = predecessor.get(step);
        }
        //Anfangsknoten in die Liste einfügen
        if (step != null && step.equals(startId)) {
            path.add(0, startId);
        }

        return path;
    }
}
