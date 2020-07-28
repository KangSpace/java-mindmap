package org.kangspace.common;

public class CourseTest20200610 {
    public static void main(String[] args) {
        scoreLevelTest();
        bollFalldownTest();
    }

    public static void scoreLevelTest() {
        System.out.println("100:"+getLevelByScore(100));
        System.out.println("91:"+getLevelByScore(91));
        System.out.println("80:"+getLevelByScore(80));
        System.out.println("60:"+getLevelByScore(60));
        System.out.println("30:"+getLevelByScore(30));
    }

    public static char getLevelByScore(float score) {
        if (score >= 90 && score <= 100) {
            return 'A';
        } else if (score >= 60 && score <= 89) {
            return 'B';
        }else if (score<60){
            return 'C';
        }
        throw new IllegalArgumentException("分数输入错误");
    }

    public static void bollFalldownTest() {
        System.out.println("小球下落10次经过的路径长度为: "+bollFalldown(10));
        System.out.println("小球下落10次经过的路径长度为: "+bollFalldown2(10));
    }

    public static double bollFalldown(int downCount){
        double height = 100D;
        double result = 0D;
        double cnt = 0D;
        for (int i = downCount; i > 0; i--) {
            result += height;
            height = height/2;
            if(i>1) {
                result += height;
                cnt += height;
            }
        }
        System.out.println("cnt:"+cnt);
        System.out.println("cnt+heigh:"+(cnt+height));
        System.out.println("cnt+result:"+(cnt+result));
        return result;
    }
    public static double bollFalldown2(int downCount){
        double height = 100D;
        double result = 100D;
        for (int i = downCount; i > 1; i--) {
            result += height;
            height = height/2;
        }
        return result;
    }
}
