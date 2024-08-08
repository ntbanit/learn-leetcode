package section5graph;

import java.util.*;

public class ValidTree {
    public boolean validTree(int n, int[][] edges) {
        adj = new ArrayList<>();
        check = new int[n];
        // for(int[] edge:edges){
        //     System.out.println(edge[0]+" "+edge[1]);
        // }

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        for(int i=0;i<n;i++){
            if(check[i] == 0){
                // a tree must have no cycle
                if(!bfs(i)) return false;
                count++;
            }
        }
        // a tree must connect all node together
        // System.out.println("count="+count);
        return count == 1;
    }
    int []check ;
    List<List<Integer>> adj;
    private boolean bfs(int vertex){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        while(!queue.isEmpty()){
            int front = queue.remove();
            check[front] ++;
            // cycle detect
            if(check[front] == 2) return false;

            for(int neighbor : adj.get(front)){
                if(check[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }
        return true;
    }
}