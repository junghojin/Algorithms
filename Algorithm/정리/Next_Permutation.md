# Next Permutation

- 배열을 오름차순 정렬한 뒤, 사전 순으로 다음 순열을 생성하는 방법

## 절차
1. 배열을 오름차순으로 정렬
2. 다음이 과정의 반복하여 순열 생성 (가장 큰 내림차순 순열을 만들 때까지 반복) 

   - 뒤쪽부터 탐색하여 교환위치(꼭대기 바로 직전) 찾기 (꼭대기 - 가장 큰 수)
   - 뒤쪽부터 탐색하여 교환위치와(꼭대기 바로 직전) 교환할 큰 값 위치 찾기 (교환위치보다 큰 수 중 가장 가까운 수)
   - 두 위치 값 교환
   - 꼭대기부터 맨 뒤까지 오름차순 정렬

ex. 1 2 3 -> 3 2 1 까지 만드는 과정

<img width="800" height="400" src="https://github.com/junghojin/DeveloperNote/blob/fbcc59778ae9fadf593f8fd679ea5bf1e69fc43c/Algorithm/img/NextPermutation.jpg">

```java
package study12_Binary;

import java.util.Arrays;

public class NextPermutation {
	static int R, N, nums[], set[];

	public static void main(String[] args) {

//			nPn
		N = 4;
		nums = new int[] { 1, 2, 4, 3, 5};
		set = new int[R]; // 조합을 만들었을 때의 집합

		// 1. 오름차순 정렬
		Arrays.sort(nums);
		
		do {
			System.out.println(Arrays.toString(nums));
			
		}while(nextPermutation());
		
	}

	// 2. 꼭대기를 찾아서 순열 만들기
	private static boolean nextPermutation() {

		// 1) 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && nums[i - 1] >= nums[i])
			--i;

		// i==0이면, 만들 수 있는 형태의 순열이 더이상 없다는 뜻 (만들 수 있는 가장 큰 순열을 다 만들었다)
		if (i == 0)
			return false;

		// 2) 교환 위치에 교환할 값 찾기
		int j = N - 1;
		while (nums[i - 1] >= nums[j])
			--j;

		// 3) 교환할 위치와 교환할 위치보다 큰 값 바꾸기
		swap(i - 1, j);

		// 4) 꼭대기부터 맨 뒤까지 오름 차순 정렬
		int k = N - 1;
		while (i < k) {
			swap(i++, k--);
		}

		return true;
	}

	private static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}

```
