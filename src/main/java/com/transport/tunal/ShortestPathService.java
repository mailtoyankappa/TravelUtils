package com.transport.tunal;

import com.transport.orm.Planet;
import com.transport.orm.Route;
import com.transport.repository.PlanetRepository;
import com.transport.repository.RouteRepository;
import net.minidev.json.JSONObject;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static org.thymeleaf.util.StringUtils.substring;


@Service
@Lazy
public class ShortestPathService implements DijkstraAlgorithm  {
	

	static final Logger LOG = org.apache.log4j.Logger.getLogger(ShortestPathService.class);
	
	@Autowired
	private PlanetRepository planetRepo;

	@Autowired
	private RouteRepository routesRepo;

	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(
			DefaultWeightedEdge.class);
		

	@PostConstruct
	private void initWeightedGraph(){
		addVertex();
		addEdges();
	}


	public void addVertex(){
		for (Planet planet : planetRepo.findAll()) {
			this.graph.addVertex(planet.getPlanetID());
		}
	}

	private void addEdges(){
		DefaultWeightedEdge edge = null;
		for (Route route : routesRepo.findAll()) {
			String source  = route.getSource().getPlanetID();
			String destination = route.getDest().getPlanetID();
			if(source != destination){
				edge = this.graph.addEdge(source,destination);
			}
			addWeight(edge, route.getDistance());
		}
	}

	private void addWeight(DefaultWeightedEdge edge, float weight) {
		this.graph.setEdgeWeight(edge, weight);
	}


	@Override
	public String findShortestPath(String source, String destination) {
		return DijkstraShortestPath.findPathBetween(this.graph, source,destination).toString();
	}

	public JSONObject readPayloadAndProcess(String payload){
		JSONObject json = new JSONObject();

		if(payload.contains("-")){
			String[] stringArray = payload.split("-");
			String caseType = stringArray[0];
			String originalString = stringArray[1];
			String reverseString = reverseString(originalString);
			if(caseType.equalsIgnoreCase("11")){
				json.put("data", reverseString);
			}else if(caseType.equalsIgnoreCase("12")){
				json.put("data",convertString(reverseString));
			}else if(caseType.equalsIgnoreCase("22")){
				String repeatReverseString = reverseString+reverseString;
				json.put("data",convertString(repeatReverseString));
			}
		}
		return json;
	}

	public String reverseString(String originalString){
		String reverseString;
		StringBuilder str = new StringBuilder();
		str.append(originalString);
		reverseString = String.valueOf(str.reverse());
		return reverseString;
	}
	public String convertString(String str){
		String original = new String();
		try{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.reset();
			md.update(str.getBytes(StandardCharsets.UTF_8));
			final byte[] resultByte = md.digest();
			original = new String(Hex.encode(resultByte));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return original;
	}
	@PreDestroy
	public void cleanup(){
		this.graph = null;
	}
}

