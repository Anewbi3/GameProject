public class Ball extends Item {

    public Ball(String name, String description) {
        super(name, description);
    }

    public void use() {
        Room currentRoom = Game.getCurrentRoom();

        if (currentRoom.getName() == "Outside" && currentRoom.getNPCInRoom("Puppy") instanceof Puppy) {
            Game.print("You proceed to play fetch for an hour.");
            Game.print("That's a lot of cardio!");
        }
        else {
            Game.print("Are you trying to destroy this house?");
            Game.print("Take that outside please!");
        }
    }
}
