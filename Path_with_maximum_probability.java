import java.util.*;

class Pair{
    int node;
    double prob;
    Pair(int node, double prob){
        this.node = node;
        this.prob = prob;
    }

}

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            double cost = succProb[i];

            adj.get(u).add(new Pair(v,cost));
            adj.get(v).add(new Pair(u,cost));
        }

        double[] dist = new double[n];
        Arrays.fill(dist,0.0);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> Double.compare(b.prob, a.prob));

        pq.add(new Pair(start_node,1.0));
        dist[start_node] = 1.0;

        while(!pq.isEmpty()){
            Pair obj = pq.poll();
            int node = obj.node;
            double cost = obj.prob;

            if(node == end_node) return cost;
            for(Pair i : adj.get(node)){
                int v = i.node;
                double wt = i.prob;

                if(wt < dist[v]) continue;

                if(wt * cost > dist[v]){
                    dist[v] = wt*cost;
                    pq.add(new Pair(v,dist[v]));
                }
            }

        }


        return 0.0;

    }
}