package module7.reviewsitefullstack;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Post {
	
		@Id
		@GeneratedValue
		protected long id;
		
		@Lob
		protected String text;
		
		public long getId() {
			return id;
		}
		
		public String getText() {
			return text;
		}
		
		public Post() {}
		
		public Post(String text) {
			this.text = text;
		}

}

