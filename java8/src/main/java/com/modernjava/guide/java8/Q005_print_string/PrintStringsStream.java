package com.modernjava.guide.java8.Q005_print_string;

import com.modernjava.guide.java8.util.ComparisonUtils;

import java.util.Arrays;

/**
 * search strings containing a substring using Stream API
 */
public class PrintStringsStream {

    public static void main(String[] args) {
        System.out.println("Q005: Print All Strings containing substring '_test' using Stream API");
        ComparisonUtils.repeat("-",80);;
        String[] strings = ComparisonUtils.getStringArrayWithPatternAtEnd(40,"_test");
                //{"Apple_test", "Banana", "Cherry_test", "Date", "Elderberry", "Fig_test", "Grape", "Honeydew_test"};
        String subString = "_test";

        System.out.println("Input with length "+strings.length+": ");
        ComparisonUtils.printStrings(strings);

        System.out.println("Output: ");
        searchStrings(strings, subString);
    }


    public static void searchStrings(String[] strings, String subString){
        Arrays.stream(strings)
                .filter(n->n.contains(subString))
                .forEach(n->System.out.print(n+","));
    }



}
