package algorithm;

import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: lidong
 * Date: 13-12-3
 * Time: 下午5:00
 * To change this template use File | Settings | File Templates.
 */
public class Guess {
    public void gg(){
        System.out.println("guess number!");
        Random random=new Random();
        int i=random.nextInt(100);
        while (true){
            System.out.println("input number!");
            Scanner s = new Scanner(System.in);
            int str = s.nextInt();
            if(str>i){
                System.out.println("too large");
            }  else if(str<i){
                System.out.println("too small");
            }  else{
                System.out.println("you got it");
                break;
            }

        }
    }

    public static void main(String[] args) {
        new Guess().gg();
    }
}
