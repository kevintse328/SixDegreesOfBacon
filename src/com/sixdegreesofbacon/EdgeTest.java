package com.sixdegreesofbacon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeTest {

    private Vertex v1;
    private Vertex v2;
    private Edge e;

    @Before
    public void setUp() throws Exception {
        v1 = new Vertex("1");
        v2 = new Vertex("2");
        e = new Edge(v1, v2, "Edge 1-2", 1);
    }

    @After
    public void tearDown() throws Exception {
        v1 = null;
        v2 = null;
        e = null;
    }

    @Test
    public void testGetOtherVertex() throws Exception {
        assertEquals(v2, e.getOtherVertex(v1));
        assertEquals(v1, e.getOtherVertex(v2));
    }

    @Test
    public void testSetOtherVertex() throws Exception {
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");

        e.setOtherVertex(v3, v1);
        assertEquals(v3, e.getOtherVertex(v1));

        e.setOtherVertex(v4, v3);
        assertEquals(v4, e.getOtherVertex(v3));
    }

    @Test
    public void testGetEdgeInfo() throws Exception {
        assertEquals("Edge 1-2", e.getEdgeInfo());
    }

    @Test
    public void testGetWeight() throws Exception {
        assertEquals(1, e.getWeight());
    }

    @Test
    public void testSetWeight() throws Exception {
        e.setWeight(2);
        assertEquals(2, e.getWeight());
    }
}