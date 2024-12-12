import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game{
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static HashMap<String, String> rooms = new HashMap<String, String>();
	public static Room currentRoom = World.buildWorld();
	
	public static HashMap<String, Item> roomObjects = new HashMap<String, Item>();
	public static Scanner input = new Scanner(System.in);
	public static GUI gameInterface;
	
	public static void main(String[] args) {
		readRoomDataFromTextFile();
		

		// runGame();
		
		gameInterface = new GUI();
		Game.informPlayerAboutRoom(currentRoom);;
	}
	
	public static void runGame() {

		String command; // player's command
		
		do {
			Game.print(currentRoom);
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
					Game.print("You can't go that way!");
				}
				else {
					if (nextRoom.getRoomLockState() == true) {
						Game.print("You need a key to unlock the door.");
					}
					else {
						currentRoom = nextRoom;						
					}
				}
				break;
			case "x":
				Game.print("Thanks for walking through my game!");
				break;
			case "take":
				Item itemFoundInRoom = currentRoom.getItemInRoom(words[1]);
				Game.print("You are attempting to take the " + words[1] + ".");
				if (itemFoundInRoom == null) {
					Game.print("Item not found");
				}
				else {
					items.add(itemFoundInRoom);
					currentRoom.removeItemInRoom(itemFoundInRoom.getName());
					Game.print("You pick up the " + itemFoundInRoom.getName() + ".");
				}
				break;
			case "look":
				Item item_in_room = currentRoom.getItemInRoom(words[1]);
				
				if (item_in_room != null) {
					Game.print(item_in_room.getDescription());
				}
				else {
					Item item_in_inventory = null;
					for (Item item_found_in_inventory : items) {
						String item_name  = item_found_in_inventory.getName();
						
						if (item_name.equals(words[1])) {
							item_in_inventory = item_found_in_inventory;
						}
					}
					
					if (item_in_inventory != null)
						Game.print(item_in_inventory.getDescription());
					else
						Game.print("This item was not found.");
				}       
				        
				break;  
				        
			case "i":   
				if(items.size() == 0) {
					Game.print("You have nothing in your inventory!");
				}       
				else {  
					Game.print("You have the following item(s) in your inventory: ");
					    
					for(Item item_in_inventory : items) {
						String item_name = item_in_inventory.getName();
						
						Game.print(item_name);
					}
				}
				break;
			case "use":
				Item itemFoundInCurrentRoom = currentRoom.getItemInRoom(words[1]);
				
				
				if (itemFoundInCurrentRoom != null) {
					Game.print("Found " + words[1] + " in the current room.");
					Game.print("Using " + words[1] + " now.");
					itemFoundInCurrentRoom.use();
				}
				else if (itemFoundInCurrentRoom == null) {
					Game.print("Item not found in room, checking inventory...");
					Item item_in_inventory = null;
					for (Item item_found_in_inventory : items) {
						String item_name = item_found_in_inventory.getName();
						
						if (item_name.equals(words[1])) {
							item_in_inventory = item_found_in_inventory;
							break;
						}
					}
					if (item_in_inventory != null) {
						Game.print("Found " + words[1] + " in your inventory.");
						Game.print("Using " + words[1] + " now.");
						item_in_inventory.use();
					}
					else {						
						Game.print("The item '" + words[1] + "' does not exist in the room or inventory.");
					}
					
				}
				break;
			case "open":
				Item itemToOpenInCurrentRoom = currentRoom.getItemInRoom(words[1]);
				
				Game.print("You are attempting to take the " + words[1] + ".");
				
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
					
					if (item_in_inventory != null)
						item_in_inventory.use();
					else
						Game.print("Such item does not exist.");
				}
				break;
			default:
				Game.print("I don't know what that means.");
			}
			
			
		} while(!command.equals("x"));
		
		input.close();
	}
	
	public static void processCommand(String command) 
	{
		String[] words = command.split(" ");
		switch(words[0]) {
		case "e":
		case "w":
		case "n":
		case "s":
		case "d":
		case "u":
			Room nextRoom = currentRoom.getExit(command.charAt(0));
			
			if (nextRoom == null) {
				Game.print("You can't go that way!");
			}
			else if (nextRoom.getRoomLockState() == true) {
				Game.print("You need a key to unlock the door.");
			}
			else {
				currentRoom = nextRoom;				
				informPlayerAboutRoom(currentRoom);
			}
			break;
		case "x":
			Game.print("Thanks for walking through my game!");
			saveGame("GameProject/SaveFile.txt");
			break;
		case "take":
			Item itemFoundInRoom = currentRoom.getItemInRoom(words[1]);
			Game.print("You are attempting to take the " + words[1] + ".");
			if (itemFoundInRoom == null) {
				Game.print("Item not found");
			}
			else {
				items.add(itemFoundInRoom);
				currentRoom.removeItemInRoom(itemFoundInRoom.getName());
				Game.print("You pick up the " + itemFoundInRoom.getName() + ".");
			}
			break;
		case "look":
			Item item_in_room = currentRoom.getItemInRoom(words[1]);
			
			if (item_in_room != null) {
				Game.print(item_in_room.getDescription());
			}
			else {
				Item item_in_inventory = null;
				for (Item item_found_in_inventory : items) {
					String item_name  = item_found_in_inventory.getName();
					
					if (item_name.equals(words[1])) {
						item_in_inventory = item_found_in_inventory;
					}
				}
				
				if (item_in_inventory != null)
					Game.print(item_in_inventory.getDescription());
				else
					Game.print("This item was not found.");
			}
			break;

		case "channel":
			if (words.length < 2) {
				Game.print("Please specify a channel number.");
			} else {
				int channelNumber;

				try {
					channelNumber = Integer.parseInt(words[1]);

					Television tv = (Television) currentRoom.getItemInRoom("Television");

					if (tv != null && tv instanceof Television) {
						tv.changeChannel(channelNumber);
					} else {
						Game.print("There's no TV in this room.");
					}
				} catch (NumberFormatException e) {
					Game.print("Invalid Channel Name.");
					Game.print("Input a channel number from 1 - 99");
				}
			}

		case "i":
			if(items.size() == 0) {
				Game.print("You have nothing in your inventory!");
			}
			else {
				Game.print("You have the following item(s) in your inventory: ");
				
				for(Item item_in_inventory : items) {
					String item_name = item_in_inventory.getName();
					
					Game.print(item_name);
				}
			}
			break;
		case "use":
		Item itemFoundInCurrentRoom = currentRoom.getItemInRoom(words[1]);
				
				
		if (itemFoundInCurrentRoom != null) {
			Game.print("Found " + words[1] + " in the current room.");
			Game.print("Using " + words[1] + " now.");
			itemFoundInCurrentRoom.use();
		}
		else if (itemFoundInCurrentRoom == null) {
			Game.print("Item not found in room, checking inventory...");
			Item item_in_inventory = null;
			for (Item item_found_in_inventory : items) {
				String item_name = item_found_in_inventory.getName();
				
				if (item_name.equals(words[1])) {
					item_in_inventory = item_found_in_inventory;
					break;
				}
			}
			if (item_in_inventory != null) {
				Game.print("Found " + words[1] + " in your inventory.");
				Game.print("Using " + words[1] + " now.");
				item_in_inventory.use();
			}
			else {						
				Game.print("The item '" + words[1] + "' does not exist in the room or inventory.");
			}
			
		}
		break;
		case "open":
			Item itemToOpenInCurrentRoom = currentRoom.getItemInRoom(words[1]);
			
			Game.print("You are attempting to take the " + words[1] + ".");
			
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
				
				if (item_in_inventory != null)
					item_in_inventory.use();
				else
					Game.print("Such item does not exist.");
			}
			break;
		default:
			Game.print("I don't know what that means.");
		}
	}
	
	public static void print(Object obj) {
		// System.out.println(obj.toString());
		gameInterface.textArea.append(obj.toString() + "\n");
	}
	
	public static void readRoomDataFromTextFile() {
		try {
			File RoomsFile = new File("GameProject/RoomsFile.txt");
			Scanner input = new Scanner(RoomsFile);
			
			ArrayList<String> stringsFromRoomsFile = new ArrayList<String>();
			
			while(input.hasNextLine()) {
				// Extract the room names and description
				if(input.hasNext("#") != true) {
					String line = input.nextLine();
					stringsFromRoomsFile.add(line);
				}
				else {
					input.nextLine();
				}
			}
			// Adds the room names and descriptions to the hashmap
			for(int s = 0; s < stringsFromRoomsFile.size(); s += 2) {
				
				String roomName = stringsFromRoomsFile.get(s);
				String roomDescription = stringsFromRoomsFile.get(s + 1);
				
				rooms.put(roomName, roomDescription);			
			}
			
			input.close();
					
		} catch (FileNotFoundException e) {
			Game.print("File not found!!");
		} 

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

	public static void informPlayerAboutRoom(Room room) {
		Game.print(room.getDescription());
		ArrayList<Item> itemsInCurrentRoom = currentRoom.getAllItemsInRoom();

		if(!itemsInCurrentRoom.isEmpty()) {
			Game.print("Items in the room: ");
			for (Item item : itemsInCurrentRoom) {
				Game.print("- " + item.getName());
			}
		} else {
			Game.print("There are no items in this room.");
		}

		ArrayList<NPC> npcsInCurrentRoom = currentRoom.getAllNPCInRoom();
		if(!npcsInCurrentRoom.isEmpty()) {
			Game.print("Characters in the room: ");
			for (NPC npc : npcsInCurrentRoom) {
				Game.print("- " + npc.getName());
			}
		}

	}
	
	public static void saveGame(String fileName) {
		File saveFile = new File(fileName);
		
		try {
			FileOutputStream fos = new FileOutputStream(saveFile);
			ObjectOutputStream stream = new ObjectOutputStream(fos);
			
			stream.writeObject(currentRoom);
			stream.writeObject(items);
			stream.writeObject(roomObjects);
			stream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + fileName + " does not exist!");
		} catch (IOException e) {
			System.out.println("That should not be happening!");
		}
	}
	
	public static void loadGame(String fileName) {
		File saveFile = new File(fileName);
		
		try {
			FileInputStream fis = new FileInputStream(saveFile);
			ObjectInputStream stream = new ObjectInputStream(fis);
			
			currentRoom = (Room) stream.readObject();
			items = (ArrayList<Item>) stream.readObject();
			roomObjects = (HashMap<String, Item>) stream.readObject();
			stream.close();
		} catch (FileNotFoundException errorWithFileNameGiven) {
			System.out.println("File " + fileName + " was not found!");
		} catch (IOException errorFromObjectInputStream) {
			System.out.println("There was an error while initiating the Object Input Stream!");
		} catch (ClassNotFoundException terribleErrorToHave) {
			System.out.println("You're cooked brother!!!");
		}
	}
}
