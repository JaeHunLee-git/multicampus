package com.sds.project0305.event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//초이스 컴포넌트에서 현재 아이템말고, 다른 아이템을 선택할때 발생하는 이벤트를 
//감지하는 리스너를 재정의해보자
public class MyItemListener implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println(e);		
	}
	
}
