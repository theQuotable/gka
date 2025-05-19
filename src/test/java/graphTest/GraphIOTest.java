package graphTest;

import graph.GraphIO;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GraphIOTest {

    @Test
    public void testReadGerichteteGraph() throws IOException {
        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/test/java/resources/graph01.gka");

        assertNotNull(graph);
        assertEquals(14, graph.getNodeCount());
        assertEquals(39, graph.getEdgeCount());
    }

    @Test
    public void testReadUngerichteteGraph() throws IOException {
        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/test/java/resources/graph02.gka");

        assertNotNull(graph);
        assertEquals(11, graph.getNodeCount());
        assertEquals(34, graph.getEdgeCount());
    }

    @Test
    public void testReadGerichteteGraphMitGewichtung() throws IOException {
        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/test/java/resources/graph12.gka");

        assertNotNull(graph);
        assertEquals(4, graph.getNodeCount());
        assertEquals(10, graph.getEdgeCount());
    }

    @Test
    public void testReadUngerichteteGraphMitGewichtung() throws IOException {
        Graph graph = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/test/java/resources/graph03.gka");

        assertNotNull(graph);
        assertEquals(22, graph.getNodeCount());
        assertEquals(40, graph.getEdgeCount());
    }

    @Test
    public void testSaveAndReloadGraph() throws IOException {
        Graph original = GraphIO.readFromFile("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/test/java/resources/graph01.gka");
        //Neue Datei erzeugen
        Path tempFile = Files.createTempFile("graph-test", ".gka");
        //Gelesene Graphen wird in tempFile gespeichert
        GraphIO.saveToFile(original, tempFile.toString());
        //Reload und Vergleich beiden Graphen
        Graph reloaded = GraphIO.readFromFile(tempFile.toString());

        assertEquals(original.getNodeCount(), reloaded.getNodeCount());
        assertEquals(original.getEdgeCount(), reloaded.getEdgeCount());

        Files.deleteIfExists(tempFile);
    }
}
