package com.sds.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.spring.domain.Board;
import com.sds.spring.exception.BoardDMLException;
import com.sds.spring.model.board.BoardService;

//DefaultAnnotaionHandlerMapping이 , 아래의 클래스를 하위 컨트롤러로 발견하게끔, 어노테이션으로 표시를 해야 한다
@Controller
public class BoardController{
	
	@Autowired
	private BoardService boardService;
	
	//앞으로드는 게시판과 관련된 요청을 이 하나의 클래스에서 담당하되, 각각의 요청은 
	//메서드로 처리한다.. 
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String getList(Model model) {
		System.out.println("목록요청 받음");
		
		List boardList = boardService.selectAll(); //3단계: 일 시키기
		
		model.addAttribute("boardList", boardList); //4단계: jsp로 가져갈 결과가 있으므로 결과 저장
	
		//View 객체를 따로 만들지 말고, 그냥 String으로 뷰의 이름을 저장하면 자동으로 View 객체에 담김 
		return "board/list";
	}

	//글쓰기 폼 요청 
	@RequestMapping(value="/board/writeform", method=RequestMethod.GET)
	public String getForm() {
		return "board/write";  //요청 유지 jsp
	}
	
	//글등록 요청 처리
	@RequestMapping(value="/board/regist", method=RequestMethod.POST)
	public String regist(Board board){
		//System.out.println("title "+board.getTitle());
		//System.out.println("writer "+board.getWriter());
		//System.out.println("content "+board.getContent());
		
		//3단계: 일 시키기 
		boardService.insert(board);
		
		return "redirect:/board/list"; //목록을 재요청을 들어가야 하므로, 요청을 끊는 redirect 시도
	}
	
	//상세보기 요청 처리
	@RequestMapping(value="/board/detail", method=RequestMethod.GET)
	public ModelAndView getDetail(Board dto) {
		//3단계: 모델에 일 시키기 
		Board board = boardService.select(dto.getBoard_idx());
		
		//4단계: 결과가 있으므로 결과 저장
		ModelAndView mav = new ModelAndView("board/content");
		mav.addObject("board", board);
		
		return mav;
	}
	
	//수정 요청 처리 
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String edit(Board board) {
		//3단계: 일 시키기 
		boardService.update(board);
		
		//상세 페이지를 요청을 끊고 redirect로 재요청
		return "redirect:/board/detail?board_idx="+board.getBoard_idx(); 
	}
	
	//삭제 요청 처리 
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)	
	public String del(Board board) {
		//3단계: 일 시키기 
		boardService.delete(board);
		
		//목록 재요청 
		return "redirect:/board/list";
	}
	
	
	//이 컨트롤러의 메서드에서 만일 예외가 발생하면, 아래의 메서드가 자동호출됨
	@ExceptionHandler(BoardDMLException.class)
	public ModelAndView handle(BoardDMLException e) {
		
		//개발자가 런타임 예외에 심어놓은 메시지를 꺼낼때는 e.getMessage() 메서드 사용 
		System.out.println("예외 발생 "+ e.getMessage());
		
		return null;
	}
}





















