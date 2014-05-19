package com.sixdegreesofbacon;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vertex in a graph. It keeps track of:
 * <ul>
 *     <li>a String description of itself</li>
 *     <li>a List of connecting edges</li>
 * </ul>
 *
 * In addition, the vertex keeps track of the following info for find the shortest path through a graph:
 *
 * <ul>
 *     <li>the distance from some source node</li>
 *     <li>the connecting edge from some node to it</li>
 * </ul>
 */
public class Vertex implements Comparable {

    private String vertexInfo;
    private List<Edge> connEdges;
    private int distance;
    private Edge shortestPathEdge;

    /**
     * Constructor for creating a vertex given just a description.
     * @param vertexInfo A description of the vertex.
     */
    public Vertex(String vertexInfo) {
        this.vertexInfo = vertexInfo;
        connEdges = new ArrayList<Edge>();

        //Initialize distance to destination as infinity
        distance = Integer.MAX_VALUE;
    }

    /**
     * Clone a vertex object.
     * @param otherVertex The vertex to clone.
     */
    public Vertex(Vertex otherVertex) {
        vertexInfo = otherVertex.getVertexInfo();
        connEdges = otherVertex.getConnEdges();
        distance = otherVertex.getDistance();
        shortestPathEdge = otherVertex.getShortestPathEdge();
    }

    /**
     * Get the description of the vertex.
     * @return The description of the vertex.
     */
    public String getVertexInfo() {
        return vertexInfo;
    }

    /**
     * Get the distance from this vertex to the source node.
     * @return The distance from this vertex to the source node.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Set the distance from this vertex to the source node.
     * @param distance The new distance from this vertex to the source node.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Get the edge connecting this vertex to another vertex on the shortest path to the source node.
     * @return Edge connecting this vertex to another vertex on the shortest path to the source node.
     */
    public Edge getShortestPathEdge() {
        return shortestPathEdge;
    }

    /**
     * Set the edge connecting this vertex to another vertex on the shortest path to the source node.
     * @param shortestPathEdge Edge connecting this vertex to another vertex on the shortest path to the source node.
     */
    public void setShortestPathEdge(Edge shortestPathEdge) {
        this.shortestPathEdge = shortestPathEdge;
    }

    /**
     * Add an edge connecting this vertex to another vertex. This does not necessarily have to be on the shortest path.
     * @param connectingEdge Edge connecting this vertex to another vertex.
     */
    public void addConnectingEdge(Edge connectingEdge) {
        this.connEdges.add(connectingEdge);
    }

    /**
     * Get all edges from this vertex.
     * @return the list of edges from this vertex.
     */
    public List<Edge> getConnEdges() {
        return connEdges;
    }


    /**
     * Checks if an object is equal to this vertex. Note that this only compares the vertexInfo field and nothing else.
     * This means that the comparesTo function may not return 0 if two Vertex objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (vertexInfo != null ? !vertexInfo.equals(vertex.vertexInfo) : vertex.vertexInfo != null) return false;

        return true;
    }


    /**
     * Redefined hashCode function based on the vertexInfo field alone.
     *
     * @see #equals(Object o)
     */
    @Override
    public int hashCode() {
        int result = vertexInfo != null ? vertexInfo.hashCode() : 0;
        return result;
    }


    /**
     * Compares two Vertex objects. Unlike equals(), this compares both distance and the vertexInfo field.
     * This means that if v1.equals(v2) == true, v1.compareTo(v2) may not necessarily equal 0.
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Vertex))
            return -1;
        Vertex otherVertex = (Vertex) o;
        int cmp = this.distance - otherVertex.getDistance();
        return cmp == 0 ? this.vertexInfo.compareTo(otherVertex.getVertexInfo()) : cmp;
    }
}
