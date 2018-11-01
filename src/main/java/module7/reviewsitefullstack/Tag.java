package module7.reviewsitefullstack;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag extends Grouping {

	@JsonIgnore
	@ManyToMany(mappedBy = "tags")
	private Collection<Game> games;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tags")
	private Collection<GameExpansion> gameExpansions;
	
	public Collection<Game> getGames() {
		return games;
	}
	
	public Collection<Long> getGameIds() {
		Collection<Long> gameIds = new ArrayList<>();
		for (Game game : games) {
			gameIds.add(game.getId());
		}
		return gameIds;
	}
	
	public Collection<GameExpansion> getGameExpansions() {
		return gameExpansions;
	}

	public Tag() {}
	
	public Tag(String name) {
		super(name);
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
