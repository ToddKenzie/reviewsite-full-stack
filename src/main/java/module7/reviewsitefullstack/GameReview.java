package module7.reviewsitefullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class GameReview {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@ManyToOne
	private GameCategory gameCategory;

	@ManyToMany
	private Collection<Tag> tags;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public GameCategory getCategory() {
		return gameCategory;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}

	public GameReview() {}
	
	public GameReview(String name, GameCategory gameCategory, Tag...tags) {
		this.name = name;
		this.gameCategory = gameCategory;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameReview other = (GameReview) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
