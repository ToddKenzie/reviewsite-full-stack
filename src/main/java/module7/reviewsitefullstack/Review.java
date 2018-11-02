package module7.reviewsitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Review extends Post{

	@OneToOne
	private Game game;
	
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;
	
	public Game getGame() {
		return game;
	}
	
	public Collection<Comment> getComments() {
		return comments;
	}
	
	public Review() {}
	
	public Review(String text, Game game) {
		super(text);
		this.game = game;
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
