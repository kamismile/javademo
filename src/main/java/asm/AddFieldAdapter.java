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

        MethodVisitor methodVisitor = addFieldAdapter.visitMethod(Opcodes.ACC_PUBLIC, "getF", "()I", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 2);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "asm/Bean", "getF", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 2);
        methodVisitor.visitInsn(Opcodes.LSUB);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(5, 4);
        methodVisitor.visitEnd();

        addFieldAdapter.visitEnd();
        classReader.accept(addFieldAdapter,ClassReader.SKIP_DEBUG);
        classVisitor.visitEnd();
        byte[] data = classVisitor.toByteArray();
        File file = new File("D:\\Bean.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}