package com.example.dhruvsharmacoursemgmt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.Widget;
import com.example.dhruvsharmacoursemgmt.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/widget")
	public Iterable<Widget> findAllWidgets(){
		System.out.println("hi hi");
		return widgetRepository.findAll();
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
	
	@PostMapping("api/widget/save")
	public Widget createWidgets(@RequestBody List<Widget> widgets) {
		// map with either lesson id or topic id
		System.out.println("Yo Bitch");
//		for(Widget widget : widgets) {
//			System.out.println("HEllo");
//			System.out.println(widget.getText());
//			widgetRepository.save(widget);
//		}
		return null;
	}
}
