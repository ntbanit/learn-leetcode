from sortedcontainers import SortedSet 
class Solution:
    size = 1000001
    not_prime = [False] * size
    prime_set = SortedSet()
    _has_run = False

    @staticmethod
    def seive():
        if Solution._has_run :
            return 
        Solution.not_prime[1] = True
        Solution.seive_number(2)
        i = 3
        while i < Solution.size:
            if not Solution.not_prime[i] :
                Solution.seive_number(i)
            i += 2
        Solution._has_run = True

    @staticmethod
    def seive_number(prime: int):
        number = prime * prime
        Solution.prime_set.add(prime)
        while number < Solution.size - 1:
            Solution.not_prime[number] = True
            number += prime

    def __init__(self):
        Solution.seive()

    def closestPrimes(self, left: int, right: int) -> List[int]:
        if left <= 2 and right >= 3:
            return [2, 3]
        
        ans = [-1] * 2
        i = left if left % 2 == 1 else left + 1
        print(f"i={i}")
        while i <= right:
            if i in Solution.prime_set : 
                index = Solution.prime_set.index(i)
                j = -1
                if index < len(Solution.prime_set) - 1:
                    j = Solution.prime_set[index + 1]
                    if j > right :
                        break
                    if ans[0] == -1 or j - i < ans[1] - ans[0]:
                        ans = [i, j]
                    i = j
                else :
                    break
            else :
                i += 2
                
        return ans 