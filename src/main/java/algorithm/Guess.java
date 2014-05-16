package algorithm;

import java.io.Console;
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
//        new Guess().gg();
//        System.out.println(new Guess().pop(1212));
        System.out.println(3&3);
        System.out.println(3|4);
    }

    public int bitCount(int x){
        int count = 0;
        while(x!=0){
            if(x%2!=0){  //判断奇偶数
                count++;
            }
            x = x>>>1;
        }
        return count;
    }

    public int bitCount2(int x){
        int count = 0;
        while(x!=0){
            count+= x&1;
            x = x>>>1;
        }
        return count;
    }

    public int bitCount3(int x){
        int count = 0;
        while(x!=0){
            if(x<0){
                count++;
            }
            x = x<<1;
        }
        return count;
    }

    public int bitCount4( int x )
    {
        int count = 0;
        while ( x != 0 )
        {
            x &= x - 1;
            count++;
        }
        return count;
    }

    private int pop(int x)
    {
        x = x - ((x >> 1) & 0x55555555);
        x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
        x = (x + (x >> 4)) & 0x0F0F0F0F;
        x = x + (x >> 8);
        x = x + (x >> 16);
        return x & 0x0000003F;
    }
}
