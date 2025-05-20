package graph;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.*;

public class BFSVisualizer {

    public static void visualizeBFS(Graph graph, String startId, String goalId) {
        // UI Engine setzen
        System.setProperty("org.graphstream.ui", "swing");

        // Stylesheet für Farben und Labels
        graph.setAttribute("ui.stylesheet",
                """
                node {
                    text-size: 16px;
                    text-alignment: above;
                    fill-color: gray;
                    size: 20px;
                    stroke-mode: plain;
                    stroke-color: black;
                }
                node.visited {
                    fill-color: orange;
                }
                node.inPath {
                    fill-color: green;
                }
                node.start {
                    fill-color: blue;
                }
                node.goal {
                    fill-color: red;
                }
                edge {
                    size: 1px;
                    fill-color: #888;
                }
                """);

        // Zeige Knoten-Labels
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }

        graph.display();
        //BFS Vorbereitung
        Map<String, Integer> label = new HashMap<>();
        Map<String, String> predecessor = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        Node start = graph.getNode(startId);
        Node goal = graph.getNode(goalId);

        if (start == null || goal == null) {
            throw new IllegalArgumentException("Start or goal node not found in the graph.");
        }
        // Start und Ziel markieren
        start.setAttribute("ui.class", "start");
        goal.setAttribute("ui.class", "goal");

        // Initialisierung BFS:
        label.put(startId, 0);
        queue.add(start);

        boolean found = false;
        //BFS Loop
        while (!queue.isEmpty() && !found) {
            //Dequeue aktuellen Knoten
            Node current = queue.poll();
            //Stufe des aktuellen Knotens
            int currentLabel = label.get(current.getId());

            sleep(500);
            current.setAttribute("ui.class", "visited");

            //alle Nachbarn des aktuellen Knotens anhand seine Kanten besuchen
            for (Edge edge : current.edges().toList()) {
                Node neighbor = edge.getOpposite(current);
                String neighborId = neighbor.getId();

                //Kontrollieren, ob der Nachbarnknoten schon besucht wurde bzw. zu einer Stufe gehört. Wenn nicht, wird dann
                //in label und predecessor gemapt
                if (!label.containsKey(neighborId)) {
                    label.put(neighborId, currentLabel + 1);
                    //aus current zu neighbor
                    predecessor.put(neighborId, current.getId());
                    queue.add(neighbor);
                    //enqueue Neighbor
                    sleep(300);
                    //Visited werden markiert
                    neighbor.setAttribute("ui.class", "visited");

                    //Wenn Zielknoten erreicht, abbrechen
                    if (neighborId.equals(goalId)) {
                        found = true;
                        break;
                    }
                }
            }
        }

        // Path Rekonstruktion
        if (label.containsKey(goalId)) {
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
//Path wird grün markiert
            for (String nodeId : path) {
                Node n = graph.getNode(nodeId);
                n.setAttribute("ui.class", "inPath");
                sleep(400);
            }

        } else {
            System.out.println("Kein Pfad gefunden.");
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph1 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph01.gka");
    Graph graph2 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph02.gka");
    Graph graph3 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph03.gka");
    Graph graph4 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph04.gka");
    Graph graph5 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph05.gka");
    Graph graph6 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph06.gka");
    Graph graph7 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph07.gka");
    Graph graph8 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph08.gka");
    Graph graph9 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph09.gka");
    Graph graph10 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph10.gka");
    Graph graph11 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph11.gka");
    Graph graph12 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph12.gka");
    Graph graph13 = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph13.gka");

    BFSVisualizer.visualizeBFS(graph1, "a", "e");
    BFSVisualizer.visualizeBFS(graph2, "a", "e");
    BFSVisualizer.visualizeBFS(graph3, "Hamburg", "Soltau");
    BFSVisualizer.visualizeBFS(graph4, "v3", "v7");
    BFSVisualizer.visualizeBFS(graph5, "v2", "v7");
    BFSVisualizer.visualizeBFS(graph6, "1", "7");
    BFSVisualizer.visualizeBFS(graph7, "v4", "v2");
    BFSVisualizer.visualizeBFS(graph8, "v1", "v10");
    BFSVisualizer.visualizeBFS(graph9, "a", "e");
    BFSVisualizer.visualizeBFS(graph10, "v2", "v8");
    BFSVisualizer.visualizeBFS(graph11, "v6", "v10");
    BFSVisualizer.visualizeBFS(graph12, "A", "D");
    BFSVisualizer.visualizeBFS(graph13, "A", "D");

}
}
