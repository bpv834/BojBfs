import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2178 {
    static int[][] board;
    static int[][] ch;
    static int n;
    static int m;
    static int[] dy= new int[]{-1,1,0,0};
    static int[] dx = new int[]{0, 0, -1, 1};
    static Queue<Point> q = new LinkedList<Point>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        StringTokenizer tk = new StringTokenizer(str);
        n = Integer.parseInt(tk.nextToken());
        m= Integer.parseInt(tk.nextToken());

        board = new int[n][m];
        ch = new int[n][m];


        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(row.substring(j, j + 1));
            }
        }

        q.add(new Point(0, 0));
        ch[0][0]=1;
        Bfs();

        System.out.println(ch[n-1][m-1]);

    }

    static void Bfs() {
        while (!q.isEmpty()) {
            Point vtx = q.poll();
            int y = vtx.y;
            int x = vtx.x;


            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < n && nx >= 0 && nx < m && board[ny][nx] == 1 && ch[ny][nx]==0) {
                    if(ch[y][x]+1 > ch[ny][nx]) {
                        // 현재길 +1 보다 다음길의 값이 작아야 작은값으로 이동한 후 현재값+1값을 넣을 수 있음
                        ch[ny][nx] = ch[y][x] + 1;
                        q.add(new Point(ny, nx));
                    }
                }


            }
        }
    }

}




class Point{
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
