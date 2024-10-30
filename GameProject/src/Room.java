import java.util.HashMap;

public class Room {
	private String room_description;
	
	private Room east;
	private Room west;
	private Room north;
	private Room south;
	private Room up;
	private Room down;
	
	private HashMap<String, Item> item = new HashMap<String, Item>();
	
	
	public Room(String init_room_description) {
		room_description  = init_room_description;
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
	
	public void removeItemInRoom(String item_name) 
	{
		item.remove(item_name);
	}
	
	
	public String toString() {
		return room_description;
	}
	
}
