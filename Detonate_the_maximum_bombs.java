import java.util.*;

class Solution {
    int count;

    void dfs(int start, List<List<Integer>> adj, Set<Integer> vis) {
        vis.add(start);
        count++;

        for (int i : adj.get(start)) {
            if (!vis.contains(i)) {
                dfs(i, adj, vis);
            }
        }
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            long x1 = bombs[i][0];
            long y1 = bombs[i][1];
            long r1 = bombs[i][2];

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                long x2 = bombs[j][0];
                long y2 = bombs[j][1];

                long dx = x1 - x2;
                long dy = y1 - y2;

                long distanceSquared = dx * dx + dy * dy;
                long radiusSquared = r1 * r1;

                if (radiusSquared >= distanceSquared) {
                    adj.get(i).add(j); // bomb i can detonate bomb j
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            dfs(i, adj, new HashSet<>());
            res = Math.max(res, count);
        }

        return res;
    }
}
