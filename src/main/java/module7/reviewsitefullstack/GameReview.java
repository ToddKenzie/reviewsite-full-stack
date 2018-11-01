package module7.reviewsitefullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class GameReview extends PlayedObject{

	@OneToOne(mappedBy = "gameReview")
	private Review review;
	
	@OneToOne(mappedBy = "gameReview")
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

	public GameReview() {}
	
	public GameReview(String name, String rangeOfPlayers, String timeToComplete, String synopsis, String weblink, String pictureLink, GameCategory gameCategory, Tag...tags) {
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
		GameReview other = (GameReview) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
