package com.sixdegreesofbacon;

import java.util.*;

/**
 * Class representing a basic Graph consisting of Edge and Vertex objects.
 */
public class Graph {
    /* Store the vertices as a Map from vertex title to the Vertex object for easy lookup. */
    private Map<String, Vertex> vertices;

    /**
     * Constructor for a Graph object.
     */
    public Graph() {
        vertices = new HashMap<String, Vertex>();
    }

    /**
     * Adds a vertex to the list of vertices. Re-adding the same vertex will override the old one.
     * @param v Vertex to add to the list of the vertices
     */
    public void addVertex(Vertex v) {
        vertices.put(v.getVertexInfo(), v);
    }

    /**
     * Returns a vertex, creating it if it doesn't exist.
     * @param vertexInfo a String representing the description of the vertex
     * @return The vertex corresponding to the description.
     */
    public Vertex getOrCreateVertex(String vertexInfo) {
        if (!vertices.containsKey(vertexInfo))
            vertices.put(vertexInfo, new Vertex(vertexInfo));
        return vertices.get(vertexInfo);
    }

    /**
     * Implementation of Dijkstra's Algorithm to find the shortest path between two vertices.
     * @param origin String representing the description of the origin vertex.
     * @param destination String representing the description of the destination vertex.
     * @return the destination vertex containing the minimum distance to reach it and the edges along that path.
     */
    public Vertex dijkstrasAlgorithm(String origin, String destination) {
        if (!(vertices.containsKey(origin) && vertices.containsKey(destination)))
            return null;

        Set<Vertex> visited = new HashSet<Vertex>();
        PriorityQueue<Vertex> unvisited = new PriorityQueue<Vertex>();
        Vertex originVertex = vertices.get(origin);
        originVertex.setDistance(0);
        unvisited.add(originVertex);

        Vertex currVertex;
        while ((currVertex = unvisited.poll()) != null) {
            if (!visited.contains(currVertex)) {
                visited.add(currVertex);
                if (currVertex.getVertexInfo().equals(destination))
                    return currVertex;

                for (Edge connEdge : currVertex.getConnEdges()) {
                    Vertex neighbour = connEdge.getOtherVertex(currVertex);
                    if (!visited.contains(neighbour)) {
                        int newDist = currVertex.getDistance() + connEdge.getWeight();
                        if (newDist < neighbour.getDistance()) {
                            //Make a copy of the current vertex instead of modify the one currently in the PQ
                            Vertex updated = new Vertex(neighbour);

                            //Update the distance to be the minimum found
                            updated.setDistance(newDist);

                            //Update the edges that reference both the current and updated vertex
                            connEdge.setOtherVertex(updated, currVertex);
                            connEdge.setOtherVertex(currVertex, updated);

                            //Keep track of how you ended up here
                            updated.setShortestPathEdge(connEdge);
                            unvisited.add(updated);
                        }
                    }

                }

            }
        }

        return null;
    }

    /**
     * Prints out the shortest path between two nodes. Relies on #dijkstrasAlgorithm(String origin, String destination).
     *
     * @param origin String representing the description of the origin vertex.
     * @param destination String representing the description of the destination vertex.
     * @return a String representing the shortest path between origin and destination.
     *
     * @see #dijkstrasAlgorithm(String origin, String destination)
     */
    public String getShortestPath(String origin, String destination) {
        StringBuilder path = new StringBuilder();

        Vertex currNode = dijkstrasAlgorithm(origin, destination);
        if (currNode == null)
            return "No path found between " + origin + " and " + destination;

        while (currNode.getShortestPathEdge() != null) {
            path.insert(0, currNode.getVertexInfo());
            path.insert(0, "-(" + currNode.getShortestPathEdge().getEdgeInfo() + ")->");
            currNode = currNode.getShortestPathEdge().getOtherVertex(currNode);
        }

        path.insert(0, currNode.getVertexInfo());

        return path.toString();
    }

}
