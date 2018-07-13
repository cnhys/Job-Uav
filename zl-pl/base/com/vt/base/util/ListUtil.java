package com.vt.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p> Title:list utils </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:May 29, 20151:57:39 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	May 29, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class ListUtil {

    /**
     * judge Collection is empty or not
     *
     * @param c collection
     * @return true-empty;false-not empty
     */
    public static boolean isEmpty(Collection<?> c) {
        return !isNotEmpty(c);
    }

    /**
     * judge Collection is empty or not
     *
     * @param c collection
     * @return true-not empty;fase-empty
     */
    public static boolean isNotEmpty(Collection<?> c) {
        return c != null && c.size() > 0;
    }

    /**
     * array to list
     *
     * @param ts array
     * @return
     */
    public static <T> List<T> arrToList(T[] ts) {
        List<T> list = new ArrayList<T>();
        for (T t : ts) {
            list.add(t);
        }
        return list;
    }

    /**
     * string to list
     *
     * @param splitStr string
     * @param regex    split regex
     * @param n        to transition number
     * @return
     */
    public static List<String> strToStrList(String splitStr, String regex) {
        String[] strArr = splitStr.split(regex);
        return arrToList(strArr);
    }

    public static List<Integer> strToIntegerList(String splitStr, String regex) {
        String[] strArr = splitStr.split(regex);
        List<Integer> list = new ArrayList<Integer>();
        for (String str : strArr) {
            list.add(Integer.parseInt(str));
        }
        return list;
    }

}
