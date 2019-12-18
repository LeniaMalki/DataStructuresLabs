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
        int visitedNodes = 0;
        HashMap edgeTo = new HashMap<V, V>();
        HashMap distTo = new HashMap<V, Integer>();
        PriorityQueue<V> pq = new PriorityQueue<>();
        List<V> bestPath = new ArrayList<>();
        Set visited = new HashSet();

        pq.add(start);
        distTo.put(start, 0.0);

        while (!pq.isEmpty()) {
            V v = pq.poll();
            visitedNodes++;

            if (!visited.contains(v)) {
                visited.add(v);
                //System.out.println(v.toString() + " "+goal.toString());
                if (v.toString().compareTo(goal.toString()) == 0) {
                    // TODO calculate path and cost and return them
                    Iterator iterableList = edgeTo.entrySet().iterator();
                    while (iterableList.hasNext()) {
                        V dEdge = (V) iterableList.next();
                        if (dEdge.toString().compareTo(v.toString()) == 0) {
                            bestPath.add(dEdge);
                        }
                    }
                    Double cost = (Double) distTo.get(v);

                    return new Result<>(true, start, goal, cost, bestPath, visitedNodes);
                }
                for (DirectedEdge edge : graph.outgoingEdges(v)) {
                    V w = (V) edge.to();
                    double newDist = (double) distTo.get(v) + edge.weight();
                    if (distTo.containsKey(w)) {
                        if ((double) distTo.get(w) > newDist) {
                            distTo.put(w, newDist);
                            edgeTo.put(w, edge);
                            pq.add(w);
                        }
                    } else {
                        distTo.put(w, newDist);
                        edgeTo.put(w, edge);
                        pq.add(w);
                    }
                    //System.out.println(v.toString() + " lalla " + newDist + " edge: " + edge.toString() +"\n     " + " baloo is !bae " + distTo.toString());
                }
            }
        }
        return new Result<>(false, start, null, -1, null, visitedNodes);
    }


    public Result<V> searchAstar(V start, V goal) {
        int visitedNodes = 0;
        /********************
         * TODO: Task 3
         ********************/
        return new Result<>(false, start, null, -1, null, visitedNodes);
    }

}
