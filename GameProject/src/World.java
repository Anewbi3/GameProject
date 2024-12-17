
public class World {
	public static Room buildWorld() {
		Room livingRoom = new Room("Living Room");	
		Room kitchen = new Room("Kitchen");
		Room outside = new Room("Outside");
		Room garden = new Room("Garden");
		Room upstairs = new Room("Upstairs");
		Room bedRoom = new Room("Bedroom");
		Room upstairs_bathroom = new Room("Upstairs Bathroom");
		Room basement = new Room("Basement");
		
		bedRoom.setRoomLockState(true);
		livingRoom.addExit(kitchen, 'e');
		/*CUSTOM ROOMS*/
		
		// TODO: VERFIY THAT THE DIRECTIONS CAN BI-DIRECTIONAL
		livingRoom.addExit(outside, 'n');
		livingRoom.addExit(upstairs, 'u');
		livingRoom.addExit(basement, 'd');
		outside.addExit(livingRoom, 's');
		outside.addExit(garden, 'e');
		basement.addExit(livingRoom, 'u');
		
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
		Television televisionInLivingRoom = new Television("Television", "A 35\" tv.");

		televisionInLivingRoom.addChannel(1, new Channel("News", "24/7 news coverage.")); 
		televisionInLivingRoom.addChannel(2, new Channel("Sports", "Live sports action.")); 
		televisionInLivingRoom.addChannel(3, new Channel("Movies", "All-day movie marathon."));
		televisionInLivingRoom.addChannel(4, new Channel("Combination code", "02-05-07"));
		Remote remoteInLivingRoom = new Remote("Remote", "A remote capable with 9 different channel buttons.");
		Item flowerVase = new Item("Vase", "A flower vase with African Violets in it.");
		Ball ball = new Ball("Ball", "A ball to play fetch with the dog outside.");
		
		
		Combination combination_in_kitchen = new Combination("Combination", "You've found the combination in the kitchen, already? Nice!");
		Safe safe_in_bedroom = new Safe("Safe", "You found the safe in the bedroom, congrats!");
		KeyForUpstairsBedroom upstairs_bedroom_key = new KeyForUpstairsBedroom("Key", "This key unlocks the door the bedroom upstairs.");

		livingRoom.setItemInRoom(flowerVase);
		livingRoom.setItemInRoom(televisionInLivingRoom);
		livingRoom.setItemInRoom(remoteInLivingRoom);
		livingRoom.setItemInRoom(upstairs_bedroom_key);

		
		kitchen.setItemInRoom(combination_in_kitchen);
		bedRoom.setItemInRoom(safe_in_bedroom);
		
		Item knife = new Item("Knife", "A kitchen knife.");
		kitchen.setItemInRoom(knife);
		
		Item tissue = new Item("Tissue", "A flower vase with African Violets in it.");
		upstairs_bathroom.setItemInRoom(tissue);
		
		Puppy professor_made_puppy = new Puppy();
		outside.setNPCInRoom(professor_made_puppy);
		outside.setItemInRoom(ball);

		SuperAdvancedSafe advancedSafe = new SuperAdvancedSafe("SuperAdvancedSafe", "This safe holds the answers to question. Find the code and be mezmerized by what you find.");
		basement.setItemInRoom(advancedSafe);
		
		
		return livingRoom;
	}
}
