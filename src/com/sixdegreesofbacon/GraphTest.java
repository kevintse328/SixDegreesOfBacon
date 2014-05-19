package com.sixdegreesofbacon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GraphTest {

    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Vertex v4;
    private Vertex v5;

    private Edge e12;
    private Edge e13;
    private Edge e14;
    private Edge e23;
    private Edge e35;
    private Edge e45;

    private Graph g;

    @Before
    public void setUp() throws Exception {
        g = new Graph();

        v1 = new Vertex("1");
        v2 = new Vertex("2");
        v3 = new Vertex("3");
        v4 = new Vertex("4");
        v5 = new Vertex("5");

        e12 = new Edge(v1, v2, "Edge 1-2", 1);
        e13 = new Edge(v1, v3, "Edge 1-3", 3);
        e14 = new Edge(v1, v4, "Edge 1-4", 2);
        e23 = new Edge(v2, v3, "Edge 2-3", 1);
        e35 = new Edge(v3, v5, "Edge 3-5", 1);
        e45 = new Edge(v4, v5, "Edge 4-5", 3);

        v1.addConnectingEdge(e12);
        v1.addConnectingEdge(e13);
        v1.addConnectingEdge(e14);

        v2.addConnectingEdge(e12);
        v2.addConnectingEdge(e23);

        v3.addConnectingEdge(e13);
        v3.addConnectingEdge(e23);
        v3.addConnectingEdge(e35);

        v4.addConnectingEdge(e14);
        v4.addConnectingEdge(e45);

        v5.addConnectingEdge(e35);
        v5.addConnectingEdge(e45);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
    }

    @After
    public void tearDown() throws Exception {
        v1 = null;
        v2 = null;
        v3 = null;
        v4 = null;
        v5 = null;

        e12 = null;
        e13 = null;
        e14 = null;
        e23 = null;
        e35 = null;
        e45 = null;

        g = null;
    }

    @Test
    public void testAddVertex() throws Exception {
        Vertex v6 = new Vertex("6");
        g.addVertex(v6);
        assertEquals(v6, g.getOrCreateVertex("6"));
    }

    @Test
    public void testGetOrCreateVertex() throws Exception {
        assertEquals(v3, g.getOrCreateVertex("3"));
        assertEquals(new Vertex("6"), g.getOrCreateVertex("6"));
    }

    @Test
    public void testDijkstrasAlgorithm() throws Exception {
        assertNull(g.dijkstrasAlgorithm("1", "6"));
        Vertex destination = g.dijkstrasAlgorithm("1", "5");
        assertEquals(3, destination.getDistance());
    }

    @Test
    public void testGetShortestPath() throws Exception {
        assertEquals("No path found between 1 and 6", g.getShortestPath("1", "6"));
        assertEquals("1-(Edge 1-2)->2-(Edge 2-3)->3-(Edge 3-5)->5", g.getShortestPath("1", "5"));
    }
}