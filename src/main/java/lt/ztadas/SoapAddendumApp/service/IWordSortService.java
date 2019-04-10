package lt.ztadas.SoapAddendumApp.service;

public interface IWordSortService {

	void sortWordsInFile(String filePath);
	
	String getSortedSentence(String sentence);
}
