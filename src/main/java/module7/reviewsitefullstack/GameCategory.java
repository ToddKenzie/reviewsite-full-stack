package module7.reviewsitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GameCategory {

	@Id
	@GeneratedValue
	private long id;
	
	private String type;
	
	@OneToMany(mappedBy = "gameCategory")
	private Collection<GameReview> gameReviews;
	
	public long getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public Collection<GameReview> getGameReviews() {
		return gameReviews;
	}

	public GameCategory() {}
	
	public GameCategory(String type) {
		this.type = type;
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
		GameCategory other = (GameCategory) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
