package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//����Ȯ��
@Controller
public class SampleController {
	@RequestMapping("/sample")
	public String smaple(){
		return "index";
		
	}
	
	
}
