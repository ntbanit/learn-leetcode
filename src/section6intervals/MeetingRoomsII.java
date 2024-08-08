package section6intervals;

import java.util.ArrayList;
import java.util.List;

class Interval {
    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MeetingRoomsII {

    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals.isEmpty()) return 0;

        intervals.sort((a, b) -> a.start - b.start);
        List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            boolean check = false;
            for(Interval nominee : result){
                if(nominee.end <= interval.start){
                    nominee.end = Math.max(nominee.end, interval.end);
                    check = true ;
                }
            }
            if(!check){
                result.add(interval);
            }
        }
        return result.size();
    }

    public static void main(String[] args) {
        int[][] input = {{0, 40}, {15, 20}, {5, 10}};
        List<Interval> intervals = new ArrayList<>();
        for (int[] arr : input) {
            intervals.add(new Interval(arr[0], arr[1]));
        }
        int result = new MeetingRoomsII().minMeetingRooms(intervals);
        System.out.println(result);
    }
}
