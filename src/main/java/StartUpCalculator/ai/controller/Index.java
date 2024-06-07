package StartUpCalculator.ai.controller;

import StartUpCalculator.ai.dto.UserInput;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
@Controller
public class Index{

  @RequestMapping(value="/")
  public String indexPage(){
    return "index.html";
  }
 
  @PostMapping(value="/")
  public String getInput(UserInput userInput,Model model){
    System.out.print("------------"+userInput.getQuery());

//    final String uri = "http://google.com";

  //  RestTemplate restTemplate = new RestTemplate();
   // String result = restTemplate.getForObject(uri, String.class);

   // System.out.println(result);
String url = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=AIzaSyAcZiXYhRwupywT-VLEeFjF8K_NyTpuxP4";

HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);

String requestJson = "{\"contents\":[{\"parts\":[{\"text\": \"Write a story about a magic backpack\"}]}]}";

HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class); // Get response as String

// Parse the JSON response
JSONObject jsonObject = new JSONObject(response.getBody());

// Process the response to extract text value (same logic as before)
String textValue = "";
// ... rest of the logic to extract textValue from jsonObject

if (jsonObject.has("candidates") && jsonObject.getJSONArray("candidates").length() > 0) {
  // Get the first candidate (assuming there's only one)
  JSONObject firstCandidate = jsonObject.getJSONArray("candidates").getJSONObject(0);
  
  if (firstCandidate.has("content") && firstCandidate.getJSONObject("content").has("parts") 
      && firstCandidate.getJSONObject("content").getJSONArray("parts").length() > 0) {
    // Get the text from the first part of the content
    textValue = firstCandidate.getJSONObject("content").getJSONArray("parts").getJSONObject(0).getString("text");
  }
}
System.out.println("Extracted Text: " + textValue);
    System.out.println("Extracted Text: " + textValue);

    return "index.html";
  }
}

