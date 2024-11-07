
public class KeyForUpstairsBedroom extends Item{
	public KeyForUpstairsBedroom(String name, String description) {
		super(name, description);
	}
	
	public void use() {
		Room currentRoom = Game.getCurrentRoom();
		Room nextRoom = currentRoom.getExit('w');
		
		if (currentRoom.getName().equals("Upstairs")) {
			nextRoom.setRoomLockState(!nextRoom.getRoomLockState());
		}
		else {
			Game.print("This key can be used to open the bedroom upstairs. Go upstairs to unlock the door.");
		}
	}
}
