package cvut.omo.app_utils;

import cvut.omo.device.documentation.BrokennessLevel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utils {


    private static final Random PRNG = new Random();

    /**
     *
     * @param to
     * @return
     */
    public static int getRandomInt(int to){
        return PRNG.nextInt(to);
    }


    public static double getRandomDouble(){return PRNG.nextDouble();}


    public static int getRandomInt(){
        return PRNG.nextInt();
    }

    public static BrokennessLevel getRandomBrokennessLevel()  {
        BrokennessLevel[] brokennessLevels = BrokennessLevel.values();
        return brokennessLevels[getRandomInt(brokennessLevels.length)];
    }

    public static boolean yesOrNo(float probabilityOfYes) {
        return PRNG.nextInt()%100 < (probabilityOfYes * 100);
    }


    public static <T> T getRandomObjFromList(List<T> objs) {
        return objs.get(getRandomInt(objs.size()));
    }

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





}
