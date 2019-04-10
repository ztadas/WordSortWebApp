package lt.ztadas.SoapAddendumApp.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import lt.ztadas.SoapAddendumApp.service.IWordSortService;
import lt.ztadas.xmlclasses.GetWordsSortedRequest;
import lt.ztadas.xmlclasses.GetWordsSortedResponse;

@Endpoint
public class WordSortEndpoint {
	
	@Autowired
	private IWordSortService service;
	
	@PayloadRoot(namespace = "http://ztadas.lt/xmlschema", localPart = "GetWordsSortedRequest")
	@ResponsePayload
    public GetWordsSortedResponse processRequest(@RequestPayload GetWordsSortedRequest request){
		GetWordsSortedResponse response = new GetWordsSortedResponse();
		response.setSentence(getSortedSentence(request.getSentence()));
		return response;
    }

	private String getSortedSentence(String sentence) {
		return service.getSortedSentence(sentence);
	}
}
