package com.sixdegreesofbacon;

/**
 * Represents an edge in a graph. It has four main components:
 * <ul>
 *     <li>two vertices on each side of the edge</li>
 *     <li>a string representing a description of what the edge is</li>
 *     <li>a numerical weight of the edge</li>
 * </ul>
 */
public class Edge {
    private Vertex v1;
    private Vertex v2;
    private String edgeInfo;
    private int weight;

    /**
     * Constructor for an edge object.
     *
     * @param v1       Vertex on one end of the edge.
     * @param v2       Vertex on the other end of the edge.
     * @param edgeInfo String describing what the edge is.
     * @param weight   Weight of the edge.
     */
    public Edge(Vertex v1, Vertex v2, String edgeInfo, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.edgeInfo = edgeInfo;
        this.weight = weight;
    }

    /**
     * Given one vertex, get the vertex on the other side of the edge.
     *
     * @param v The starting vertex of the edge.
     * @return The other side of the edge.
     */
    public Vertex getOtherVertex(Vertex v) {
        if (v == null) return null;
        if (v.equals(v1) || v.equals(v2)) {
            if (v.equals(v1)) return v2;
            else if (v.equals(v2)) return v1;
        }
        return null;
    }

    /**
     * Given one vertex, set the vertex on the other side of the edge.
     *
     * @param newV The new vertex to set.
     * @param v    The starting vertex of the edge.
     */
    public void setOtherVertex(Vertex newV, Vertex v) {
        if (v != null && newV != null) {
            if (v.equals(v1) || v.equals(v2)) {
                if (v.equals(v1)) v2 = newV;
                else if (v.equals(v2)) v1 = newV;
            }
        }

    }

    /**
     * Get the description of the edge.
     *
     * @return The edge description.
     */
    public String getEdgeInfo() {
        return edgeInfo;
    }

    /**
     * Get the weight of the edge.
     *
     * @return The weight of the edge.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set the weight of the edge.
     *
     * @param weight The new weight of the edge.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
