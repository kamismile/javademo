package asm;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;

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
            @Override
            public void visitInsn(int opcode) {
                if (opcode == Opcodes.RETURN) {
                    mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                            "Ljava/io/PrintStream;");
                    mv.visitFieldInsn(Opcodes.GETSTATIC,
                            Type.getInternalName(Bean.class), "timer", "J");
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
                            "currentTimeMillis", "()J");
                    mv.visitInsn(Opcodes.LADD);
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                            "println", "(J)V");
                }
                mv.visitInsn(opcode);
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