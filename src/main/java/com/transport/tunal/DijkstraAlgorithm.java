package com.transport.tunal;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

public interface DijkstraAlgorithm {
	
	public String findShortestPath(String source, String destination);

    JSONObject readPayloadAndProcess(String payload);
}
