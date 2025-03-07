class Solution:
    def findMissingAndRepeatedValues(self, grid: List[List[int]]) -> List[int]:
        N = len(grid)
        check = set()
        twice = 0
        for number in range(1, N*N + 1):
            check.add(number)
        for i in range(N):
            for j in range(N):
                number = grid[i][j]
                if number not in check :
                    twice = number
                else :
                    check.remove(number)
        my_list = list(check)
        return [twice, my_list[0]]