package section5graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int N, int[][] pre) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());
        int[] inDegree = new int[N];
        for (int[] e : pre) {
            adjList.get(e[0]).add(e[1]);
            inDegree[e[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                count++;
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int front = queue.remove();
            for (int neighbor : adjList.get(front)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                    count++;
                }
            }
        }

        return count == N;
    }


}
