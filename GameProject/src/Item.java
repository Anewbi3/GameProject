import java.io.Serializable;

public class Item implements Serializable {
	private String name;
	private String description;
	
	
	public Item(String name_arg, String description_arg) {
		name = name_arg;
		description = description_arg;
	}
	
	public void open() {
		Game.print("You can't open that!");
	}
	
	public void close() {
		Game.print("The " + name + "doesn't close.");
	}
	
	public void use() {
		Game.print("You can't use " + name + "!");
	}
	
	public void pull() {
		Game.print("You can't pull the " + name + ".");
	}
	
	public void push() {
		Game.print(name + " can't be pushed.");
	}
	
	public void specialCommand(String command) {
		Game.print("That doesn't work.");
	}
	
	
	
	// Setter function
	public void setName(String name_arg)
	{
		name = name_arg;
	}
	
	// Getter function
	public String getName() 
	{
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String toString()
	{
		return name;
	}
}
