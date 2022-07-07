package com.example.miu.utils;

/**
 * <Description> ArrayUtil
 *
 * @author 26802
 * @version 1.0
 * @ClassName ArrayUtil
 * @taskId
 * @see com.example.miu.utils
 */
import java.util.*;

public class ArrayUtil {

    //用来将(1,2,3)这样的字符串转成Integer数组
    public static  List<Integer>  str2IntegerList(String str){
        List<Integer> integerList = new ArrayList<>();
        String temp = str.replaceAll("[()]+", "");
        String[] strings = temp.split(","); // [1,2,3]

        for (String string : strings) {
            integerList.add(Integer.parseInt(string));
        }

        return integerList;
    }


}
