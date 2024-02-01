package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Spencer Tramontina - srtramontina
 * CIS175 - Spring 2024
 * Jan 31, 2024
 */

@Entity
@Table(name="games")
public class Game {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="GENRE")
	private String genre;
	@Column(name="PLATFORM")
	private String platform;
	
	public Game() {
		super();
	}

	public Game(String name, String genre, String platform) {
		super();
		this.name = name;
		this.genre = genre;
		this.platform = platform;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public String returnItemDetails() {
		return this.name + ": " + this.genre + " game on " + this.platform;
	}

	@Override
	public String toString() {
		return "Game [name=" + name + ", genre=" + genre + ", platform=" + platform + "]";
	}
	
	
	
}
