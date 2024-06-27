package org.lombardo.rest.model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "known_cliffs")
@NamedQuery(name = "Cliffs.findAll", query = "SELECT f FROM Cliff f ORDER BY f.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Cliff {

	@Id @SequenceGenerator(name = "cliffsSequence", sequenceName = "known_cliffs_id_seq", allocationSize = 1, initialValue = 10) @GeneratedValue(generator = "cliffsSequence") private Integer id;
	private String name;
	private String description;

	public Cliff() {
	}

	public Cliff(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}

	public Cliff(int id, String name, String description) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
