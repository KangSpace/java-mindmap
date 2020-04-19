package org.kangspace.common.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 解释器模式
 * 给定一种语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子
 * 适用性：
 *  当有一个语言需要解释执行，并且你可以将语言中的句子表示为一个抽象语法树时，可使
 *  用解释器模式。当存在以下情况是效果最好：
 *  1. 该文法简单对于复杂的文法，文法的类层次变得庞大而无法管理。
 *  2. 效率不是一个关键问题，最高效的解释器通常不是通过直接解释语法分析树实现的，而是首先将他们转换为另一种形式
 * 参与者:
 *  1. 抽象表达式
 *     定义一个解释操作
 *  2. 终结符表达式
 *  3. 非终结符表达式
 *  4. Context 上下文对象
 *     保存除解释器外的其他全局对象
 *  5. Client
 *     构建表示该文法定义中的一个特定语句中的抽象语法树，该语法树有终结符表达式和非终结符表达式构建，调用解析操作。
 * @author kango2gler@gmail.com
 */
public class InterpreterPatternTest {
    interface Expression{
        void interpreter(Context context);
    }

    /**
     * 增强解释器
     */
    static class AdvanceExpression implements Expression{
        @Override
        public void interpreter(Context context) {
            System.out.println("这是增强解析器:"+context.getContent());
        }
    }
    static class DefaultExpression implements Expression{
        @Override
        public void interpreter(Context context) {
            System.out.println("这是默认解析器:"+context.getContent());
        }
    }
    static class Context {
        private String content;
        private List<Expression> expressions = new ArrayList<>();

        public Context(String content) {
            this.content = content;
        }
        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void add(Expression expression) {
            expressions.add(expression);
        }
        public List<Expression> getList(){
            return expressions;
        }
    }


    static void main(){
        Context context = new Context("public void main()");
        context.add(new DefaultExpression());
        context.add(new AdvanceExpression());
        context.getList().forEach(t->{
            t.interpreter(context);
        });
    }
    public static void main(String[] args) {
        main();
    }
}
