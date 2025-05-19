package graphTest;

import graph.BFS;
import graph.GraphIO;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BFSTest {

    private static final Path BASE_PATH = Path.of("/home/uncleruckus/Documents/uni/2025SS/gka/Praktikum/gkaA1/src/main/resources");

    //EINFACHE GRAPHEN
    @Test
    public void testBfsSimpelGerichteteGraphOhneGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph12.gka").toString());  //Kein geeignetes Beispiel in der Testdatei gefunden

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path, "Path sollte nicht null sein");
        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
    }
    @Test
    public void testBfsSimpelGerichteteGraphMitGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph12.gka").toString()); //DONE

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path, "Path sollte nicht null sein");
        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
    }
    @Test
    public void testBfsSimpleUngerichteteGraphOhneGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph02.gka").toString()); //Kein geeignetes Beispiel in der Testdatei gefunden

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path, "Path sollte nicht null sein");
        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
    }
    @Test
    public void testBfsSimpleUngerichteteGraphMitGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph03.gka").toString()); //DONE

        List<String> path = BFS.bfs(graph, "v1", "v100");

        assertNotNull(path, "Path should not be null");
        assertTrue(path.isEmpty(), "There should be no path between v1 and v100");
    }
    //KOMPLEXE GRAPHEN

    @Test
    public void testBfsKomplexeGerichteteGraphOhneGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString()); //DONE

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path, "Path sollte nicht null sein");
        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
    }
    @Test
    public void testBfsKomplexeGerichteteGraphMitGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString()); //DONE

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path, "Path sollte nicht null sein");
        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
    }
    @Test
    public void testBfsKomplexeUngerichteteGraphOhneGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph02.gka").toString());   //DONE

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path, "Path sollte nicht null sein");
        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
    }
    @Test
    public void testBfsKomplexeUngerichteteGraphMitGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph05.gka").toString()); //DONE

        List<String> path = BFS.bfs(graph, "v1", "v100");

        assertNotNull(path, "Path should not be null");
        assertTrue(path.isEmpty(), "There should be no path between v1 and v100");
    }

    @Test
    public void testBfsSameStartAndGoal() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString());

        List<String> path = BFS.bfs(graph, "a", "a");

        assertNotNull(path, "Path sollte nicht null sein");
        assertEquals(1, path.size(), "Path sollte nur den ersten Knoten haben");
        assertEquals("a", path.get(0));
    }
}
