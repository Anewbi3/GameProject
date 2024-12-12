
public class Remote extends Item{

	public Remote(String name, String description) {
		super(name, description);
		
	}
	
	public void use() {
		Room currentRoom = Game.getCurrentRoom();
		Television tv = (Television) currentRoom.getItemInRoom("Television");
		// Figure out a way to make the player know that the tv is now on.
		if (tv != null) {
			boolean newTvState = !tv.getTvState();
			tv.setTvState(newTvState);

			if(newTvState) {
				Game.print("You have turned on the TV with the remote.");
				tv.use();
			}
			else {
				Game.print("You have turned off the TV with the remote.");
			}
		}
		else {
			Game.print("There's no TV here my dude!");
		
		}
	}
	
}
