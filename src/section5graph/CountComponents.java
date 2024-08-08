package section5graph;

import java.util.*;

public class CountComponents {


    List<List<Integer>> adj;
    boolean check[];

    public int countComponents(int n, int[][] edges) {
        adj = new ArrayList<>();
        check = new boolean[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                bfs(i);
                count++;
            }
        }
        return count;
    }

    private void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            int front = queue.remove();
            check[front] = true;
            for (int neighbor : adj.get(front)) {
                if (!check[neighbor]) {
                    queue.add(neighbor);
                }
            }
        }
    }
}
