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

import java.sql.Timestamp;

@Controller
public class Index {

  @RequestMapping(value = "/")
  public String indexPage() {
    return "index.html";
  }

  @PostMapping(value = "/")
  @ResponseBody
  public String getInput(UserInput userInput, Model model) {
    System.out.print("------------" + userInput.getQuery());
    
    Timestamp startTime= new Timestamp(System.currentTimeMillis());
    System.out.print(startTime);
    String userQuery = userInput.getQuery();

    userQuery = userQuery.replace("\"", "\\\"");

    String url = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=AIzaSyAcZiXYhRwupywT-VLEeFjF8K_NyTpuxP4";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String contents = "{\"contents\":[{\"parts\":[{\"text\": \"";
    String jsonCloseBrakets = "\"}]}]}";
    String requestJson = contents + userQuery + jsonCloseBrakets;

    HttpEntity < String > entity = new HttpEntity < String > (requestJson, headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity < String > response = restTemplate.postForEntity(url, entity, String.class);

    Timestamp responseTime= new Timestamp(System.currentTimeMillis());
    System.out.println("------"+responseTime);
    
    JSONObject jsonObject = new JSONObject(response.getBody());
    String textValue = "";
    if (jsonObject.has("candidates") && jsonObject.getJSONArray("candidates").length() > 0) {
      JSONObject firstCandidate = jsonObject.getJSONArray("candidates").getJSONObject(0);

      if (firstCandidate.has("content") && firstCandidate.getJSONObject("content").has("parts") &&
        firstCandidate.getJSONObject("content").getJSONArray("parts").length() > 0) {
        textValue = firstCandidate.getJSONObject("content").getJSONArray("parts").getJSONObject(0).getString("text");
      }
    }
    System.out.println("Extracted Text: " + textValue);

    Timestamp endTime= new Timestamp(System.currentTimeMillis());
    System.out.print(endTime);
    
    return textValue;
  }
}
