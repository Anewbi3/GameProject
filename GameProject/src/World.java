
public class World {
	public static Room buildWorld() {
		Room livingRoom = new Room("You are in the living room.");	
		Room kitchen = new Room("You are in the kitchen.");
		Room outside = new Room("You are outside your home.");
		Room garden = new Room("You are admiring your beautiful garden.");
		Room upstairs = new Room("You are upstairs");
		Room bedRoom = new Room("You are in your humble abode...your bedroom.");
		Room upstairs_bathroom = new Room("You are permitted to blow up the bathroom!");
		
		
		livingRoom.addExit(kitchen, 'e');
		/*CUSTOM ROOMS*/
		
		// TODO: VERFIY THAT THE DIRECTIONS CAN BI-DIRECTIONAL
		livingRoom.addExit(outside, 'n');
		livingRoom.addExit(upstairs, 'u');
		outside.addExit(livingRoom, 's');
		outside.addExit(garden, 'e');
		
		garden.addExit(outside, 'w');
		
		upstairs.addExit(bedRoom, 'w');
		upstairs.addExit(upstairs_bathroom, 'n');
		upstairs.addExit(livingRoom, 's');
		upstairs.addExit(livingRoom, 'd');
		
		bedRoom.addExit(upstairs, 'e');
		
		upstairs_bathroom.addExit(upstairs, 's');
		
		
		/*END OF CUSTOM ROOMS*/
		
		kitchen.addExit(livingRoom, 'w');
		
		/*SET ITEMS IN A FEW ROOMS*/
		Item television = new Item("Television", "A 35\" tv.");
		Item remote = new Item("Remote", "A remote capable with 9 different channel buttons.");
		Item flowerVase = new Item("Vase", "A flower vase with African Violets in it.");
		
		
		Combination combination_in_kitchen = new Combination("CombinationItem", "You've found the combination in the kitchen, already? Nice!");
		Safe safe_in_bedroom = new Safe("Safe", "You found the safe in the bedroom, congrats!");
		
		livingRoom.setItemInRoom(flowerVase);
		livingRoom.setItemInRoom(television);
		livingRoom.setItemInRoom(remote);
		
		
		kitchen.setItemInRoom(combination_in_kitchen);
		bedRoom.setItemInRoom(safe_in_bedroom);
		
		Item knife = new Item("Knife", "A kitchen knife.");
		kitchen.setItemInRoom(knife);
		
		Item tissue = new Item("Tissue", "A flower vase with African Violets in it.");
		upstairs_bathroom.setItemInRoom(tissue);
		
		
		
		return livingRoom;
	}
}
