package com.nacer.controller;

import com.nacer.model.Events;
import com.nacer.response.ApiResponse;
import com.nacer.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	public EventsService eventService;
	
	@PostMapping("/admin/events/restaurant/{restaurantId}")
	public ResponseEntity<Events> createEvents(@RequestBody Events event,
			@PathVariable Long restaurantId) throws Exception {
		Events createdEvents=eventService.createEvent(event, restaurantId);
		return new ResponseEntity<>(createdEvents,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/events")
	public ResponseEntity<List<Events>> findAllEvents() {
		List<Events> events=eventService.findAllEvent();
		return new ResponseEntity<>(events,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/events/restaurant/{restaurantId}")
	public ResponseEntity<List<Events>> findRestaurantsEvents(
			@PathVariable Long restaurantId) {
		List<Events> events=eventService.findRestaurantsEvent(restaurantId);
		return new ResponseEntity<>(events,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/events/{id}")
	public ResponseEntity<ApiResponse> deleteEvents(
			@PathVariable Long id) throws Exception{
		eventService.deleteEvent(id);
		ApiResponse res=new ApiResponse("Events Deleted",true);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}

}
