package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//연결확인
@Controller
public class SampleController {
	@RequestMapping("/sample")
	public String smaple(){
		return "index";
		
	}
	
	
}
