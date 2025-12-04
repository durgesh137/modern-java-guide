package com.modernjava.guide.java8.Q005_print_string;

import com.modernjava.guide.java8.util.ComparisonUtils;

public class PrintStringsTraditional {
    public static void main(String[] args) {
        String substring = "_test";
        String[] strings = ComparisonUtils.getStringArrayWithPatternAtEnd(20,substring);
        System.out.println("Q005: Print All Strings containing substring '_test' using Traditional Approach");
        System.out.println(ComparisonUtils.repeat("-",80));;
        System.out.println("Input: ");
        ComparisonUtils.printStrings(strings);
        System.out.println("Output: ");
        printStrings(strings, substring);
    }

    public static void printStrings(String[] strings, String substring) {
        /**
         * Traditional approach to print all strings in an array.
         * Steps:
         * 1. Use a for-each loop to iterate through each string in the array.
         * 2. Print each string to the console.
         */
        for (String str : strings) {
            if(str.contains(substring))
                System.out.print(str+",");
        }
        System.out.println("\n");
    }
}
