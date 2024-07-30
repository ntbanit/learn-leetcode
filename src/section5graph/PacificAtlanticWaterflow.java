package section5graph;

import java.util.*;

public class PacificAtlanticWaterflow {
    public class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] h) {
        int M = h.length, N = h[0].length;
        boolean[][] pacific = new boolean[M][N];
        boolean[][] atlantic = new boolean[M][N];

        Queue<Point> pacificQueue = new LinkedList<>();
        Queue<Point> atlanticQueue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            //pacific[i][0] = true;
            pacificQueue.add(new Point(i, 0));
            //atlantic[i][N-1] = true;
            atlanticQueue.add(new Point(i, N - 1));
        }
        for (int i = 0; i < N; i++) {
            //pacific[0][i] = true;
            pacificQueue.add(new Point(0, i));
            //atlantic[M-1][i]=true;
            atlanticQueue.add(new Point(M - 1, i));
        }

        bfs(pacificQueue, pacific, h);
        bfs(atlanticQueue, atlantic, h);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void bfs(Queue<Point> queue, boolean[][] check, int[][] h) {
        int M = h.length, N = h[0].length;

        while (!queue.isEmpty()) {
            Point front = queue.remove();
            check[front.x][front.y] = true;
            for (int[] direction : directions) {
                int x = front.x + direction[0];
                int y = front.y + direction[1];
                int curr = h[front.x][front.y];
                if (x >= 0 && x < M && y >= 0 && y < N && !check[x][y]) {
                    int neighbor = h[x][y];
                    if (neighbor >= curr) queue.add(new Point(x, y));
                }
            }
        }
    }

}


class CopySolution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int row = heights.length;
        int col = heights[0].length;
        int MIN = Integer.MIN_VALUE;
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        // first row : pacific, last row: atlantic
        for (int colIndex = 0; colIndex < col; colIndex++) {
            dfs(heights, pacific, 0, colIndex, MIN);
            dfs(heights, atlantic, row - 1, colIndex, MIN);
        }
        // first col : pacific, last col: atlantic
        for (int rowIndex = 0; rowIndex < row; rowIndex++) {
            dfs(heights, pacific, rowIndex, 0, MIN);
            dfs(heights, atlantic, rowIndex, col - 1, MIN);
        }
        // get result
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (pacific[i][j] && atlantic[i][j])
                    result.add(Arrays.asList(i, j));

        return result;
    }

    void dfs(int[][] matrix, boolean[][] ocean, int rowIndex, int colIndex, int heightValue) {
        if (rowIndex < 0 || rowIndex >= matrix.length || colIndex < 0 || colIndex >= matrix[0].length)
            return;
        if (matrix[rowIndex][colIndex] < heightValue || ocean[rowIndex][colIndex])
            return;
        ocean[rowIndex][colIndex] = true;
        dfs(matrix, ocean, rowIndex + 1, colIndex, matrix[rowIndex][colIndex]);
        dfs(matrix, ocean, rowIndex - 1, colIndex, matrix[rowIndex][colIndex]);
        dfs(matrix, ocean, rowIndex, colIndex + 1, matrix[rowIndex][colIndex]);
        dfs(matrix, ocean, rowIndex, colIndex - 1, matrix[rowIndex][colIndex]);
    }
}

