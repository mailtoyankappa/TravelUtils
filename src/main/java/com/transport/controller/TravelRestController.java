package com.transport.controller;

import com.transport.tunal.DijkstraAlgorithm;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;


@RestController
public class TravelRestController {
	
	@Autowired
	@Lazy
	private DijkstraAlgorithm shortestPathService;
	
	/**
	  http://localhost:8090/shortestpath/A/Z
	 */
	@RequestMapping(method = RequestMethod.GET, value="/shortestpath/{source}/{destination}")
	@ResponseBody
	public String getShortestPath(@PathVariable("source") String source, @PathVariable("destination") String destination){
		return shortestPathService.findShortestPath(source, destination);
	}

	@GetMapping(value = "/v2/reply/{payload}")
	public JSONObject replyString(@PathVariable String payload){
		return shortestPathService.readPayloadAndProcess(payload);
	}
}
