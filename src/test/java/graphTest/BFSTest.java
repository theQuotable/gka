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
    //GERICHTETE
    //OHNE GEWICHTUNG
//    @Test
//    public void testBfsSimpelGerichteteGraphOhneGewichtungPathExists() throws IOException { //Kein geeignetes Beispiel in der Testdatei gefunden
//        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph12.gka").toString());
//
//        List<String> path = BFS.bfs(graph, "A", "C");
//
//        assertNotNull(path);
//        assertFalse(path.isEmpty());
//        assertEquals("A", path.get(0));
//        assertEquals("C", path.get(path.size() - 1));
//        assertTrue(path.size() <= 3);
//    }

    //EINFACHE GRAPHEN
    //GERICHTETE
    //MIT GEWICHTUNG
    @Test
    public void testBfsSimpelGerichteteGraphMitGewichtungPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph12.gka").toString());

        List<String> path = BFS.bfs(graph, "A", "C");

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("A", path.get(0));
        assertEquals("C", path.get(path.size() - 1));
        assertTrue(path.size() <= 3);
    }

    //EINFACHE GRAPHEN
    //UNGERICHTETE
    //OHNE GEWICHTUNG
//    @Test
//    public void testBfsSimpleUngerichteteGraphOhneGewichtungPathExists() throws IOException {  //Kein geeignetes Beispiel in der Testdatei gefunden
//        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph02.gka").toString());
//
//        List<String> path = BFS.bfs(graph, "a", "c");
//
//        assertNotNull(path, "Path sollte nicht null sein");
//        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
//        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
//        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
//        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
//    }

    //EINFACHE GRAPHEN
    //UNGERICHTETE
    //MIT GEWICHTUNG
    @Test
    public void testBfsSimpleUngerichteteGraphMitGewichtungPathExists() throws IOException { //CHECK
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph03.gka").toString());

        List<String> path = BFS.bfs(graph, "Soltau", "Hamburg");

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("Soltau", path.get(0));
        assertEquals("Hamburg", path.get(path.size() - 1));
        assertTrue(path.size() <= 3); // Soltau → Buxtehude → Hamburg oder Soltau → Lüneburg → Hamburg
    }


    //KOMPLEXE GRAPHEN
    //GERICHTETE
    //OHNE GEWICHTUNG
    @Test
    public void testBfsKomplexeGerichteteGraphOhneGewichtungPathExists() throws IOException { //CHECK
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString());

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("a", path.get(0));
        assertEquals("c", path.get(path.size() - 1));
    }

    //KOMPLEXE GRAPHEN
    //GERICHTETE
    //MIT GEWICHTUNG
//    @Test
//    public void testBfsKomplexeGerichteteGraphMitGewichtungPathExists() throws IOException { ////Kein geeignetes Beispiel in der Testdatei gefunden
//        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString());
//
//        List<String> path = BFS.bfs(graph, "a", "c");
//
//        assertNotNull(path, "Path sollte nicht null sein");
//        assertFalse(path.isEmpty(), "Path sollte nicht leer sein");
//        assertEquals("a", path.get(0), "Path beginnt bei 'a'");
//        assertEquals("c", path.get(path.size() - 1), "Path sollte bis zum 'c' Knoten gehen");
//        assertTrue(path.size() <= 3, "Path sollte maximal 3 Knoten und 2 Kanten haben)");
//    }

    //KOMPLEXE GRAPHEN
    //UNGERICHTETE
    //OHNE GEWICHTUNG
    @Test
    public void testBfsKomplexeUngerichteteGraphOhneGewichtungPathExists() throws IOException { //CHECK
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph02.gka").toString());

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("a", path.get(0));
        assertEquals("c", path.get(path.size() - 1));
    }

    //KOMPLEXE GRAPHEN
    //UNGERICHTETE
    //MIT GEWICHTUNG
    @Test
    public void testBfsKomplexeUngerichteteGraphMitGewichtungPathExists() throws IOException { //CHECK
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph13.gka").toString());

        List<String> path = BFS.bfs(graph, "A", "C");

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("A", path.get(0));
        assertEquals("C", path.get(path.size() - 1));
        assertTrue(path.size() <= 2); // z. B. A → C direkt über Kante x8
    }

    @Test
    public void testBfsSameStartAndGoal() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString());

        List<String> path = BFS.bfs(graph, "a", "a");

        assertNotNull(path, "Path sollte nicht null sein");
        assertEquals(1, path.size(), "Path sollte nur den ersten Knoten haben");
        assertEquals("a", path.get(0));
    }

    @Test
    public void testPathNotExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph06.gka").toString());

        List<String> path = BFS.bfs(graph, "1", "12");

        assertNotNull(path);
        assertTrue(path.isEmpty());
    }
}
