package lt.ztadas.SoapAddendumApp.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lt.ztadas.SoapAddendumApp.service.IWordSortService;

@Service
public class WordSortService implements IWordSortService{
	
	private static final Logger LOG = LoggerFactory.getLogger(WordSortService.class);
	private static final String FILE_NOT_FOUND = "Couldn't get file. File not found";
	private static final String NO_DATA = "Couldn't find words";
	
	public void sortWordsInFile(String filePath) {
		List<String> words = getWordsFromFile(filePath);
		if(!words.isEmpty()) {
			writeWordsToFile(filePath, getSortedWords(words));
		}else {
			LOG.info(NO_DATA);
		}
	}

	private void writeWordsToFile(String filePath, List<String> list) {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
			writer.write(String.join(", ", list));
		}catch(IOException e) {
			LOG.error(FILE_NOT_FOUND);
		}
	}

	private List<String> getWordsFromFile(String filePath) {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))){
			return br.lines().collect(Collectors.toList());
		}catch(IOException e) {
			LOG.error(FILE_NOT_FOUND);
			return Collections.emptyList();
		}
	}
	
	private List<String> getSortedWords(List<String> sentences) {
		if(sentences.isEmpty()) {
			return Collections.emptyList();
		}
		List<String> words = new ArrayList<>();
		for(String sentence : sentences) {
			words.addAll(Arrays.asList(getWordsWithoutCommas(sentence.split(" "))));
		}
		Collections.sort(words);
		return words;
	}

	private String[] getWordsWithoutCommas(String[] words) {
		for(int i = 0; i < words.length; i++) {
			words[i] = words[i].replace(",", "");
		}
		return words;
	}

	public String getSortedSentence(String sentence) {
		if(sentence.length() > 0) {
			List<String> words = Arrays.asList(getWordsWithoutCommas(sentence.split(" ")));
			Collections.sort(words);
			return String.join(", ", words);
		}
		return "";
		
	}
}
