import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Room implements Serializable {
	private String name;
	private String room_description;
	private Boolean lock = false;
	
	private Room east;
	private Room west;
	private Room north;
	private Room south;
	private Room up;
	private Room down;

	



	private HashMap<String, Item> item = new HashMap<String, Item>();
	private HashMap<String, NPC> NON_PLAYABLE_CHARACTERS = new HashMap<String, NPC>();
	
	
	public Room(String room_name) {
		name = room_name;
		room_description = getDescription();
	}
	
	public Room getExit(char direction) {
		if (direction == 'e' ) {
			return east;
		}
		else if (direction == 'w' ) {
			return west;
		}
		else if (direction == 'n' ) {
			return north;
		}
		else if (direction == 's' ) {
			return south;
		}
		else if (direction == 'u' ) {
			return up;
		}
		else if (direction == 'd' ) {
			return down;
		}
		else {
			return null;
		}
	}
	
	public void addExit(Room newRoom, char direction) {
		if (direction == 'e' ) {
			east = newRoom;
		}
		else if (direction == 'w' ) {
			west = newRoom;
		}
		else if (direction == 'n' ) {
			north = newRoom;
		}
		else if (direction == 's' ) {
			south = newRoom;
		}
		else if (direction == 'u' ) {
			up = newRoom;
		}
		else if (direction == 'd' ) {
			down = newRoom;
		}
		else {
			System.out.println("You can not do that!");
		}
		
	}
	
	public void setItemInRoom(Item set_item) 
	{
		item.put(set_item.getName(), set_item);
	}
	
	public Item getItemInRoom(String item_name) 
	{
		return item.get(item_name);
	}

	public ArrayList<Item> getAllItemsInRoom() {
		ArrayList<Item> allItemsInRoom = new ArrayList<Item>();

		for (Item itemInRoom : item.values()) {
			allItemsInRoom.add(itemInRoom);
		}

		return allItemsInRoom;
	}
	
	public void removeItemInRoom(String item_name) 
	{
		item.remove(item_name);
	}

	public void setNPCInRoom(NPC npc)
	{
		NON_PLAYABLE_CHARACTERS.put(npc.getName(), npc);
	}

	public NPC getNPCInRoom(String npc_name)
	{
		return NON_PLAYABLE_CHARACTERS.get(npc_name);
	}

	public ArrayList<NPC> getAllNPCInRoom() {
		ArrayList<NPC> all_npc_in_room = new ArrayList<NPC>();

		for (NPC npcInRoom : NON_PLAYABLE_CHARACTERS.values()) {
			all_npc_in_room.add(npcInRoom);
		}

		return all_npc_in_room;
	}

	public void removeNPCInRoom(String npc_name)
	{
		NON_PLAYABLE_CHARACTERS.remove(npc_name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.room_description = description;
	}
	
	public String getDescription() {
		return Game.rooms.get(name);
	}

	public Boolean getRoomLockState() {
		return lock;
	}

	public void setRoomLockState(Boolean state) {
		lock = state;
	}
	
	
	public String toString() {
		return getDescription();
	}
	
}
