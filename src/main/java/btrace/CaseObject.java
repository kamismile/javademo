package btrace;

/**
 * Created with IntelliJ IDEA.
 * User: lidong
 * Date: 13-11-29
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public class CaseObject {
    private static int sleepTotalTime=0;

    public boolean execute(int sleepTime) throws Exception{
        System.out.println("sleep: "+sleepTime);
        sleepTotalTime+=sleepTime;
        Thread.sleep(sleepTime);
        if(sleepTime%2==0)
            return true;
        else
            return false;
    }

}
