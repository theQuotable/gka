package graph;

import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GraphIOTest {

    @Test
    public void testReadGraph01() throws IOException {
        Graph graph = GraphIO.readFromFile("src/test/resources/graph01.gka");

        assertNotNull(graph);
        assertEquals(3, graph.getNodeCount());
        assertEquals(3, graph.getEdgeCount());
    }

    @Test
    public void testSaveAndReloadGraph() throws IOException {
        Graph original = GraphIO.readFromFile("src/test/resources/graph07.gka");

        Path tempFile = Files.createTempFile("graph-test-", ".gka");
        GraphIO.saveToFile(original, tempFile.toString(), true);

        Graph reloaded = GraphIO.readFromFile(tempFile.toString());

        assertEquals(original.getNodeCount(), reloaded.getNodeCount());
        assertEquals(original.getEdgeCount(), reloaded.getEdgeCount());

        Files.deleteIfExists(tempFile);
    }
}
