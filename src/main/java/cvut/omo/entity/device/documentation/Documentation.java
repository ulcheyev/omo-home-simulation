package cvut.omo.entity.device.documentation;

import cvut.omo.app_utils.Constants;
import cvut.omo.app_utils.WriterToFile;
import cvut.omo.entity.device.HomeAppliances;

import java.io.IOException;

/**
 * The class is an implementation of the documentation for {@link cvut.omo.entity.device.HomeDevice}
 */
public class Documentation {

    private HomeAppliances homeAppliances;

    protected String nameDevice;
    protected BrokennessLevel brokennessLevel;
    protected String nameOfDocumentation;


    /**
     * Constructor for class
     * @param homeAppliances the device for which documentation is being created
     */
    public Documentation(HomeAppliances homeAppliances){
        this.homeAppliances = homeAppliances;
    }

    /**
     * Generate documentation for {@link #homeAppliances}.
     * @throws IOException {@link  WriterToFile}
     */
    public void generateDocumentation() throws IOException {
        nameDevice = homeAppliances.getClass().getSimpleName();
        brokennessLevel = homeAppliances.getBrokennessLevel();
        StringBuilder res = new StringBuilder();

        res.append(Constants.PDF_DOCUMENTATION_HEADER);

        final String text =
                "This is very clever documentation for the device: "
                        + nameDevice + " you just broke.\n It turned out that your device has a state of damage: ";

        switch (brokennessLevel) {
            case HARDCORE -> res.append(text + brokennessLevel + ".\n Nothing will help you, throw the device in the trash.").append("\n");
            case MEDIUM -> res.append(text +  brokennessLevel + ".\n You still have a chance to save your device. Call your grandfather and he will help you.").append("\n");
            case FINE -> res.append(text + brokennessLevel + ".\n The device is very, very slightly damaged. You will need to fix it for it to work.").append("\n");
        }

        nameOfDocumentation = nameDevice + "_documentation";
        WriterToFile.generateNewDocumentation(nameOfDocumentation,res.toString());
    }
}
