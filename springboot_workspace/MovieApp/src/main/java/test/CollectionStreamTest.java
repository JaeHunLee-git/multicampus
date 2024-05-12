package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionStreamTest {

	
	//스트림을 이용한 정렬 
	public static void test1() {
		// 4, 7, 2, 8, 1
		
		List<Integer> list = Arrays.asList(4, 7, 2, 8, 1); //배열되어있는 데이터를 리스트로 만드는 단축형식
		
		List<Integer> resultList=list.stream() 
		.filter(e -> e>3 )
		.sorted(Comparator.reverseOrder())
		.collect(Collectors.toList());
		
		resultList.stream().forEach(e -> System.out.println(e));
		
	}
	
	
	public static void test2() {
		Map<Integer, String> map = new HashMap();
		map.put(1, "사과");
		map.put(2, "딸기");
		map.put(3, "바나나");
		map.put(4, "포도");
		
		//맵은 순서가 없으므로, 먼저  Iterator로  key값을 추출
		/*
		Iterator<Integer> it = map.keySet().iterator();
		
		while(it.hasNext()) { //키가 존재하는 만큼..
			int key = it.next();//키 하나를 꺼내기..
			String value = map.get(key);
			System.out.println(value);
		}
		*/
		Set<Map.Entry<Integer, String>> set = map.entrySet();
		set.stream()
		.forEach(e -> System.out.println(e.getValue()));
		
	}
	
	public static void main(String[] args) {
		test2();

	}

}




