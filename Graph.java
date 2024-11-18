import java.util.Arrays;

public class Graph {
    // Class to represent an edge in the graph
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Comparator function to sort edges by weight
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    // Class to represent a subset for Union-Find
    class Subset {
        int parent, rank;
    }

    int vertices, edges; // Number of vertices and edges
    Edge[] edge; // Array of edges

    // Constructor to create a graph
    public Graph(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edge = new Edge[edges];
        for (int i = 0; i < edges; ++i)
            edge[i] = new Edge();
    }

    // Find set of an element using path compression
    int find(Subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // Perform Union of two sets by rank
    void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Function to apply Kruskal's algorithm
    void kruskalAlgorithm() {
        Edge[] result = new Edge[vertices]; // Result array to store MST
        int minCost = 0; // Total cost of MST
        int e = 0; // Index for result array
        int i = 0; // Index for sorted edges

        for (i = 0; i < vertices; ++i)
            result[i] = new Edge();

        // Step 1: Sort all edges by weight
        Arrays.sort(edge);

        // Allocate memory for subsets
        Subset[] subsets = new Subset[vertices];
        for (i = 0; i < vertices; ++i)
            subsets[i] = new Subset();

        // Initialize subsets
        for (int v = 0; v < vertices; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        // Step 2: Pick edges one by one from sorted array
        i = 0;
        while (e < vertices - 1) {
            Edge nextEdge = edge[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            // If including this edge doesn't cause a cycle, include it
            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        // Print the resulting MST
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minCost += result[i].weight;
        }
        System.out.println("Total cost of MST: " + minCost);
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        int vertices = 6; // Number of vertices
        int edges = 8;    // Number of edges
        Graph graph = new Graph(vertices, edges);

        // Define the edges with source, destination, and weight
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 4;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 2;

        graph.edge[3].src = 2;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 3;

        graph.edge[4].src = 2;
        graph.edge[4].dest = 5;
        graph.edge[4].weight = 2;

        graph.edge[5].src = 2;
        graph.edge[5].dest = 4;
        graph.edge[5].weight = 4;

        graph.edge[6].src = 3;
        graph.edge[6].dest = 4;
        graph.edge[6].weight = 3;

        graph.edge[7].src = 5;
        graph.edge[7].dest = 4;
        graph.edge[7].weight = 3;

        // Run Kruskal's algorithm
        graph.kruskalAlgorithm();
    }
}
