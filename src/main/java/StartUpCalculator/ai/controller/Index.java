package StartUpCalculator.ai.controller;
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
  public String getInput(@RequestBody String data){
    System.out.print("------------"+data);
    return "index.html";
  }
}

