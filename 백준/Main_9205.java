import java.io.*;
import java.util.*;

public class Main_9205 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int n = Integer.parseInt(br.readLine()); // 편의점 개수

			List<int[]> points = new ArrayList<int[]>();
			StringTokenizer stk = null;

			for (int i = 0; i < n + 2; i++) {
				stk = new StringTokenizer(br.readLine(), " ");
				int[] point = new int[] { Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()) };
				points.add(point);
			}

			boolean[][] adjMatrix = new boolean[n + 2][n + 2];
			for (int i = 0; i < n + 2; i++) {
				for (int j = i + 1; j < n + 2; j++) { // i: 출발하는 지점, j: 도착하는 지점
					int[] from = points.get(i);
					int[] to = points.get(j);
					int distance = distance(from[0], from[1], to[0], to[1]);

					if ((distance / 50.0) <= 20.0) { // 나눗셈에서 int 형은 버림을 취하기 때문에, double 형으로 바꿔준다.
						adjMatrix[i][j] = true;
						adjMatrix[j][i] = true;
					}
				}
			}

			if (bfs(adjMatrix, n + 2))
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}

	private static boolean bfs(boolean[][] isAvailable, int n) {

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0); // 출발지
		boolean[] visited = new boolean[n];
		visited[0] = true; // 출발지 방문 처리

		while (!queue.isEmpty()) {
			int current = queue.poll();

			if (current == n - 1) // 도착지에 도착하는 길이 있다면 true 리턴
				return true;

			for (int i = 0; i < n; i++) {
				if (visited[i] || !isAvailable[current][i])
					continue;

				// current와 i점 사이 길이 존재하고, 방문하지 않았다면 queue에 넣기
				visited[i] = true;
				queue.offer(i);
			}
		}

		return false;
	}

	private static int distance(int r, int c, int nr, int nc) {
		return Math.abs(r - nr) + Math.abs(c - nc);
	}
}

/*
1
2
0 0
1000 5
2000 10
3000 15
*/
