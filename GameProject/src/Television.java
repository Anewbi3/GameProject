import java.util.HashMap;


// I ALREADY DESPISE ADDING THIS TELEVISON AND CHANNELS CLASS STUFF
public class Television extends Item{
	
	// TV state determines if the tv is on or off.
	private Boolean tvState = false;
	
	private Channel currentChannel;
	
	private HashMap<Integer, Channel> channels = new HashMap<Integer, Channel>();
	
	public Television(String name, String description) {
		super(name, description);
		
	}
	
	@Override
	public void use() {
		if (tvState) {
			Game.print("Turning off the TV.");
			tvState = false;
			currentChannel = null;
		} else {
			Game.print("Turning on the TV.");
			tvState = true;

			currentChannel = channels.get(1);
		}
	}

	public void addChannel(int channel_number, Channel new_channel) {
		channels.put(channel_number, new_channel);
	}

	public Boolean getTvState() {
		return tvState;
	}

	public void setTvState(Boolean tvState) {
		this.tvState = tvState;
	}
	
	public void displayCurrentChannel()
	{
		if (currentChannel != null) {
			Game.print("Current Channel: " + currentChannel.getName());
			Game.print(currentChannel.getDescription());
		}
	}
	
	
	public void changeChannel(int channel_number) {
		if (channels.containsKey(channel_number)) {
			currentChannel = channels.get(channel_number);
			displayCurrentChannel();
		} else {
			Game.print("Channel " + channel_number + " was not found.");
		}
		
	}
	
}
