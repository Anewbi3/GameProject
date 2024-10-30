
public class Item {
	private String name;
	private String description;
	
	
	public Item(String name_arg, String description_arg) {
		name = name_arg;
		description = description_arg;
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
