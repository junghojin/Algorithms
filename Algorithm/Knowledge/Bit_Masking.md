# 비트

---

- 0 = false = 사용 중 X
- 1 = true = 사용 중 O

# 비트 연산자

---

- **&** : AND 연산
- **|** : OR 연산
- ^ : XOR 연산 (같으면 0, 다르면 1)
- ~ : 모든 비트 반전
- **<<** : 비트 열을 왼쪽으로 이동 (빈 공간은 0으로 채운다)
- >> : 비트 열을 오른쪽으로 이동 (빈 공간은 부호비트로 채운다)
- >>> : 비트 열을 오른쪽으로 이동 (빈 공간은 0으로 채운다)

<img width = "500" height = "300" src = "">

> ‘**&’은 해당 숫자가 사용중인지, ‘|’은 해당 숫자를 사용 중임을 나타내기 위해 사용**
> 

## 순열 코드 - 비트 마스킹 사용

```java
import java.util.Arrays;

public class Permutation_BitMasking {

	static int R, N, nums[], set[];

	public static void main(String[] args) {

//		nPr
		R = 2;
		N = 5;
		nums = new int[] { 1, 2, 3, 4, 5 };
		set = new int[R]; // 순열을 만들었을 때의 집합

		permutation(0, 0);
	}

	private static void permutation(int L, int flag) { // L: N개의 숫자 중 뽑은 개수
		if (L == R) {
			System.out.println(Arrays.toString(set));
			return;
		}

		for (int i = 0; i < N; i++) {
			
			// 해당 위치의 숫자가 사용 중인지 확인
			**if ((flag & 1 << i) != 0)**
				continue;

			set[L] = nums[i]; // 해당 위치 숫자가 사용 중이지 않으면 집합에 넣기
			
			// 다음 수 뽑으러 가기 
			**permutation(L + 1, flag | 1 << i);** // i번째 숫자가 사용 상태임을 세팅
		}
	}
}
```

## 조합 코드 - 비트마스킹 사용
```java
package study12_Binary;

import java.util.Arrays;

public class Permutation_BitMasking {

	static int R, N, nums[], set[];

	public static void main(String[] args) {

//		nCr
		R = 2;
		N = 5;
		nums = new int[] { 1, 2, 3, 4, 5 };
		set = new int[R]; // 조합을 만들었을 때의 집합

		combination(0, 0, 0);
	}

	private static void combination(int L, int start, int flag) { // L: N개의 숫자 중 뽑은 개수
		if (L == R) {
			System.out.println(Arrays.toString(set));
			return;
		}

		for (int i = start; i < N; i++) {

			// 해당 위치의 숫자가 사용 중인지 확인
			if ((flag & 1 << i) != 0)
				continue;

			set[L] = nums[i]; // 해당 위치 숫자가 사용 중이지 않으면 집합에 넣기

			// 다음 수 뽑으러 가기
			combination(L + 1, i + 1, flag | 1 << i); // i번째 숫자가 사용 상태임을 세팅
		}
	}
}
```
