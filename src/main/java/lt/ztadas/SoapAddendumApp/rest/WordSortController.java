package lt.ztadas.SoapAddendumApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lt.ztadas.SoapAddendumApp.service.IWordSortService;

@Controller
public class WordSortController {
	
	@Autowired
	private IWordSortService service;
	
	@Value("${filePath}")
	private String filePath;

	@RequestMapping(value = "/sort", method = RequestMethod.GET)	
	public void sort() {
		service.sortWordsInFile(filePath);
	}
}
