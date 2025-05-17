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

    @Test
    public void testBfsSimpleGraphPathExists() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString());

        List<String> path = BFS.bfs(graph, "a", "c");

        assertNotNull(path, "Path should not be null");
        assertFalse(path.isEmpty(), "Path should not be empty");
        assertEquals("a", path.get(0), "Path should start at 'a'");
        assertEquals("c", path.get(path.size() - 1), "Path should end at 'c'");
        assertTrue(path.size() <= 3, "Path should have max 3 nodes (2 edges)");
    }

    @Test
    public void testBfsSimpleGraphNoPath() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph06.gka").toString());

        List<String> path = BFS.bfs(graph, "v1", "v100");

        assertNotNull(path, "Path should not be null");
        assertTrue(path.isEmpty(), "There should be no path between v1 and v100");
    }

    @Test
    public void testBfsLargeCityGraph() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("cityGraph.gka").toString());

        List<String> path = BFS.bfs(graph, "Münster", "Cuxhaven");

        assertNotNull(path, "Path should not be null");
        assertFalse(path.isEmpty(), "Path should exist between Münster and Cuxhaven");
        assertEquals("Münster", path.get(0));
        assertEquals("Cuxhaven", path.get(path.size() - 1));
    }

    @Test
    public void testBfsSameStartAndGoal() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph01.gka").toString());

        List<String> path = BFS.bfs(graph, "a", "a");

        assertNotNull(path, "Path should not be null");
        assertEquals(1, path.size(), "Path should only contain the start node");
        assertEquals("a", path.get(0));
    }

    @Test
    public void testBfsComplexGraphCorrectPath() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph07.gka").toString());

        List<String> path = BFS.bfs(graph, "q", "v3");

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("q", path.get(0));
        assertEquals("v3", path.get(path.size() - 1));

        // Optional: Print the path for visual verification
        System.out.println("BFS path from q to v3: " + path);
    }

    @Test
    public void testBfsDisconnectedGraph() throws IOException {
        Graph graph = GraphIO.readFromFile(BASE_PATH.resolve("graph_disconnected.gka").toString());

        List<String> path = BFS.bfs(graph, "A", "Z");

        assertNotNull(path);
        assertTrue(path.isEmpty(), "Should return empty path when no connection exists");
    }
}
