// We will detect the cycle in a directed graph using dfs
import java.util.*;

public class Cycle_in_directedGraph_DFS {
    static boolean[] vis;
    static boolean[] inrec; // in recurssion


    static boolean dfs(List<List<Integer>> adj, int start){
        vis[start] = true;
        inrec[start] = true;
        
        for(int node : adj.get(start)){
            if(!vis[node]){
                if(dfs(adj,node)) return true;
            }else if(inrec[node]){
                return true;
            }
        }
        
        inrec[start] = false;
        return false;

    }
    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();

        int n = 5;
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(3);
        adj.get(3).add(0);
        adj.get(3).add(4);


        vis = new boolean[n];
        inrec = new boolean[n];

        if(dfs(adj,0)){
            System.out.println("Cycle exists");
        }else{
            System.out.println("No cycle found");
        }
              
    }
}
