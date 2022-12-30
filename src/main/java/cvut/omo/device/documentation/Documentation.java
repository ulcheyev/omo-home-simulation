package cvut.omo.device.documentation;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.FileWriter;
import cvut.omo.device.HomeAppliances;

import java.io.IOException;

public class Documentation {

    private HomeAppliances homeAppliances;

    protected String name_device;
    protected BrokennessLevel level_of_damage;
    protected String nameOfDocumentation;


    public Documentation(HomeAppliances homeAppliances){
        this.homeAppliances = homeAppliances;
    }

    public void generateDocumentation() throws IOException {
        name_device = homeAppliances.getClass().getSimpleName();
        level_of_damage = homeAppliances.getBrokennessLevel();
        StringBuilder res = new StringBuilder();

        res.append(Constants.PDF_DOCUMENTATION_HEADER);

        final String text =
                "This is very clever documentation for the device: "
                        + name_device + " you just broke.\n It turned out that your device has a state of damage: ";

        switch (level_of_damage) {
            case HARDCORE -> res.append(text + level_of_damage + ".\n Nothing will help you, throw the device in the trash.").append("\n");
            case MEDIUM -> res.append(text +  level_of_damage + ".\n You still have a chance to save your device. Call your grandfather and he will help you.").append("\n");
            case FINE -> res.append(text + level_of_damage + ".\n The device is very, very slightly damaged. You will need to fix it for it to work.").append("\n");
        }

        nameOfDocumentation = name_device + "_documentation";
        FileWriter.generateNewDocumentation(nameOfDocumentation,res.toString());
    }
}
