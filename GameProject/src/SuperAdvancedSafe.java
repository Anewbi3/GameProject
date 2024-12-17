import javax.swing.JOptionPane;

public class SuperAdvancedSafe extends Item {
    private Boolean alreadyOpenedTheSafe = false;

    public SuperAdvancedSafe(String name, String description) {
        super(name, description);
    }

    public void open() {
        Game.print("Enter the code in this format xx-xx-xx: ");

        
        
        if (!alreadyOpenedTheSafe) {
            String codeForSafe = JOptionPane.showInputDialog("Enter code: ");
            
            if (codeForSafe.equals("02-05-07")) {
                Game.print("You would think a " + '"' + "Super Advanced Safe" + '"'
                        + " would be more protected than that.");
                Game.print("Here you go I guess.");

                Item LegallyAcquiredAlcohol = new Item("Dirty Martini",
                        "A bold and briny martini made with gin, dry vermouth, olive juice, and garnished with olives.");
                Game.items.add(LegallyAcquiredAlcohol);
                Game.print("You acquired a glass of Dirty Martini that never seems to run out.");
                Game.print("Congratulations! You finally beat the game!");
                Game.print("Enjoy!");
                alreadyOpenedTheSafe = true;
            } else {
                Game.print("Nah dude...not today.");
            }

        }
        else {
            Game.print("You found an open safe...what else were you expecting.");
            Game.print("Now go away. I can finally rest and retire from this job of being a narrator.");
            Game.print("The End.");
        }
    }
}
