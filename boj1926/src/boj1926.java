import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1926 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};
    static int n;
    static int m;
    static int[][] board;
    static int[][] ch;
    static int cnt = 0;
    static int maxArea = 0;

    static Queue<PointYx> q = new LinkedList<PointYx>();


    public static void bfs() {
        int area = 0;
        while (!q.isEmpty()) {
            PointYx vtx = q.poll();
            ++area;
            for (int i = 0; i < 4; i++) {
                int ny = vtx.y + dy[i];
                int nx = vtx.x + dx[i];

                if (ny >= 0 && ny < n && nx >= 0 && nx < m && board[ny][nx] == 1 && ch[ny][nx] ==0) {
                    q.add(new PointYx(ny, nx));
                    ch[ny][nx] = 1;
                }
            }
        }
        maxArea = Math.max(maxArea, area);

    }

    public static void main(String[] args) throws IOException {
        String str = br.readLine();

        StringTokenizer tk = new StringTokenizer(str);
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        board = new int[n][m];
        ch = new int[n][m];


        // 보드 입력
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            StringTokenizer token = new StringTokenizer(row);

            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        // 순회
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && ch[i][j] == 0) {
                    ++cnt;
                    ch[i][j] = 1;
                    PointYx v = new PointYx(i, j);
                    q.add(v);
                    bfs();
                }
            }
        }
        System.out.println(cnt);
        System.out.println(maxArea);
    }
}

