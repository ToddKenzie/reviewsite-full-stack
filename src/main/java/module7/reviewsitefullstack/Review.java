package module7.reviewsitefullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;
	
	@Lob
	private String text;

	@OneToOne
	private GameReview gameReview;
	
	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public GameReview getGameReview() {
		return gameReview;
	}
	
	public Review() {}
	
	public Review(String text, GameReview gameReview) {
		this.text = text;
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
