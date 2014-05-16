package thread;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by lidong on 14-5-16.
 */
public class Main {

    public static void main(String[] args) {
//        Deque<Event> deque=new ArrayDeque<Event>();
//        WriterTask writer=new WriterTask(deque);
//        for (int i=0; i<3; i++){
//            Thread thread=new Thread(writer);
//            thread.start();
//        }
//        CleanerTask cleaner=new CleanerTask(deque);
//        cleaner.start();


        Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();


    }
}
