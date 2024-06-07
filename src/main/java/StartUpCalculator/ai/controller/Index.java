package StartUpCalculator.ai.controller;

import StartUpCalculator.ai.dto.UserInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class Index{

  @RequestMapping(value="/")
  public String indexPage(){
    return "index.html";
  }
 
  @PostMapping(value="/")
  public String getInput(UserInput userInput,Model model){
    System.out.print("------------"+userInput.getQuery());
    return "index.html";
  }
}

