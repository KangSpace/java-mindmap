package org.kangspace.common.algorithms;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成n对括号有效括号数组
 * @author kango2gler@gmail.com
 * @since 2022/7/21
 */
@Slf4j
public class GenerateParenthesis {

    public static void generateOneByOne(List<String> result, int left, int right, String lastResult) {
        if (left == 0 && right == 0) {
            result.add(lastResult);
            return;
        }
        if (left > 0) {
            generateOneByOne(result, left - 1, right, lastResult + "(");
        }
        if (right > left) {
            generateOneByOne(result, left, right - 1, lastResult + ")");
        }
    }

    public static void generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateOneByOne(result, n , n, "");
        log.info("result:{}",result);
    }

    public static void main(String[] args) {
        generateParenthesis(3);
    }

}
