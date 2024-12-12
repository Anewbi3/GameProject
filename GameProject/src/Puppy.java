public class Puppy extends NPC {
	private int talkCounter;

	public Puppy() {
		super("Puppy", "This is a special puppy from CSCI 257.");
		talkCounter = 0;
	}
	
	@Override
	public void talk() {
		
		switch(talkCounter) {
			case 1:
			say("Hi! I'm an adorable puppy!");
			String[] optionsForFirstTalkCounter = {
					"Yes you are! Who's a good boy?",
					"Ew, no. You're actually kinda hideous."
			};
			getResponse(optionsForFirstTalkCounter);
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
			default:
				Game.print("The dog ignores you.");
		}
		talkCounter++;

	}

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
}
