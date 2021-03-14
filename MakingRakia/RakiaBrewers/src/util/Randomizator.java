package util;

import java.util.Random;

public class Randomizator {
    public static int randomFromTo(int min,int max){
        return new Random().nextInt(max-min)+1;
    }
}
