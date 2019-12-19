package lab4.code;

import java.util.*;
import java.util.stream.Collectors;


public class PathFinder<V> {

    private DirectedGraph<V> graph;
    private long startTimeMillis;


    public PathFinder(DirectedGraph<V> graph) {
        this.graph = graph;
    }


    public class Result<V> {
        public final boolean success;
        public final V start;
        public final V goal;
        public final double cost;
        public final List<V> path;
        public final int visitedNodes;
        public final double elapsedTime;

        public Result(boolean success, V start, V goal, double cost, List<V> path, int visitedNodes) {
            this.success = success;
            this.start = start;
            this.goal = goal;
            this.cost = cost;
            this.path = path;
            this.visitedNodes = visitedNodes;
            this.elapsedTime = (System.currentTimeMillis() - startTimeMillis) / 1000.0;
        }

        public String toString() {
            String s = "";
            s += String.format("Visited nodes: %d\n", visitedNodes);
            s += String.format("Elapsed time: %.1f seconds\n", elapsedTime);
            if (success) {
                s += String.format("Total cost from %s -> %s: %s\n", start, goal, cost);
                s += "Path: " + path.stream().map(x -> x.toString()).collect(Collectors.joining(" -> "));
            } else {
                s += String.format("No path found from %s", start);
            }
            return s;
        }
    }


    public Result<V> search(String algorithm, V start, V goal) {
        startTimeMillis = System.currentTimeMillis();
        switch (algorithm) {
            case "random":
                return searchRandom(start, goal);
            case "dijkstra":
                return searchDijkstra(start, goal);
            case "astar":
                return searchAstar(start, goal);
        }
        throw new IllegalArgumentException("Unknown search algorithm: " + algorithm);
    }


    public Result<V> searchRandom(V start, V goal) {
        int visitedNodes = 0;
        LinkedList<V> path = new LinkedList<>();
        double cost = 0.0;
        Random random = new Random();

        V current = start;
        path.add(current);
        while (current != null) {
            visitedNodes++;
            if (current.equals(goal)) {
                return new Result<>(true, start, current, cost, path, visitedNodes);
            }

            List<DirectedEdge<V>> neighbours = graph.outgoingEdges(start);
            if (neighbours == null || neighbours.size() == 0) {
                break;
            } else {
                DirectedEdge<V> edge = neighbours.get(random.nextInt(neighbours.size()));
                cost += edge.weight();
                current = edge.to();
                path.add(current);
            }
        }
        return new Result<>(false, start, null, -1, null, visitedNodes);
    }


    public Result<V> searchDijkstra(V start, V goal) {
        HashMap<V, Double> distTo = new HashMap<>();
        Comparator<V> comparator = Comparator.comparing(distTo::get);
        return commonSearch(start, goal, comparator, distTo);
    }


    public Result<V> searchAstar(V start, V goal) {
        HashMap<V, Double> distTo = new HashMap<>();
        Comparator<V> comparator = Comparator.comparingDouble(o -> distTo.get(o) + graph.guessCost(o, goal));
        return commonSearch(start, goal, comparator, distTo);
    }


    public Result<V> commonSearch(V start, V goal, Comparator comparator, HashMap<V, Double> distTo) {
        int visitedNodes = 0;
        HashMap<V, DirectedEdge<V>> edgeTo = new HashMap<>();

        Queue<V> pq = new PriorityQueue<>(comparator);
        List<V> bestPath = new ArrayList<>();
        Set<V> visited = new HashSet<>();

        pq.add(start);
        distTo.put(start, 0.0);

        while (!pq.isEmpty()) {
            V currentV = pq.poll();
            visitedNodes++;

            if (!visited.contains(currentV)) {
                visited.add(currentV);
                if (currentV.toString().compareTo(goal.toString()) == 0) {
                    while (goal != start) {
                        bestPath.add(goal);
                        goal = edgeTo.get(goal).from();
                    }
                    bestPath.add(start);
                    Collections.reverse(bestPath);

                    Double cost = distTo.get(currentV);

                    return new Result<>(true, start, goal, cost, bestPath, visitedNodes);
                }
                for (DirectedEdge<V> edge : graph.outgoingEdges(currentV)) {
                    V w = edge.to();
                    double newDist = distTo.get(currentV) + edge.weight();
                    if (distTo.containsKey(w) && !(distTo.get(w) > newDist)) {
                        continue;
                    }

                    distTo.put(w, newDist);
                    edgeTo.put(w, edge);
                    pq.add(w);
                }
            }
        }
        return new Result<>(false, start, null, -1, null, visitedNodes);
    }
}
