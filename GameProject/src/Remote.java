
public class Remote extends Item{

	public Remote(String name, String description) {
		super(name, description);
		
	}
	
	public void use() {
		Room currentRoom = Game.getCurrentRoom();
		Television itemInRoom = (Television) currentRoom.getItemInRoom("Television");
		// Figure out a way to make the player know that the tv is now on.
		if (itemInRoom != null) {
			itemInRoom.setTvState(!itemInRoom.getTvState());
		}
		else {
			Game.print("There's no TV here my dude!");
		
		}
	}
	
}
