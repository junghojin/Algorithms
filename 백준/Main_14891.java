import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_14891 {
	static ArrayList<LinkedList<Integer>> wheels;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 톱니바퀴
		wheels = new ArrayList<LinkedList<Integer>>();
		wheels.add(null); // 1-4번이라는 톱니바퀴 순서에 맞추기 위해 null 삽입
		StringTokenizer stk = null;
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			LinkedList<Integer> w = new LinkedList<Integer>();
			for (int j = 0; j < 8; j++) {
				w.add(str.charAt(j) - '0');
			}
			wheels.add(w);
		}

		int K = Integer.parseInt(br.readLine());

		for (int k = 0; k < K; k++) {
			stk = new StringTokenizer(br.readLine(), " ");
			int no = Integer.parseInt(stk.nextToken()); // 회전시킬 톱니바퀴 번호
			int dir = Integer.parseInt(stk.nextToken()); // 방향

			boolean[] isAvailable = new boolean[5];
			int[] directions = new int[5];

			checkStatus(no, isAvailable);
			checkDirections(no, dir, directions);
			move(no, dir, isAvailable, directions);

//				for (int i = 1; i < 5; i++) {
//					System.out.println(Arrays.toString(wheels.get(i).toArray()));
//				}
		}

		System.out.println(score());

	}

	// 점수 계산
	private static int score() {
		int total = 0;
		for (int i = 1; i < 5; i++) {

			if (wheels.get(i).get(0) == 1) { // 빨간색 화살표 위치의 날이 S극이라면
				total += Math.pow(2, i - 1);
			}
		}
		return total;
	}

	private static void move(int no, int dir, boolean[] isAvailable, int[] directions) {
		for (int i = 1; i < 5; i++) {
			if (!isAvailable[i])
				continue;
			if (directions[i] == -1) {
				int temp = wheels.get(i).removeFirst();
				wheels.get(i).addLast(temp);
			} else if (directions[i] == 1) {
				int temp = wheels.get(i).removeLast();
				wheels.get(i).addFirst(temp);
			}
		}
	}

	// 톱니바퀴 방향 결정
	private static void checkDirections(int no, int dir, int[] directions) {
		directions[no] = dir;
		// 왼쪽
		for (int i = no - 1; i > 0; i--) {
			directions[i] = directions[i + 1] * -1;
		}
		// 오른쪽
		for (int i = no + 1; i < 5; i++) {
			directions[i] = directions[i - 1] * -1;
		}
	}

	// 톱니바퀴 움직임 가능여부 체크
	private static void checkStatus(int no, boolean[] isAvailable) {
		// 자기 자신
		isAvailable[no] = true;

		// 왼쪽
		for (int i = no - 1; i >= 1; i--) {
			if (leftAvailable(i))
				isAvailable[i] = true;
			else
				break;
		}

		// 오른쪽
		for (int i = no + 1; i < 5; i++) {
			if (rightAvailable(i))
				isAvailable[i] = true;
			else
				break;
		}
	}

	private static boolean leftAvailable(int no) {
		if ((wheels.get(no + 1).get(6) ^ wheels.get(no).get(2)) != 0) {
			return true;
		}
		return false;
	}

	private static boolean rightAvailable(int no) {
		if ((wheels.get(no).get(6) ^ wheels.get(no - 1).get(2)) != 0) {
			return true;
		}
		return false;
	}
}
