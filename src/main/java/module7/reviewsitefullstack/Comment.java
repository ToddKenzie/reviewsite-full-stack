package module7.reviewsitefullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;
	
	@Lob
	private String text;

	private String username;
	
	public long getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public String getUsername() {
		return username;
	}
	
	public Comment() {}

	public Comment(String text, String username) {
		this.text = text;
		this.username = username;
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
