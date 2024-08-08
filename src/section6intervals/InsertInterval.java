package section6intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insertInterval(int[][] curr, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        // find out interval on the left of newInterval (non-overlapping)
        while (i < curr.length && curr[i][1] < newInterval[0]) {
            result.add(curr[i++]);
        }
        // add the new inverval [start, end] : boundaries should change if overlapping
        while (i < curr.length && curr[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(curr[i][0], newInterval[0]);
            newInterval[1] = Math.max(curr[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);
        // find out interval on the right of newInterval (non-overlapping)
        while (i < curr.length) {
            result.add(curr[i++]);
        }
        return result.toArray(new int[result.size()][2]);
    }
}
