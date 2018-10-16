package module7.reviewsitefullstack;

public abstract class PlayedObject {
	
	private String name;
	private String rangeOfPlayers;
	private String timeToComplete;
	private String synopsis;
	private String weblink;
	private String pictureLink;
	
	
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
	
	public PlayedObject(String name, String rangeOfPlayers, String timeToComplete, String synopsis, String weblink, String pictureLink) {
		this.name = name;
		this.rangeOfPlayers = rangeOfPlayers;
		this.timeToComplete = timeToComplete;
		this.synopsis = synopsis;
		this.weblink = weblink;
		this.pictureLink = pictureLink;
	}
}
