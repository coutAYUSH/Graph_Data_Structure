class Solution {

    boolean find(List<List<Integer>> adj,int i, int j){
        for(int node : adj.get(j)){
            if(node == i) return true;
        }

        return false;
    }
    public int maximalNetworkRank(int n, int[][] roads) {
        List<List<Integer>> adj = new ArrayList<>();

         for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
         }

         for(int[] i : roads){
            adj.get(i[0]).add(i[1]);
            adj.get(i[1]).add(i[0]);
         }
         
         int ans =0;
         for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int rank_i = adj.get(i).size();
                int rank_j = adj.get(j).size();

               int total = rank_i + rank_j;
                if(find(adj,i,j)){
                    total = total -1;
                }

                ans = Math.max(ans,total);
            }
         }

         return ans;
    }
}