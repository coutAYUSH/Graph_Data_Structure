import java.util.*;


class Solution {
    List<Integer> items;
    List<Integer> grpitems;
    int[] indeg;
    int[] indeg2;
    
    void topoSort(int n, List<List<Integer>> adj){
         Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<n;i++){
            if(indeg[i] ==0){
                q.add(i);
            }
        }
       
        while(!q.isEmpty()){
            int node = q.poll();
            items.add(node);
            for(int i : adj.get(node)){
                indeg[i]--;
                if(indeg[i] ==0){
                    q.add(i);
                }
            }
        }
    }


    void topoSort2(int m,List<Set<Integer>> gadj){
        Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<=m;i++){
            if(indeg2[i] ==0){
                q.add(i);
            }
        }
       
        while(!q.isEmpty()){
            int node = q.poll();
            grpitems.add(node);
            for(int i : gadj.get(node)){
                indeg2[i]--;
                if(indeg2[i] ==0){
                    q.add(i);
                }
            }
        }
    }


    
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        for(int i=0;i<n;i++){
            if(group[i] == -1){
                group[i] = m++;
            }
        }

        items = new ArrayList<>();
        grpitems = new ArrayList<>();
       
       // Create group for items
       List<List<Integer>> adj = new ArrayList<>();
       
       for(int i=0;i<n;i++){
        adj.add(new ArrayList<>());
       }
       
       indeg = new int[n];
       for(int i=0;i<n;i++){
        for(int j : beforeItems.get(i)){
            adj.get(j).add(i);
            indeg[i]++;
        }
       }

       // Create graph for groups
      List<Set<Integer>> gadj = new ArrayList<>();
      indeg2 = new int[m+1];
      for(int i=0;i<=m;i++){
        gadj.add(new HashSet<>());
      }


      for(int i=0;i<n;i++){
        for(int j : beforeItems.get(i)){
            if (group[i] != group[j]) {
                    if (!gadj.get(group[j]).contains(group[i])) {
                        gadj.get(group[j]).add(group[i]);
                        indeg2[group[i]]++;
                    }
                }
        
        }
      }

      topoSort(n, adj);
        topoSort2(m, gadj);

    if (items.size() < n || grpitems.size() < m) {
        return new int[0]; // cycle detected
        }


      
     Map<Integer, List<Integer>> grouped = new HashMap<>();
    for (int i = 0; i < m; i++) {
     grouped.put(i, new ArrayList<>());
    }


    for (int item : items) {
    grouped.get(group[item]).add(item);
   }
    

   List<Integer> res = new ArrayList<>();
    for (int g : grpitems) {
    res.addAll(grouped.getOrDefault(g, new ArrayList<>()));
   }


   return res.stream().mapToInt(i -> i).toArray();


    }
}