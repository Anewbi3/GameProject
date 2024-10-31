import java.util.*;

public class Game {
	public static ArrayList<Item> items = new ArrayList<Item>();
	static Room currentRoom = World.buildWorld();
	public static void main(String[] args) {
		runGame();
	}
	
	public static void runGame() {

		
		Scanner input = new Scanner(System.in);
		
		String command; // player's command
		
		do {
			System.out.println(currentRoom);
			System.out.println();
			System.out.print("What do you want to do: 	");
			command = input.nextLine();
			String[] words = command.split(" ");
			// comment
			switch(words[0]) {
			case "e":
			case "w":
			case "n":
			case "s":
			case "d":
			case "u":
				Room nextRoom = currentRoom.getExit(command.charAt(0));
				
				if (nextRoom == null) {
					System.out.println("You can't go that way!");
				}
				else {
					currentRoom = nextRoom;
				}
				break;
			case "x":
				System.out.println("Thanks for walking through my game!");
				break;
			case "take":
				Item itemFoundInRoom = currentRoom.getItemInRoom(words[1]);
				System.out.println("You are attempting to take the " + words[1] + ".");
				if (itemFoundInRoom == null) {
					System.out.println("Item not found");
				}
				else {
					items.add(itemFoundInRoom);
					currentRoom.removeItemInRoom(itemFoundInRoom.getName());
					System.out.println("You pick up the " + itemFoundInRoom.getName() + ".");
				}
				break;
			case "look":
				Item item_in_room = currentRoom.getItemInRoom(words[1]);
				
				if (item_in_room != null) {
					System.out.println(item_in_room.getDescription());
				}
				else {
					Item item_in_inventory = null;
					for (Item item_found_in_inventory : items) {
						String item_name  = item_found_in_inventory.getName();
						
						if (item_name.equals(words[1])) {
							item_in_inventory = item_found_in_inventory;
						}
					}
					
					System.out.println(item_in_inventory.getDescription());
					
				}
				
				break;
				
			case "i":
				if(items.size() == 0) {
					System.out.println("You have nothing in your inventory!");
				}
				else {
					System.out.println("You have the following item(s) in your inventory: ");
					
					for(Item item_in_inventory : items) {
						String item_name = item_in_inventory.getName();
						
						System.out.println(item_name);
					}
				}
				break;
			case "use":
				Item itemFoundInCurrentRoom = currentRoom.getItemInRoom(words[1]);
				
				System.out.println("You are attempting to take the " + words[1] + ".");
				
				if (itemFoundInCurrentRoom != null) {
					itemFoundInCurrentRoom.use();
				}
				else {
					Item item_in_inventory = null;
					for (Item item_found_in_inventory : items) {
						String item_name  = item_found_in_inventory.getName();
						
						if (item_name.equals(words[1])) {
							item_in_inventory = item_found_in_inventory;
						}
					}
					item_in_inventory.use();
				}
				break;
			case "open":
				Item itemToOpenInCurrentRoom = currentRoom.getItemInRoom(words[1]);
				
				System.out.println("You are attempting to take the " + words[1] + ".");
				
				if (itemToOpenInCurrentRoom != null) {
					itemToOpenInCurrentRoom.open();
				}
				else {
					Item item_in_inventory = null;
					for (Item item_found_in_inventory : items) {
						String item_name  = item_found_in_inventory.getName();
						
						if (item_name.equals(words[1])) {
							item_in_inventory = item_found_in_inventory;
						}
					}
					item_in_inventory.use();
				}
				break;
			default:
				System.out.println("I don't know what that means.");
			}
			
			
		} while(!command.equals("x"));
		
		input.close();
	}
	
	public static void print(Object obj) {
		System.out.println(obj.toString());
	}
	
	public static Room getCurrentRoom() 
	{
		return currentRoom;
	}
	
	public static Item getItemFromPlayerInventory(String item_name_arg) 
	{
		Item item_in_inventory = null;
		for (Item item : items) {
			String item_name = item.getName();
			
			if (item_name.equals(item_name_arg)) {
				item_in_inventory = item;
			}
		}
		
		return item_in_inventory;
	}
}
