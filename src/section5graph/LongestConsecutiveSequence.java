package section5graph;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int longest = 0;
        for (int number : nums) set.add(number);
        for (int number : set) {
            if (set.contains(number - 1)) continue;
            int currStreak = 1;
            while (set.contains(number + currStreak)) currStreak++;
            longest = Math.max(longest, currStreak);
        }
        return longest;
    }

    public static void main(String[] args) {
        int [] nums = {100,4,200,1,3,2};
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(nums));
    }
}
