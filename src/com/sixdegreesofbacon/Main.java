package com.sixdegreesofbacon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Main class that executes the Six Degrees of Kevin Bacon code.
 */
public class Main {

    /**
     * Given a .json file describing a movie, add the actors and their connections to the graph
     * @param mapper ObjectMapper object mapping .json to POJOs
     * @param graph Graph object representing all connections between actors
     * @param movieFile .json file describing one movie
     */
    private static void readMovieFile(ObjectMapper mapper, Graph graph, InputStream movieFile) {
        JsonNode root = null;
        try {
            root = mapper.readTree(movieFile);
            String filmName = root.get("film").get("name").asText();
            JsonNode castNode = root.get("cast");
            if (castNode.isArray()) {
                for (final JsonNode actor1 : castNode) {
                    Vertex actor1V = graph.getOrCreateVertex(actor1.get("name").asText());
                    for (final JsonNode actor2 : castNode) {
                        if (!actor1.get("name").asText().equals(actor2.get("name").asText())) {
                            Vertex actor2V = graph.getOrCreateVertex(actor2.get("name").asText());

                            //An "edge" between two actors is defined to be a film with a weight of 1
                            Edge connection = new Edge(actor1V, actor2V, filmName, 1);
                            actor1V.addConnectingEdge(connection);
                            actor2V.addConnectingEdge(connection);

                            graph.addVertex(actor2V);
                        }
                    }
                    graph.addVertex(actor1V);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        //Build the graph of actors
        Graph graph = new Graph();
        ObjectMapper mapper = new ObjectMapper();

        String dirPath = "resources/films";
        /**
         * Open the zipped json files in a .jar. Code adapted from
         * http://stackoverflow.com/questions/1429172/how-do-i-list-the-files-inside-a-jar-file
         */
        if(Main.class.getResource("Main.class").toString().startsWith("jar")) {
            CodeSource src = Main.class.getProtectionDomain().getCodeSource();
            if (src != null) {
                //Get the location of the jar itself
                URL jar = src.getLocation();

                //Open up the jar
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                ZipEntry entry;

                //Iterate through all entries in the jar, only reading those that end in .json
                while((entry = zip.getNextEntry()) != null) {
                    String name = entry.getName();
                    if (name.contains(dirPath) && name.endsWith("json")) {
                        InputStream input = Main.class.getResourceAsStream("/" + name);
                        readMovieFile(mapper, graph, input);
                    }
                }
            }
        } else {
            final File folder = new File(Main.class.getResource(dirPath).getPath());
            for (final File movieFile : folder.listFiles()) {
                InputStream input = new FileInputStream(movieFile.getPath());
                readMovieFile(mapper, graph, input);
            }
        }

        System.out.println(graph.getShortestPath(args[0], "Kevin Bacon"));
    }
}
