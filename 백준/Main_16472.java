import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

		// 1. 입력 얻기
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine().trim());
        String str = sc.nextLine().trim();

		// 2. 투 포인터로 문자 읽기
        int left = 0;
        int right = 0;
        int max_length = Integer.MIN_VALUE;
        
        // 문자 종류 개수 확인을 위해 HashMap 사용 - 문자열 보관소
        HashMap<Character, Integer> hm = new HashMap<>(); 

        while (right < str.length()) {
        
        	// 오른쪽 인덱스가 가리키는 문자 current
            char current = str.charAt(right);
            
            // 문자열 보관소에 current 문자가 존재하는 경우, 문자열에 포함된 문자 개수만 증가
            if (hm.containsKey(current)) {
            
                hm.put(current, hm.get(current) + 1); 
                
            } else {  
            
            /* 문자열에 현재 포함하고자 하는 문자가 존재하지 않는 경우 새로운 문자를 문자열에 추가하기 위해
			현재 문자열의 길이가 허용범위 N보다 작은지 확인 -> 
			작지 않다면, 해당 문자열에 포함된 문자를 왼쪽부터 하나씩 제거 */
            
            max_length = Math.max(max_length, right - left);
                while (hm.size() == N) {
                    char past = str.charAt(left);
                    hm.put(past, hm.get(past) - 1);
                    if (hm.get(past) == 0) {
                        hm.remove(past);
                    }
                    left++; // 왼쪽부터 문자를 제거하면서 left 인덱스 이동 
                }
                // 문자열 길이가 N보다 작아졌을 때, 새로운 문자 추가
                hm.put(current, 1); 
            }
            // 오른쪽 인덱스는 단계마다 계속 증가
            right++;
           
      /* 매 단계마다 오른쪽에서 왼쪽 인덱스를 뺀 값이 최대 문자열 길이를 넘어설 경우, 최대 문자열 길이 갱신
      마지막에 최대 문자열길이를 구하는 이유는, aaaaaaaa 의 경우, 
      최대 문자열 길이가 8이 아닌 7로 출력되는 것을 막기 위함 */
            
            max_length = Math.max(max_length, right - left);
        }

        System.out.println(max_length);
    }
}
