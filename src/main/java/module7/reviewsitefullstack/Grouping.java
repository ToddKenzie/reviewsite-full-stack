package module7.reviewsitefullstack;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Grouping {
	
	@Id
	@GeneratedValue
	protected long id;
	
	protected String name;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Grouping() {}
	
	public Grouping(String name) {
		this.name = name;
	}

}
