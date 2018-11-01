package module7.reviewsitefullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Game extends PlayedObject{

	@OneToOne(mappedBy = "game")
	private Review review;
	
	@OneToOne(mappedBy = "game")
	private GameExpansion gameExpansion;
	
	@ManyToOne
	private GameCategory gameCategory;

	@ManyToMany
	private Collection<Tag> tags;
	
	public Review getReview() {
		return review;
	}
	
	public GameExpansion getGameExpansion() {
		return gameExpansion;
	}
	
	public GameCategory getGameCategory() {
		return gameCategory;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}

	public Game() {}
	
	public Game(String name, String rangeOfPlayers, String timeToComplete, String synopsis, String weblink, String pictureLink, GameCategory gameCategory, Tag...tags) {
		super(name, rangeOfPlayers, timeToComplete, synopsis, weblink, pictureLink);
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
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
