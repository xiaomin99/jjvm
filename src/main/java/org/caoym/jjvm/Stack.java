package org.caoym.jjvm;

import com.sun.tools.classfile.ConstantPool;
import org.caoym.jjvm.opcodes.Opcode;

/**
 * 虚拟机栈
 * 每个虚拟机线程持有一个独立的栈
 */
public class Stack {

    private SlotsStack<StackFrame> frames = new SlotsStack<>(1024);
    private boolean running = false;

    public StackFrame newFrame() {
        StackFrame frame = new StackFrame(null, null, 0, 0);
        frames.push(frame, 1);
        return frame;
    }

    public StackFrame newFrame(ConstantPool constantPool,
                               Opcode[] opcodes,
                               int variables,
                               int stackSize) {
        StackFrame frame = new StackFrame(constantPool, opcodes, variables, stackSize);
        frames.push(frame, 1);
        return frame;
    }
    public StackFrame currentFrame() {
        return frames.pick();
    }
    public StackFrame popFrame(){
        return frames.pop();
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}
