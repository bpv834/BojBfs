import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7576 {

    static BufferedReader br;
    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};

    static int[][] board;
    static int[][] ch;

    // 가로(열)
    static int m;
    // 세로(행)
    static int n;

    static int ans =0;


    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String info = br.readLine();
        StringTokenizer tk = new StringTokenizer(info);
        m = Integer.parseInt(tk.nextToken());
        n = Integer.parseInt(tk.nextToken());

        board = new int[n][m];
        ch = new int[n][m];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            StringTokenizer token = new StringTokenizer(row);
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 익은 토마토 중에 오염되지 않은것만 Q에 넣는다, 마지막에 안익은 토마토가 있는지 구별하기 위해 전부 넣어봐야 함
                if (board[i][j] == 1 && ch[i][j] == 0) {
                    q.add(new Point(i, j));
                }
            }
        }

        bfs();

        // 전부 돌았는데도 안익은 토마토가 있다면 -1 리턴
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 익은 토마토 중에 오염되지 않은것만 Q에 넣는다, 마지막에 안익은 토마토가 있는지 구별하기 위해 전부 넣어봐야 함
                if (board[i][j]==0) {
                    System.out.println(-1);
                   return;
                }
                ans = Math.max(ans, ch[i][j]);
            }
        }

        System.out.println(ans);

    }


    static void bfs() {
        while (!q.isEmpty()) {
            Point vtx = q.poll();
            int popY = vtx.y;
            int popX = vtx.x;

            for (int i = 0; i < 4; i++) {
                int ny = popY + dy[i];
                int nx = popX + dx[i];

                if (ny >= 0 && ny < n && nx >= 0 && nx < m && board[ny][nx] == 0 && ch[ny][nx] == 0) {
                    ch[ny][nx] = ch[popY][popX] + 1;
                    board[ny][nx] = 1;
                    q.add(new Point(ny, nx));
                }

            }
        }

    }

}

class Point {
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
