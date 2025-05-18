package graph;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.util.regex.*;

public class GraphIO {

    public static Graph readFromFile(String filePath) {
        // MultiGraph für parallel Kanten
        Graph graph = new MultiGraph("gka");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
        //Zeilenweise Formatierung
            while ((line = reader.readLine()) != null) {
                line = line.strip();

                if (line.isEmpty()) continue;
         // Gerichtet oder ungerichtet
                if (line.contains("->") || line.contains("--")) {
                    boolean directed = line.contains("->");

                    // Aufteilung und Parsing der Knoten
                    String[] parts = directed ? line.split("->") : line.split("--");

                    String node1 = parts[0].strip();
                    String rest = parts[1].strip().replace(";", "");  //entferne Trennzeichen

                    // node2 extrahieren  (vor ':' oder '(' wenn vorhanden)  Bsp-"Hamburg : 127" => Hamburg
                    String node2 = rest.split("[:\\(]")[0].strip();

                    // Parse Gewichtung wenn vorhanden , default ist 1.0  Bsp-"B (e1) : 2" → split(":") → ["B (e1) ", " 2"]
                    double weight = rest.contains(":") ? Double.parseDouble(rest.split(":")[1].strip()) : 1.0;

                    // Knoten zum Graph hinzufügen wenn nicht vorhanden
                    if (graph.getNode(node1) == null) graph.addNode(node1);
                    if (graph.getNode(node2) == null) graph.addNode(node2);

                    //KantenId aus geparsten Wege
                    String edgeId;
                    //Gerichtet oder ungerichtet
                    if (directed) {
                        //
                        edgeId = node1 + "->" + node2;
                        // Gerichtete Kante mit entsprechendem Gewicht hinzufügen
                        if (graph.getEdge(edgeId) == null) {
                            graph.addEdge(edgeId, node1, node2, true).setAttribute("weight", weight);
                        }
                    } else {
                        //Sortierung der Knotennamen zur Vermeidung von doppelten Kanten  Bsp. A -- B gleich wie B -- A
                        // Alphabetische Ordnung
                        String first = node1.compareTo(node2) < 0 ? node1 : node2;
                        String second = node1.compareTo(node2) < 0 ? node2 : node1;
                        //Eindeutige ID des Kantes
                        edgeId = first + "--" + second;

                        // Ungerichtete Kanten mit entsprechendem Gewicht hinzufügen
                        if (graph.getEdge(edgeId) == null) {
                            graph.addEdge(edgeId, first, second, false).setAttribute("weight", weight);
                        }
                    }
                // Isolierte Knoten
                    // Falls  eine Zeile nur ein einzelner Knoten ist Bsp. X;
                } else if (line.endsWith(";")) {
                    String node = line.replace(";", "").strip();
                    if (graph.getNode(node) == null) graph.addNode(node);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    public static void saveToFile(Graph graph, String path) throws IOException {
        //open file to write
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path))) {
            boolean isDirected = false;

            // Prüfe, ob überhaupt eine Kante existiert, und bestimme ob es sich um gerichtete oder ungerichtete Graphen handelt
            if (graph.getEdgeCount() > 0) {
                Edge firstEdge = graph.getEdge(0);
                isDirected = firstEdge.isDirected();
            }
            //Iteration durch alle Kanten und entsprechende Extraktion der Start-, Endknoten und ggf. Gewichtung
            for (Edge edge : graph.edges().toList()) {
                String src = edge.getSourceNode().getId();
                String tgt = edge.getTargetNode().getId();
                Object weight = edge.getAttribute("weight");

                String line = src + (isDirected ? " -> " : " -- ") + tgt;

                if (weight != null) {
                    line += ": " + weight;
                }
                //Schreiben in der Datei
                writer.write(line + ";\n");
            }
             //Iteration und schreiben der isolierten Knoten in der Datei
            for (Node node : graph.nodes().toList()) {
                //Wenn getDegree() == 0 , dann hat der Knoten keine Kanten
                if (node.getDegree() == 0) {
                    writer.write(node.getId() + ";\n");
                }
            }
        }
    }}

