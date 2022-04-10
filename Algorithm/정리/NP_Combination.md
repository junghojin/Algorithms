# Next Permutation 이용한 조합 만들기
- flag를 Next Permutation을 이용하여 구현한 것
- nCr 을 구하고자 할 때, 크기 n의 배열을 생성하여 r 개의 크기만큼 0 이 아닌 값 초기화
> 5C2 이면, 00011 로 초기화

- Next Permutation 알고리즘 이용
> 00011을 사용하여 NP를 돌리고 1의 위치에 원소 값이 선택되어 조합을 만든다. 

- Next Permutation 한 번 이용할 때마다 조합이 만들어 진다. (0이 아닌 값의 위치가 변경된다) <br>
=> r개의 크기 만큼, 즉, 0이 아닌 값이 세팅된 곳에 원소를 선택하여 조합을 만든 것이다.

<img width="700" height="400" src="https://github.com/junghojin/DeveloperNote/blob/62e08684ee5be968f9936c56b9f61f0059be53ac/Algorithm/img/Combination.jpg">

```java
package study12_Binary;

import java.util.Arrays;

public class Combination {
	static int N, R, nums[];

	public static void main(String[] args) {
//			nCr
		R = 3;
		N = 5;
		nums = new int[] { 1, 2, 3, 4, 5 };

		int[] set = new int[N];
		// 0보다 큰 값으로 R개를 맨 뒤부터 채운다.
		int cnt = 0;
		while (++cnt <= R)
			set[cnt] = 1;
		Arrays.sort(set); // 오름차순으로 만들기 위해서

		do {
			for(int i = 0; i < N; i++) {
				if(set[i] == 1) {
					System.out.print(nums[i]+" ");
				}
			}
			System.out.println();
			
		} while (nextPermutation(set));
	}

	// 2. 꼭대기를 찾아서 순열 만들기
	private static boolean nextPermutation(int[] set) {

		// 1) 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && set[i - 1] >= set[i])
			--i;

		// i==0이면, 만들 수 있는 형태의 순열이 더이상 없다는 뜻 (만들 수 있는 가장 큰 순열을 다 만들었다)
		if (i == 0)
			return false;

		// 2) 교환 위치에 교환할 값 찾기
		int j = N - 1;
		while (set[i - 1] >= set[j])
			--j;

		// 3) 교환할 위치와 교환할 위치보다 큰 값 바꾸기
		swap(set, i - 1, j);

		// 4) 꼭대기부터 맨 뒤까지 오름 차순 정렬
		int k = N - 1;
		while (i < k) {
			swap(set, i++, k--);
		}

		return true;
	}

	private static void swap(int[] set, int i, int j) {
		int temp = set[i];
		set[i] = set[j];
		set[j] = temp;
	}
}

```
