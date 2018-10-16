package module7.reviewsitefullstack;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PlayedObject {
	
	@Id
	@GeneratedValue
	protected long id;
	
	protected String name;
	protected String rangeOfPlayers;
	protected String timeToComplete;
	protected String synopsis;
	protected String weblink;
	protected String pictureLink;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRangeOfPlayers() {
		return rangeOfPlayers;
	}

	public String getTimeToComplete() {
		return timeToComplete;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public String getWeblink() {
		return weblink;
	}
	
	public String getPictureLink() {
		return pictureLink;
	}
	
	
	public PlayedObject() {}
	
	public PlayedObject(String name, String rangeOfPlayers, String timeToComplete, String synopsis, String weblink, String pictureLink) {
		this.name = name;
		this.rangeOfPlayers = rangeOfPlayers;
		this.timeToComplete = timeToComplete;
		this.synopsis = synopsis;
		this.weblink = weblink;
		this.pictureLink = pictureLink;
	}
}
