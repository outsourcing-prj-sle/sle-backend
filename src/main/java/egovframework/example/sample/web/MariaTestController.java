package egovframework.example.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.MariaTestVO;

@RestController
public class MariaTestController {
	
	@Autowired
	private EgovSampleService egovSampleService;
	
	/**
	 * MariaDB 연결 테스트
	 */
	@GetMapping("/api")
	public ResponseEntity<?> apiMethodTest() {
		List<MariaTestVO> list = egovSampleService.selectMariaTest();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/api/test")
	public String test() {
		return "test";
	}
}
