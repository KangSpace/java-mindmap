package org.kangspace.common.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式
 * 概述: 使多个对象可以处理1个请求，已解耦发送者和接受者
 * 适用性:
 *      1. 多个对象可以处理1个请求，具体哪个对象处理由运行时确定
 *      2. 不确定接收者的情况下，可以向多个对象提交1个请求
 *      3. 可处理请求的对象集合应动态指定
 * 编码方式:
 *      1. 责任链模式写法1: 接口方法中传入责任链对象,有责任链对象调度各个责任对象处理
 *         void handle(String request, IResponsibility responsibility);
 *      2. 写法2: 责任对象中设置下一个处理者责任对象
 *         IResponsibility next;
 * @author kangxuefeng@etiantian.com
 */
public class CORPatternTest {
    //----------------------责任链模式写法1: 接口方法中传入责任链对象,有责任链对象调度各个责任对象处理------------------
    /**
     * 责任链对象公共接口
     */
    interface IResponsibility {
        void handle(String request, IResponsibility responsibility);
    }

    /**
     * 责任对象A
     */
    static class AResponsibility implements IResponsibility {
        @Override
        public void handle(String request, IResponsibility cor) {
            if ("A".equals(request)) {
                System.out.println("AResponsibility handle :"+request);
            }
            if (cor != null) {
                cor.handle(request,cor);
            }
        }
    }

    /**
     * 责任对象B
     */
    static class BResponsibility implements IResponsibility {
        @Override
        public void handle(String request, IResponsibility cor) {
            if ("B".equals(request)) {
                System.out.println("BResponsibility handle :"+request);
            }
            if (cor != null) {
                cor.handle(request,cor);
            }
        }
    }

    /**
     * 责任链对象
     */
    static class ChainOfResponsibility implements IResponsibility{
        List<IResponsibility> responsibilities = new ArrayList<>();
        Integer idx=0;
        ChainOfResponsibility add(IResponsibility responsibility) {
            responsibilities.add(responsibility);
            return this;
        }

        @Override
        public void handle(String request, IResponsibility responsibility) {
            if (idx >= responsibilities.size()) {return;}
            IResponsibility resp = responsibilities.get(idx);
            idx++;
            resp.handle(request,this);
        }
    }

    //----------------------责任链模式写法2: 责任对象中添加下一个责任对象为对象属性------------------
    abstract static class IResponsibility2{
        IResponsibility2 next;
        abstract void handle(String request);
    }

    static class CResponsibility extends IResponsibility2 {
        private CResponsibility(){}

        public CResponsibility(IResponsibility2 next) {
            this.next = next;
        }
        @Override
        void handle(String request) {
            if ("C".equals(request)) {
                System.out.println("CResponsibility handle :"+request);
            }
            if (next != null) {
                next.handle(request);
            }
        }
    }

    static class DResponsibility extends IResponsibility2 {
        private DResponsibility(){}

        public DResponsibility(IResponsibility2 next) {
            this.next = next;
        }
        @Override
        void handle(String request) {
            if ("D".equals(request)) {
                System.out.println("DResponsibility handle :"+request);
            }
            if (next != null) {
                next.handle(request);
            }
        }
    }
    static class ChainOfResponsibility2 extends IResponsibility2 {
        private ChainOfResponsibility2(){}

        public ChainOfResponsibility2(IResponsibility2 next) {
            this.next = next;
        }
        @Override
        void handle(String request) {
            if (next != null) {
                next.handle(request);
            }
        }
    }

    static void main(){
        System.out.println("-----------方法一:接口方法中传入责任链对象,有责任链对象调度各个责任对象处理-------------");
        ChainOfResponsibility cor = new ChainOfResponsibility();
        cor.add(new AResponsibility()).add(new BResponsibility());
        String req = "A";
        cor.handle(req,cor);

        System.out.println("-----------方法二:责任对象中添加下一个责任对象为对象属性-------------");

        ChainOfResponsibility2 cor2 = new ChainOfResponsibility2(new CResponsibility(new DResponsibility(null)));
        String req2 = "D";
        cor2.handle(req2);
    }
    public static void main(String[] args) {
        main();
    }
}
