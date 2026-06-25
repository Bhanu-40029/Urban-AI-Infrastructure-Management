package DSA;

public class Edge {

    int destination;

    int distance;

    int travelTime;

    public Edge(int destination,
                int distance,
                int travelTime) {

        this.destination = destination;
        this.distance = distance;
        this.travelTime = travelTime;
    }
}