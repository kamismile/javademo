package asm;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lidong
 * Date: 13-12-2
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
public class AddFieldAdapter extends ClassVisitor {
    public AddFieldAdapter(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    public static void main(String[] args) throws Exception {
        ClassReader classReader=new ClassReader("asm.Bean");
        ClassWriter classVisitor=new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        AddFieldAdapter addFieldAdapter=new AddFieldAdapter(classVisitor);
        addFieldAdapter.visitField(Opcodes.ACC_PUBLIC,
                "aa", "Z", null, true);

        MethodVisitor m = addFieldAdapter.visitMethod(Opcodes.ACC_PUBLIC, "getF", "()I", null, null);



        MethodVisitor mv1 = new MethodVisitor(Opcodes.ASM4,m) {
            public void visitInsn(int opcode) {
                //此方法可以获取方法中每一条指令的操作类型，被访问多次
                //如应在方法结尾处添加新指令，则应判断：

//                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
//                "asm/Bean",
//                "getF",
//                "()I");
                if(opcode == Opcodes.RETURN)
                {
                    // pushes the 'out' field (of type PrintStream) of the System class
                    mv.visitFieldInsn(Opcodes.GETSTATIC,
                            "java/lang/System",
                            "out",
                            "Ljava/io/PrintStream;");
                    // pushes the "Hello World!" String constant
                    mv.visitLdcInsn("this is a modify method!");
                    // invokes the 'println' method (defined in the PrintStream class)
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                            "java/io/PrintStream",
                            "println",
                            "(Ljava/lang/String;)V");
//                mv.visitInsn(RETURN);
                }
                super.visitInsn(opcode);
            }
        };

        mv1.visitInsn(Opcodes.RETURN);
        mv1.visitEnd();


        addFieldAdapter.visitEnd();
        classReader.accept(addFieldAdapter,ClassReader.SKIP_DEBUG);

        byte[] data = classVisitor.toByteArray();
        File file = new File("D:\\Bean.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}