
public class Safe extends Item {
	
	public Safe(String name, String description) {
		super(name, description);
	}
	
	public void open() {
		Item combinationItem = Game.getItemFromPlayerInventory("CombinationItem");
		
		if (combinationItem.getName().equals("CombinationItem")) {
			Item diamond = new Item("Diamond", "A shiny, beautiful diamond.");
			Game.items.add(diamond);
			Game.print("Using the combination, you open the safe and find a diamond inside! Naturally, you pocket the diamond.");
		}
		else {
			Game.print("The safe is locked and you don't have the combination.");
		}
	}
}
