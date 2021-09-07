package com.transport.orm;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Planet {
	@Id
	private String planetID;

	private String planetName;

	public Planet(String id, String name) {
		this.planetID = id;
		this.planetName = name;
	}

	public String getPlanetID() {
		return planetID;
	}

	public void setPlanetID(String plaentID) {
		this.planetID = plaentID;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	@Override
	public String toString() {
		return planetName;
	}

	public Planet() {

	}
}


