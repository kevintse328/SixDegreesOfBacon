package com.sixdegreesofbacon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class VertexTest {

    private Vertex v1;

    @Before
    public void setUp() throws Exception {
        v1 = new Vertex("Vertex 1");
    }

    @After
    public void tearDown() throws Exception {
        v1 = null;
    }

    @Test
    public void testGetVertexInfo() throws Exception {
        assertEquals("Vertex 1", v1.getVertexInfo());
    }

    @Test
    public void testGetConnEdges() throws Exception {
        assertEquals("Vertex 1", v1.getVertexInfo());
    }

    @Test
    public void testGetDistance() throws Exception {
        assertEquals(Integer.MAX_VALUE, v1.getDistance());
    }

    @Test
    public void testSetDistance() throws Exception {
        v1.setDistance(1);
        assertEquals(1, v1.getDistance());
    }

    @Test
    public void testGetConnectingEdge() throws Exception {
        assertNull(v1.getShortestPathEdge());
    }

    @Test
    public void testSetConnectingEdge() throws Exception {
        Vertex v2 = new Vertex("Vertex 2");
        Edge e = new Edge(v1, v2, "Test", 1);

        v1.setShortestPathEdge(e);
        assertEquals(e, v1.getShortestPathEdge());
    }

    @Test
    public void testAddConnectingEdge() throws Exception {
        Vertex v2 = new Vertex("Vertex 2");
        Edge e = new Edge(v1, v2, "Test", 1);
        assertThat(v1.getConnEdges(), not(hasItem(e)));
        v1.addConnectingEdge(e);
        assertThat(v1.getConnEdges(), hasItem(e));
    }

    @Test
    public void testEquals() throws Exception {
        Vertex v2 = new Vertex("Vertex 1");
        assertEquals(v1, v2);
    }

    @Test
    public void testHashCode() throws Exception {
        Vertex v2 = new Vertex("Vertex 1");
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    public void testCompareTo() throws Exception {
        Vertex v2 = new Vertex("Vertex 1");
        assertEquals(0, v1.compareTo(v2));
        assertEquals(0, v2.compareTo(v1));

        v2.setDistance(1);
        assertTrue(v1.compareTo(v2) > 0);
        assertTrue(v2.compareTo(v1) < 0);
    }
}