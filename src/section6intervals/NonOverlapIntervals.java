package section6intervals;

import java.util.Arrays;

public class NonOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0];
        int end = intervals[0][1];
        int result = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {
                start = intervals[i][0];
                end = intervals[i][1];
                continue;
            }
            if (start <= intervals[i][0] && intervals[i][0] < end) {
                if (intervals[i][1] < end) {
                    start = intervals[i][0];
                    end = intervals[i][1];
                }
                result++;
            }
        }
        return result;
    }

}
