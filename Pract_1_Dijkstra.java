import java.util.*;
public class Pract_1_Dijkstra {
    private static int[][]graph;
    private static int vertices;
    public static void addEdge(int u, int v, int weight){
         graph[u][v]=weight;
         graph[v][u]=weight;
    }
    public static void dijkstra(int src, int dest){
        int[]dist=new int[vertices];
        boolean[]visited=new boolean[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src]=0;
        for(int i=0;i<vertices-1;i++){
            int u=findMin(dist, visited);
            visited[u]=true;
            for(int v=0;v<vertices;v++){
                if(!visited[v]&& graph[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+graph[u][v]<dist[v]){
                    dist[v]=dist[u]+graph[u][v];
                }
            }
        }
        System.out.println("Shortest Distance from source to destination is: "+dist[dest]);
    }
    public static int findMin(int[]dist, boolean []visited){
        int min=Integer.MAX_VALUE, minIndex=-1;
        for(int i=0;i<dist.length;i++){
            if(!visited[i] && dist[i]<min){
                min=dist[i];
                minIndex=i;
            }
        }
        return minIndex;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        vertices=sc.nextInt();  
        graph=new int[vertices][vertices];
        while(true){
            System.out.println("\nMenu");
            System.out.println("1. Add edge");
            System.out.println("2. Find Shortest Path (Dijkstra)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter source, destination, weight");
                    int u=sc.nextInt();
                    int v=sc.nextInt();
                    int weight=sc.nextInt(); 
                    addEdge(u, v, weight);
                    break;
                    case 2:
                    System.out.println("Enter source and destination:");
                    int src = sc.nextInt();
                    int dest = sc.nextInt();
                    dijkstra(src, dest);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    break;
            }
        }
    }
}

