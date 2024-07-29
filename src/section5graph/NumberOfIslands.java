package section5graph;

class NumberOfIslands {

    char[][] map;
    int row, col;

    public int numIslands(char[][] grid) {
        this.map = grid;
        this.row = grid.length;
        this.col = grid[0].length;

        int count = 0;
        for (int i = 0; i < this.row; i++)
            for (int j = 0; j < this.col; j++)
                if (this.map[i][j] == '1') {
                    dfs(i, j);
                    count++;
                }
        return count;
    }

    private void dfs(int x, int y) {
        if (x < 0 || x >= this.row || y < 0 || y >= this.col)
            return;
        if (this.map[x][y] == '0')
            return;
        this.map[x][y] = '0';
        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}