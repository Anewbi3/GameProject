public class Puppy extends NPC {
	private int talkCounter = 1;
	private int talkCounterMax = 3;

	public Puppy() {
		super("Puppy", "This is a special puppy from CSCI 257.");
	}
	
	@Override
	public void talk() {
		System.out.println("Starting talk method, talkCounter: " + talkCounter);
		switch(talkCounter) {
			case 1:
			say("Hi! I'm an adorable puppy!");
			// Game.print("Hi! I'm an adorable puppy!");
			// String[] optionsForFirstTalkCounter = {
			// 		"Yes you are! Who's a good boy?",
			// 		"Ew, no. You're actually kinda hideous."
			// };
			// getResponse(optionsForFirstTalkCounter);
			break;
			case 2:
				say("Hey! Wanna play fetch? Say yes! Say yes!");

				String[] optionsForSecondTalkCounter = {
					"Yes! I love fetch!",
					"No. I am a horrible person and don't like playing with puppies."
				};

				getResponse(optionsForSecondTalkCounter);
			break;
			case 3:
				say("Yip!");
				Game.print("The puppy wags its tail.");
			break;
		}

		updateTalkCounter();
		System.out.println("Ending talk method, talkCounter: " + talkCounter);

	}

	@Override
	public void response(int option)
	{
		switch (talkCounter) {
			case 1:
			switch(option) {
				case 1:
					say("I am! I'm a good boy!");
				break;
				case 2:
					say("I am adorable! Why are you so mean?");
					Game.print("The puppy bites you. You deserve it.");
					break; 
			}
				break;
			case 2:
				switch(option) {
					case 1:
						say("Yay! Fetch!");
						Game.print("The puppy runs off and returns with a ball.");						

					break;
					case 2:
					say("You're a bad person! I don't like you!");
					Game.print("The puppy runs away and doesn't come back.");
				}
				break;
		}

	}

	private void updateTalkCounter() {
		System.out.println("Updating talk counter from: " + talkCounter);
		if ( talkCounter <= talkCounterMax ) {
			talkCounter += 1;
		}
		System.out.println("Updated talk counter to: " + talkCounter);;
	}
}
