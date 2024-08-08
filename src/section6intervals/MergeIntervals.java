package section6intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merge = new ArrayList<>();
        merge.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            int last = merge.size() - 1;
            if (merge.get(last)[1] < curr[0]) {
                merge.add(curr);
            } else {
                merge.get(last)[1] = Math.max(merge.get(last)[1], curr[1]);
            }
        }
        return merge.toArray(new int[merge.size()][2]);
    }
}
