package cvut.omo.app_utils;

import cvut.omo.entity.device.documentation.BrokennessLevel;
import cvut.omo.entity.device.notifier.EmailListener;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * Class contains methods for general use.
 */
public class Utils {

    private static final Random PRNG = new Random();

    /**
     * Returns random int.
     *
     * @param to max int to generate
     * @return generated number to the max int "to"
     */
    public static int getRandomInt(int to) {
        return Math.abs(PRNG.nextInt(to));
    }


    /**
     * Returns random double.
     *
     * @return generated random double
     */
    public static double getRandomDouble() {
        return PRNG.nextDouble();
    }

    /**
     * Returns random int.
     *
     * @return generated random int
     */
    public static int getRandomInt() {
        return PRNG.nextInt();
    }

    /**
     * Returns random {@link BrokennessLevel}.
     *
     * @return random value from BrokennessLevel
     */
    public static BrokennessLevel getRandomBrokennessLevel() {
        BrokennessLevel[] brokennessLevels = BrokennessLevel.values();
        return brokennessLevels[getRandomInt(brokennessLevels.length)];
    }

    /**
     * Return random probability of yes.
     *
     * @param probabilityOfYes probability of yes
     * @return true -> yes, false -> no
     */
    public static boolean yesOrNo(float probabilityOfYes) {
        return Math.random() < probabilityOfYes;
    }


    /**
     * Returns random object from specified list.
     *
     * @param objs list of objects
     * @param <T>  specified class
     * @return random object from list
     */
    public static <T> T getRandomObjFromList(List<T> objs) {
        return objs.get(getRandomInt(objs.size()));
    }

    /**
     * Scan user input for int.
     *
     * @param string message to show
     * @return valid int from input
     */
    public static int getInputNumber(String string) {
        boolean isValid = false;
        int days = 0;
        Scanner input = new Scanner(System.in);
        while (!isValid) {
            try {
                System.out.print(string);
                days = input.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please, enter digit.");
                input.nextLine();
            }
        }
        return days;
    }

    /**
     * Hello before start program.
     */
    public static void hello() {
        System.out.println("\n" +
                "███████╗███╗   ███╗ █████╗ ██████╗ ████████╗    ██╗  ██╗ ██████╗ ███╗   ███╗███████╗\n" +
                "██╔════╝████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝    ██║  ██║██╔═══██╗████╗ ████║██╔════╝\n" +
                "███████╗██╔████╔██║███████║██████╔╝   ██║       ███████║██║   ██║██╔████╔██║█████╗  \n" +
                "╚════██║██║╚██╔╝██║██╔══██║██╔══██╗   ██║       ██╔══██║██║   ██║██║╚██╔╝██║██╔══╝  \n" +
                "███████║██║ ╚═╝ ██║██║  ██║██║  ██║   ██║       ██║  ██║╚██████╔╝██║ ╚═╝ ██║███████╗\n" +
                "╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝       ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝\n" +
                "                                                                                    \n");
    }

    /**
     * Scan email input.
     */
    public static void checkInput() {
        Scanner myObj = new Scanner(System.in);
        Scanner yesNoObj = new Scanner(System.in);
        System.out.println("Do you want to receive email notifications?");
        String yesNo = yesNoObj.nextLine();
        while (!(yesNo.equals("yes") || yesNo.equals("no") || yesNo.equals("YES") || yesNo.equals("Yes") || yesNo.equals("NO") || yesNo.equals("No"))) {
            System.out.println("Please enter a valid value: yes/no");
            yesNo = yesNoObj.nextLine();
        }
        if (yesNo.equals("yes") || yesNo.equals("YES") || yesNo.equals("Yes")) {
            EmailListener.setWantEMail(true);
            System.out.println("Enter email for accept alerts: ");
            String email = myObj.nextLine();
            while (!emailValidator(email)) {
                System.out.println("Please enter a valid email for accept alerts");
                email = myObj.nextLine();
            }
            EmailListener.setEmail(email);
        } else if (yesNo.equals("no") || yesNo.equals("NO") || yesNo.equals("No")) {
            EmailListener.setWantEMail(false);
        }
    }

    private static boolean emailValidator(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;

    }

}
