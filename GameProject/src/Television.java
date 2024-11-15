import java.util.HashMap;


// I ALREADY DESPISE ADDING THIS TELEVISON AND CHANNELS CLASS STUFF
public class Television extends Item{
	
	// TV state determines if the tv is on or off.
	private Boolean tvState;
	
	private Channel currentChannel;
	
	private HashMap<Integer, Channel> channels = new HashMap<Integer, Channel>();
	
	public Television(String name, String description) {
		super(name, description);
		
	}
	
//	@Override
//	public void use()

	public Boolean getTvState() {
		return tvState;
	}

	public void setTvState(Boolean tvState) {
		this.tvState = tvState;
	}
	
//	public 
	
	
	public Channel getChannel(int channel_number) {
		Channel channel_found = channels.get(channel_number);
		return channel_found;
	}
	
}
