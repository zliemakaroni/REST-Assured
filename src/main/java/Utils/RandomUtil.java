package Utils;

import Configuration.TestData;
import Models.Test;

import java.util.Random;

public class RandomUtil {

    private static final String ALPHABETS_IN_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHABETS_IN_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";

    private static final Random random = new Random();

    public static String getRandomString(int length) {
        StringBuilder randomString = new StringBuilder();
        String pool = ALPHABETS_IN_UPPER_CASE + ALPHABETS_IN_LOWER_CASE;

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(pool.length());
            randomString.append(pool.charAt(randomIndex));
        }
        return randomString.toString();
    }

    public static int getRandomInt(int min, int max){
        return (int)(Math.random()*(max-min+1)+min);
    }

    public static Test getRandomTest() {
        return new Test(getRandomString(TestData.getRandomLength()),
                getRandomString(TestData.getRandomLength()),
                "n/a", "n/a", "n/a","n/a");
    }
}
