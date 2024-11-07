
public class Television extends Item{
	
	// TV state determines if the tv is on or off.
	private Boolean tvState;
	
	public Television(String name, String description) {
		super(name, description);
	}
	

	public Boolean getTvState() {
		return tvState;
	}

	public void setTvState(Boolean tvState) {
		this.tvState = tvState;
	}
	
	
	
}
