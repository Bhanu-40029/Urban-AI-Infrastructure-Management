package DSA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Exceptions.RouteNotFoundException;

import java.util.PriorityQueue;
import java.util.Arrays;

public class Graph {

    private int vertices;
    
    private ArrayList<ArrayList<Edge>> adjList;
    private String[] cityNames = {
            "Indiranagar",
            "M.G. Road",
            "Koramangala",
            "Jayanagar",
            "Whitefield",
            "Hebbal",
            "Yeshwanthpur",
            "Electronic City",
            "Marathahalli",
            "HSR Layout"
    }
    ;

    public Graph(int vertices) {

        this.vertices = vertices;

        adjList = new ArrayList<>();

        for(int i = 0; i < vertices; i++) {

            adjList.add(new ArrayList<>());
        }
        
        
    }
    private void printPath(int[] parent, int vertex) {

        if(vertex == -1) {
            return;
        }

        printPath(parent, parent[vertex]);

        System.out.print(cityNames[vertex]);

        if(parent[vertex] != -1) {
            System.out.print(" -> ");
        }
    }

    // Add Road Connection
    public void addEdge(int source,
            int destination,
            int distance,
            int travelTime) {

adjList.get(source)
   .add(new Edge(destination,
                 distance,
                 travelTime));

adjList.get(destination)
   .add(new Edge(source,
                 distance,
                 travelTime));
}

    // Display Graph
    public void displayGraph() {

        System.out.println("\n========== CITY ROAD NETWORK ==========");

        for(int i = 0; i < vertices; i++) {

            System.out.print(cityNames[i] + " -> ");

            for(Edge edge : adjList.get(i)) {

                System.out.print("("
                        + cityNames[edge.destination]
                        + ", Distance="
                        + edge.distance
                        + " km"
                        + ", Time="
                        + edge.travelTime
                        + " min)   ");
            }

            System.out.println();
        }
    }

    // BFS
    public void bfs(int start) {

        boolean[] visited = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;

        queue.add(start);

        System.out.println("\n===== BFS Traversal =====");

        while(!queue.isEmpty()) {

            int current = queue.poll();

            System.out.print(cityNames[current]);

            if(!queue.isEmpty() || current != start)
                System.out.print(" -> ");

            for(Edge edge : adjList.get(current)) {

                if(!visited[edge.destination]) {

                    visited[edge.destination] = true;

                    queue.add(edge.destination);
                }
            }
        }

        System.out.println();
    }
    // DFS
    public void dfs(int start) {

        boolean[] visited = new boolean[vertices];

        System.out.println("\n===== DFS Traversal =====");

        dfsHelper(start, visited);

        System.out.println();
    }
    private void dfsHelper(int vertex,
            boolean[] visited) {

visited[vertex] = true;

System.out.print(cityNames[vertex] + " ");

for(Edge edge : adjList.get(vertex)) {

if(!visited[edge.destination]) {

 dfsHelper(edge.destination,
           visited);
    }
  }
}
    
    public void dijkstraDistance(int source, int destination)
            throws RouteNotFoundException {
           int[] distance = new int[vertices];
           int[] parent = new int[vertices];

           Arrays.fill(distance, Integer.MAX_VALUE);
           Arrays.fill(parent, -1);

           distance[source] = 0;

           PriorityQueue<int[]> pq =new PriorityQueue<>((a, b) -> a[1] - b[1]);

           pq.add(new int[] {source, 0});

           while (!pq.isEmpty()) {

               int[] current = pq.poll();

               int currentVertex = current[0];

               for (Edge edge :adjList.get(currentVertex)) {

               int newDistance = distance[currentVertex]+ edge.distance;

               if (newDistance <distance[edge.destination]) {

                   distance[edge.destination]= newDistance;

                   parent[edge.destination]= currentVertex;

                   pq.add(new int[] {edge.destination,newDistance});
        }
    }
}
           if(distance[destination] == Integer.MAX_VALUE) {

        	    throw new RouteNotFoundException(
        	            "No route exists between "
        	            + cityNames[source]
        	            + " and "
        	            + cityNames[destination]);
        	}

           System.out.println("\n===== SHORTEST DISTANCE ROUTE =====");

           System.out.println("Source      : " + cityNames[source]);
           System.out.println("Destination : " + cityNames[destination]);

           System.out.print("\nPath : ");

           printPath(parent, destination);

           System.out.println("\n\nTotal Distance = "
                   + distance[destination]
                   + " km");
}

    public void dijkstraTime(int source, int destination)
            throws RouteNotFoundException {
        int[] time = new int[vertices];
        int[] parent = new int[vertices];

        Arrays.fill(time, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        time[source] = 0;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int currentVertex = current[0];

            for (Edge edge : adjList.get(currentVertex)) {

                int newTime = time[currentVertex] + edge.travelTime;

                if (newTime < time[edge.destination]) {

                    time[edge.destination] = newTime;

                    parent[edge.destination] = currentVertex;

                    pq.add(new int[]{
                            edge.destination,
                            newTime
                    });
                }
            }
        }
        if(time[destination] == Integer.MAX_VALUE) {

            throw new RouteNotFoundException(
                    "No route exists between "
                    + cityNames[source]
                    + " and "
                    + cityNames[destination]);
        }
        System.out.println("\n===== FASTEST ROUTE =====");

        System.out.println("Source      : " + cityNames[source]);
        System.out.println("Destination : " + cityNames[destination]);

        System.out.print("\nPath : ");

        printPath(parent, destination);

        System.out.println("\n\nTotal Time = "
                + time[destination]
                + " minutes");
    }
    public String getCityName(int index) {

        return cityNames[index];
    }
    public int getVertices() {

        return vertices;
    }
    
}