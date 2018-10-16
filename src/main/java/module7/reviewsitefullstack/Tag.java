package module7.reviewsitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag extends Grouping {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToMany(mappedBy = "tags")
	private Collection<GameReview> gameReviews;
	
	@ManyToMany(mappedBy = "tags")
	private Collection<GameExpansion> gameExpansions;
	
	public long getId() {
		return id;
	}
	
	public Collection<GameReview> getGameReviews() {
		return gameReviews;
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
