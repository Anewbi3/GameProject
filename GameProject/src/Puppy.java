
public class Puppy extends NPC {
	public Puppy() {
		super("puppy", "A hideous puppy wags his tail.");		
	}
	
	@Override
	public void talk() {
		say("Hi! I'm an adorable puppy!");
		String[] options = {
				"Yes you are! Who's a good boy?",
				"Ew, no. You're actually kinda hideous."
		};
		getResponse(options);
	}
}
