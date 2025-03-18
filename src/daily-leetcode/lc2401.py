class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        # try brute force 
        answer = 1
        
        for left in range(len(nums)):
            right = left + 1
            mask = nums[left]
            # print(f"mask={mask} left={left}")
            while right < len(nums) and right - left < 31 and mask & nums[right] == 0:
                # print(f"mask={mask} right={right}")
                mask ^= nums[right]
                right += 1
                
            answer = max(answer, right - left)
        return answer