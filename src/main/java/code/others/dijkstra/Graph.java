package code.others.dijkstra;

import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * 由顶点名 顶点组合的Map确定的Graph 二叉树堆 Dijkstra
 *
 * @author JIll Wang
 * @date 2020-07-02 12:17
 **/
class Graph {

    private final HashMap<String, Vertex> stringVertexHashMap;
// mapping of vertex names to Vertex objects, built from a set of Edges

    /**
     * Builds a graph from a set of edges
     */
    public Graph(Edge[] edges) {
        stringVertexHashMap = new HashMap<>(edges.length);

//one pass to find all vertices
        for (Edge e : edges) {
            if (!stringVertexHashMap.containsKey(e.v1)) {
                stringVertexHashMap.put(e.v1, new Vertex(e.v1));
            }
            if (!stringVertexHashMap.containsKey(e.v2)) {
                stringVertexHashMap.put(e.v2, new Vertex(e.v2));
            }
        }

//another pass to set neighbouring vertices
        for (Edge e : edges) {

            stringVertexHashMap.get(e.v1).neighbours.put(stringVertexHashMap.get(e.v2), e.dist);
//graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    /**
     * Runs dijkstra using a specified source vertex
     */
    public void dijkstra(String startName) {
        if (!stringVertexHashMap.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"%n", startName);
//Graph doesn't contain start vertex "q"
            return;
        }
        final Vertex source = stringVertexHashMap.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

// set-up vertices
        for (Vertex v : stringVertexHashMap.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    /**
     * Implementation of dijkstra's algorithm using a binary heap.
     */
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u;
        Vertex v;
        while (!q.isEmpty()) {
// vertex with shortest distance (first iteration will return source)删除最低的元素
            u = q.pollFirst();
// we can ignore u (and any other remaining vertices) since they are unreachable
            if (Objects.requireNonNull(u).dist == Integer.MAX_VALUE) {
                break;
            }

//look at distances to each neighbour
            for (HashMap.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                //the neighbour in this iteration
                v = a.getKey();

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    // shorter path to neighbour found
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    /**
     * Prints a path from the source to the specified vertex
     */
    public void printPath(String endName) {
        if (!stringVertexHashMap.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\" %n", endName);
            return;
        }

        stringVertexHashMap.get(endName).printPath();
        System.out.println();
    }

    /**
     * Prints the path from the source to every vertex (output order is not guaranteed)
     */
    public void printAllPaths() {
        for (Vertex v : stringVertexHashMap.values()) {
            v.printPath();
            System.out.println();
        }
    }

    /**
     * One edge of the graph (only used by Graph constructor)
     */
    public static class Edge {
        public final String v1;
        public final String v2;
        public final int dist;

        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    /**
     * One vertex of the graph, complete with mappings to neighbouring vertices
     */
    @EqualsAndHashCode
    public static class Vertex implements Comparable<Vertex> {
        public final String name;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();
        public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;//previous

        public Vertex(String name) {
            this.name = name;
        }

        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else {
                if (this.previous == null) {
                    System.out.printf("%s(unreached)", this.name);
                } else {
                    this.previous.printPath();
                    System.out.printf(" -> %s(%d)", this.name, this.dist);
                }
            }
        }

        @Override
        public int compareTo(Vertex other) {
            if (dist == other.dist) {
                return name.compareTo(other.name);
            }

            return Integer.compare(dist, other.dist);
        }

        @Override
        public String toString() {
            return "(" + name + ", " + dist + ")";
        }
    }
}
