
import java.util.*; 

class Pair{
    String ch;
    double val;
    Pair(String ch, double val){
        this.ch = ch;
        this.val = val;
    }
}

class Solution {
    List<Double> ans;
    double prod;
    boolean found;
   
   void dfs( Map<String,List<Pair>> adj, Set<String> vis, String start,String end){
          if(!adj.containsKey(start) || vis.contains(start)) return;

          vis.add(start);
          if(start.equals(end)){
            found = true;
            return;
          }

          for(Pair p : adj.get(start)){
            if(!vis.contains(p.ch)){
                double prev = prod;
                prod *= p.val;
                dfs(adj,vis,p.ch,end);
                if(found) return;
                prod = prev;
            }
          }
    

   }

    

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,List<Pair>> adj = new HashMap<>();
        ans = new ArrayList<>();

        

        for(int i=0;i<values.length;i++){
            String s1 = equations.get(i).get(0);
            String s2 = equations.get(i).get(1);
            double val = values[i];
            adj.computeIfAbsent(s1,k-> new ArrayList<>()).add(new Pair(s2,val));
            adj.computeIfAbsent(s2,k->new ArrayList<>()).add(new Pair(s1,1.0/val));

        }

        

         for(List<String> l: queries){
            String q1 = l.get(0);
            String q2 = l.get(1);

            if(!adj.containsKey(q1) || !adj.containsKey(q2)){
                ans.add(-1.0);
                continue;
            }
            prod =1.0;
            found = false;
            
            dfs(adj,new HashSet<>(),q1,q2);
            ans.add(found ? prod:-1.0);
         }

         double[] res = new double[ans.size()];

         for(int i=0;i<ans.size();i++){
            res[i] = ans.get(i);
         }

         return res;
    }
}