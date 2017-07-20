package com.cooksys.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cooksys.pojo.Flight;

@Component
public class Dijkstra {
	
	private List<String> nodes;
	private List<Flight> edges;
	private Set<String> settledNodes;
	private Set<String> unSettledNodes;
	private Map<String, String> predecessors;
	private Map<String, Long> distance;
	
	public Dijkstra(){}
	
	public Dijkstra(ArrayList<String> nodes, ArrayList<Flight> edges){
		this.nodes = new ArrayList<String>(nodes);
		this.edges = new ArrayList<Flight>(edges);
	}
	
	public void execute(String origin){
		settledNodes = new HashSet<String>();
		unSettledNodes = new HashSet<String>();
		distance = new HashMap<String, Long>();
		predecessors = new HashMap<String, String>();
		distance.put(origin, 0l);
		unSettledNodes.add(origin);
		while (unSettledNodes.size() > 0) {
			String node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}
	
	private void findMinimalDistances(String node){
		List<String> adjacentNodes = getNeighbors(node);
		for(String target : adjacentNodes) {
			if(getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
			}
		}	
	}
	
	private long getDistance(String node, String target) {
		for(Flight edge : edges) {
			if (edge.getOrigin().equals(node) && edge.getDestination().equals(target)){
				return edge.getFlightTime();
			}
		}
		throw new RuntimeException("Should not happen");
	}
	
	private List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<String>();
        for (Flight edge : edges) {
                if (edge.getOrigin().equals(node)
                                && !isSettled(edge.getDestination())) {
                        neighbors.add(edge.getDestination());
                }
        }
        return neighbors;
	}

	private String getMinimum(Set<String> vertexes) {
        String minimum = null;
        for (String vertex : vertexes) {
                if (minimum == null) {
                        minimum = vertex;
                } else {
                        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                                minimum = vertex;
                        }
                }
        }
        return minimum;
	}

	private boolean isSettled(String vertex) {
        return settledNodes.contains(vertex);
	}

	private Long getShortestDistance(String destination) {
        Long d = distance.get(destination);
        if (d == null) {
                return Long.MAX_VALUE;
        } else {
                return d;
        }
	}

    public LinkedList<Flight> getPath(String target, ArrayList<Flight> flights) {
        LinkedList<String> stringPath = new LinkedList<String>();
        LinkedList<Flight> path = new LinkedList<Flight>();
        String step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
                return null;
        }
        stringPath.add(step);
        while (predecessors.get(step) != null) {
        	for(Flight f : flights){
        		if(f.getDestination().equals(step)&&f.getOrigin().equals(predecessors.get(step))){
        			if(path.isEmpty()){
        				path.add(f);
        			} else if(f.getOffset() + f.getFlightTime() < path.getLast().getOffset()){
        				path.add(f);
        			}
        		}
        	}
                step = predecessors.get(step);
                stringPath.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
