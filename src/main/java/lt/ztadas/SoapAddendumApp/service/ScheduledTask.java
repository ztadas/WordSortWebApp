package lt.ztadas.SoapAddendumApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
	
	@Autowired
	private IWordSortService service;
	
	@Value("${filePath}")
	private String filePath;
	
	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void sortWords() {
		service.sortWordsInFile(filePath);
	}
}
