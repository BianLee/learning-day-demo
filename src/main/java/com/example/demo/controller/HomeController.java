package com.example.demo.controller;
import com.example.demo.model.Team;
import com.example.demo.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Controller
public class HomeController {

	@Autowired
	private TeamService teamService;
	
    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "Hello World!";
    }
    
    @RequestMapping("homeJSP")
    public String homeJsp(ModelMap model){
    	List<Team> teams = teamService.getAllTeams();
    	model.put("teams", teams);
        return "home";
    }
    
}