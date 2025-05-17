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

                    // Split nodes
                    String[] parts = directed ? line.split("->") : line.split("--");

                    String node1 = parts[0].strip();
                    String rest = parts[1].strip().replace(";", "");

                    // Extract node2 (before ':' or '(' if any)
                    String node2 = rest.split("[:\\(]")[0].strip();

                    // Parse weight if present, default 1.0
                    double weight = rest.contains(":") ? Double.parseDouble(rest.split(":")[1].strip()) : 1.0;

                    // Add nodes if not exist
                    if (graph.getNode(node1) == null) graph.addNode(node1);
                    if (graph.getNode(node2) == null) graph.addNode(node2);

                    String edgeId;
                    if (directed) {
                        edgeId = node1 + "->" + node2;
                        // Add directed edge
                        if (graph.getEdge(edgeId) == null) {
                            graph.addEdge(edgeId, node1, node2, true).setAttribute("weight", weight);
                        }
                    } else {
                        // Undirected edges: normalize node order to avoid duplicate edges with reversed nodes
                        String first = node1.compareTo(node2) < 0 ? node1 : node2;
                        String second = node1.compareTo(node2) < 0 ? node2 : node1;
                        edgeId = first + "--" + second;

                        if (graph.getEdge(edgeId) == null) {
                            graph.addEdge(edgeId, first, second, false).setAttribute("weight", weight);
                        }
                    }

                } else if (line.endsWith(";")) {
                    // isolated node line
                    String node = line.replace(";", "").strip();
                    if (graph.getNode(node) == null) graph.addNode(node);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    public static void saveToFile(Graph graph, String path, boolean directed) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path))) {
            writer.write(directed ? "gerichtet\n" : "ungerichtet\n");

            for (Edge edge : graph.edges().toList()) {
                String src = edge.getSourceNode().getId();
                String tgt = edge.getTargetNode().getId();
                Object weight = edge.getAttribute("weight");

                String line = src + (directed ? " -> " : " -- ") + tgt;

                if (weight != null) {
                    line += ": " + weight;
                }

                writer.write(line + ";\n");
            }

            for (Node node : graph.nodes().toList()) {
                if (node.getDegree() == 0) {
                    writer.write(node.getId() + ";\n");
                }
            }
        }
    }
}
