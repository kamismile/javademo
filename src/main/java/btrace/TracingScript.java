package btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * Created with IntelliJ IDEA.
 * User: lidong
 * Date: 13-11-29
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
@BTrace
public class TracingScript {
    /* put your code here */
/*指明要查看的方法，类*/
    @OnMethod(
            clazz="btrace.CaseObject",
            method="execute",
            location=@Location(Kind.RETURN)
    )
/*主要两个参数是对象自己的引用 和 返回值，其它参数都是方法调用时传入的参数*/
    public static void traceExecute(@Self btrace.CaseObject object,int sleepTime, @Return boolean result){
        println("调用堆栈！！");
        println(strcat("返回结果是：",str(result)));
        jstack();
        println(strcat("时间是：",str(sleepTime)));
    }

}
