package module7.reviewsitefullstack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class GameExpansion extends PlayedObject{

	@OneToOne
	private GameReview gameReview;

	@ManyToMany
	private Collection<Tag> tags;

	public GameReview getGameReview() {
		return gameReview;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}
	
	public GameExpansion() {}
	
	public GameExpansion(String name, String rangeOfPlayers, String timeToComplete, String synopsis, String weblink, String pictureLink, GameReview gameReview, Tag...tags) {
		super(name, rangeOfPlayers, timeToComplete, synopsis, weblink, pictureLink);
		this.gameReview = gameReview;
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
		GameExpansion other = (GameExpansion) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
