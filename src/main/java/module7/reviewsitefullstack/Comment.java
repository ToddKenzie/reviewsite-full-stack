package module7.reviewsitefullstack;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends Post {

	private String username;
	private Instant timeStamp;
	
	@ManyToOne
	private Review review;
	

	public Review getReview() {
		return review;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getTimeStamp() {
		return StringFormatter.timeformat(timeStamp);
	}
	
	public Comment() {}

	public Comment(Review review, String text, String username) {
		super(text);
		this.review = review;
		this.username = username;
		this.timeStamp = Instant.now();
	}
	
	//use this only for Populator generation to set up the initial load
	public Comment(Review review, String text, String username, String timeStamp) {
		super(text);
		this.review = review;
		this.username = username;
		this.timeStamp = Instant.parse(timeStamp);
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
