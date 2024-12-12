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
		

		//		runGame();
		
		// gameInterface = new GUI();
		// Game.print(currentRoom);
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
				
				Game.print("Using " + words[1] + " now.");
				
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
			else {
				if (nextRoom.getRoomLockState() == true) {
					Game.print("You need a key to unlock the door.");
				}
				else {
					currentRoom = nextRoom;
					Game.print(currentRoom);
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
			
			Game.print("Using " + words[1] + " now.");
			
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
		System.out.println(obj.toString());
		// gameInterface.textArea.setText(obj.toString());
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
	
	public static void saveGame(String fileName) {
		File saveFile = new File(fileName);
		
		try {
			FileOutputStream fos = new FileOutputStream(saveFile);
			ObjectOutputStream stream = new ObjectOutputStream(fos);
			
			stream.writeObject(currentRoom);
			stream.writeObject(items);
			stream.writeObject(roomObjects);
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
