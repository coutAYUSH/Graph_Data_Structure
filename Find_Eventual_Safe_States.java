import java.util.*;

class Solution {
    boolean[] vis;
    boolean[] inrec;

    boolean dfs(int[][] adj, int start){
        vis[start] = true;
        inrec[start] = true;

        for(int i : adj[start]){
            if(!vis[i]){
                if(dfs(adj,i)) return true;
            }else if(inrec[i]){
                return true;
            }
        }

        inrec[start] = false;

        return false;
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        vis = new boolean[n];
        inrec = new boolean[n];

       for(int i=0;i<n;i++){
        if(!vis[i]){
            dfs(graph,i);
        }
       }

      List<Integer> ans = new ArrayList<>();

      for(int i=0;i<n;i++){
        if(inrec[i]==false){
            ans.add(i);
        }
      }
        
    return ans;
    }
}