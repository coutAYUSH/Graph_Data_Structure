
import java.util.*;
class Solution {
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        int[] indeg = new int[V];
        
        for(int[] i : edges){
            adj.get(i[0]).add(i[1]);
            indeg[i[1]]++;
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0;i<V;i++){
            if(indeg[i] ==0){
                q.add(i);
            }
        }
        
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int node = q.poll();
                ans.add(node);
                for(int e : adj.get(node)){
                    indeg[e]--;
                    if(indeg[e] == 0){
                        q.add(e);
                    }
                }
            }
            
        }
        
        
        return ans;
        
        
        
    }
}