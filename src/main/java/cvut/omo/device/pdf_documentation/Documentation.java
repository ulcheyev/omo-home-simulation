package cvut.omo.device.pdf_documentation;
import cvut.omo.app_utils.Constants;
import cvut.omo.device.HomeAppliances;
import java.io.IOException;
import java.util.Random;

public class Documentation {

    protected String name_device;
    protected String level_of_damage;
    protected String nameOfDocumentation;

    public void generateReport(HomeAppliances homeAppliances) throws IOException {
        name_device = homeAppliances.getClass().getSimpleName();
        level_of_damage = String.valueOf(homeAppliances.getBrokennessLevel());
        StringBuilder res = new StringBuilder();
        res.append(Constants.PDF_DOCUMENTATION_HEADER);
        switch (level_of_damage){
            case "HARDCORE":
                res.append("This is very clever documentation for the device: " + name_device + " you just broke. " + "\n" +  "It turned out that your device has a state of damage: " + level_of_damage + "." + "\n" + "Nothing will help you, give your device to the service.").append("\n");
                break;
            case "MEDIUM":
                res.append("This is very clever documentation for the device: " + name_device + " you just broke. " + "\n" +  "It turned out that your device has a state of damage: " + level_of_damage + "." + "\n" + "You still have a chance to save your device. You will need to fix it for it to work.").append("\n");
                break;
            case "FINE":
                res.append("This is very clever documentation for the device: " + name_device + " you just broke. " + "\n" +  "It turned out that your device has a state of damage: " + level_of_damage + "." + "\n" + "The device is very, very slightly damaged. Call your grandfather and he will help you.").append("\n");
                break;
        }
        nameOfDocumentation = "documentation" + new Random().nextInt();
        FileWriterForDocumentation.generateNewLog(nameOfDocumentation,res.toString());
    }
}
