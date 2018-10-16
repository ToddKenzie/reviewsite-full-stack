package module7.reviewsitefullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GameExpansion {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@OneToOne
	private GameReview gameReview;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public GameReview getGameReview() {
		return gameReview;
	}
	
	public GameExpansion() {}
	
	public GameExpansion(String name, GameReview gameReview) {
		this.name = name;
		this.gameReview = gameReview;
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
