package graph;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class GraphVisualizer {
    public static void main(String[] args) throws Exception {
        //
        System.setProperty("org.graphstream.ui", "swing");

        // Use MultiGraph here (actually created inside readFromFile)
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph01.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph02.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph03.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph04.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph05.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph06.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph07.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph08.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph09.gka");
        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph10.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph11.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph12.gka");
//        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources/graph13.gka");

        //Knoten sind rot, Kanten sind grau
        graph.setAttribute("ui.stylesheet", "node { fill-color: red; } edge { fill-color: gray; }");
        //ID des Knoten als Label
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }

        graph.display();
    }
}


