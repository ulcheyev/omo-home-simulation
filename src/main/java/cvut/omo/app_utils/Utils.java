package cvut.omo.app_utils;

import cvut.omo.entity.device.documentation.BrokennessLevel;
import cvut.omo.entity.device.notifier.EmailListener;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Class contains methods for general use
 */
public class Utils {


    private static final Random PRNG = new Random();

    /**
     * Returns random int.
     * @param to max int to generate
     * @return generated number to the max int "to"
     */
    public static int getRandomInt(int to){
        return PRNG.nextInt(to);
    }


    /**
     * Returns random double.
     * @return generated random double
     */
    public static double getRandomDouble(){return PRNG.nextDouble();}

    /**
     * Returns random int.
     * @return generated random int
     */
    public static int getRandomInt(){
        return PRNG.nextInt();
    }

    /**
     * Returns random {@link BrokennessLevel}.
     * @return random value from BrokennessLevel
     */
    public static BrokennessLevel getRandomBrokennessLevel()  {
        BrokennessLevel[] brokennessLevels = BrokennessLevel.values();
        return brokennessLevels[getRandomInt(brokennessLevels.length)];
    }

    /**
     * @param probabilityOfYes probability of yes
     * @return true -> yes, false -> no
     */
    public static boolean yesOrNo(float probabilityOfYes) {
        return PRNG.nextInt()%100 < (probabilityOfYes * 100);
    }


    /**
     *
     * @param objs list of objects
     * @param <T> specified class
     * @return random object from list
     */
    public static <T> T getRandomObjFromList(List<T> objs) {
        return objs.get(getRandomInt(objs.size()));
    }

    /**
     * Scan user input for int.
     * @param string message to show
     * @return valid int from input
     */
    public  static int getInputNumber(String string){
        boolean isValid = false;
        int days = 0;
        Scanner input = new Scanner(System.in);
        while (!isValid) {
            try {
                System.out.print(string);
                days = input.nextInt();
                isValid = true;
            }catch (InputMismatchException e){
                System.out.println("Please, enter digit.");
                input.nextLine();
            }
        }
        return days;
    }


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

    public static void checkInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter email for alerts:");
        EmailListener.setEmail(input.nextLine());
    }





}
