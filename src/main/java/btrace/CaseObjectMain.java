package btrace;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: lidong
 * Date: 13-11-29
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public class CaseObjectMain {
    public static void main(String[] args) throws Exception{
        Random random=new Random();
        CaseObject object=new CaseObject();
        while(true){
            boolean result=object.execute(random.nextInt(1000));
            Thread.sleep(1000);
        }
    }
}
