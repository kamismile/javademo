package asm;

import com.sun.btrace.org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: lidong
 * Date: 13-12-2
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public class Bean {
    private int f;
    public int getF() {
        return this.f;
    }
    public void setF(int f) {
        this.f = f;
    }

}
