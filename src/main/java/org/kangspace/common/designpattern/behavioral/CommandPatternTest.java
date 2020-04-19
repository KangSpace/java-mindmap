package org.kangspace.common.designpattern.behavioral;

/**
 * 命令模式
 * 概述: 将一个请求封装成一个对象，从而可用不同的请求对客户端进行参数化；对请求进行排队或者记录日志，以及支持撤销的操作
 * 适用性:
 *     1.抽象出待执行的动作以参数化某对象。
 *     2.在不同的时刻指定、排列和执行请求。
 *     3.支持取消操作。
 *     4.支持修改日志，这样当系统崩溃时，这些修改可以被重做一遍。
 *     5.用构建在原语操作上的高层操作构造一个系统。
 * 参与者:
 *      接收者: 命令最终执行对象
 *      命令接口/命令接口实现: 创建不同的命令实现对象，封装接收者,定义对外接口调用接收者具体实现
 *      请求者: 封装对命令接口的调用，提供给客户端
 *      客户端: 负责创建命令，并指定其接收者，并使用请求者执行操作
 *
 * @author kango2gler@gmail.com
 */
public class CommandPatternTest {

    /**
     * 接受者: 灯
     */
    static class ReceiverLight {
        void on() {
            System.out.println("灯亮了...");
        }
        void off() {
            System.out.println("灯灭了...");
        }
    }

    /**
     * 命令接口
     */
    interface Command{
        /**
         * 执行
         */
        void execute();

        /**
         * 撤销
         */
        void undo();
    }

    /**
     * 具体命令: 灯开关命令
     */
    static class LightOnCommand implements Command {
        LightOnCommand(ReceiverLight receiver) {
            this.receiver = receiver;
        }
        ReceiverLight receiver;
        @Override
        public void execute() {
            receiver.on();
        }
        @Override
        public void undo() {
            receiver.off();
        }
    }
    static class LightOffCommand implements Command {
        LightOffCommand(ReceiverLight receiver) {
            this.receiver = receiver;
        }
        ReceiverLight receiver;
        @Override
        public void execute() {
            receiver.off();
        }
        @Override
        public void undo() {
            receiver.on();
        }
    }

    /**
     * 请求者
     */
    static class RemoteInvoker {
        Command onCommand;
        Command offCommand;
        Command undoCommand;
        public RemoteInvoker addCommand(Command onCommand,Command offCommand){
            this.onCommand = onCommand;
            this.offCommand = offCommand;
            return this;
        }
        public void lightOn(){
            onCommand.execute();
            undoCommand = onCommand;
        }

        public void lightOff(){
            offCommand.execute();
            undoCommand = offCommand;
        }
        public void undo(){
            undoCommand.undo();
        }


    }


    static void main(){
        //请求者
        RemoteInvoker invoker = new RemoteInvoker();
        //接受者
        ReceiverLight light = new ReceiverLight();

        invoker.addCommand(new LightOnCommand(light), new LightOffCommand(light));
        invoker.lightOn();
        invoker.lightOff();
        invoker.undo();
    }
    public static void main(String[] args) {
        main();
    }
}
