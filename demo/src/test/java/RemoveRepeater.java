/**
 * FileName: Test
 * Author:   huaying
 * Date:     2021-3-5 17:58
 * Description:
 * version: IT2021
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


import java.util.*;

/**
 * @author：huaying Date: 2021-3-5 17:58
 * @Description：元素去重
 */

public class RemoveRepeater {

    /*
        第一种方法：利用set去重
    */
    public static Object[] oneClear(Object[] arr) {
        Set set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            set.add( arr[i] );
        }
        return set.toArray();
    }

    public static Object[] twoClear(Object[] arr) {
        LinkedHashSet<Object> tmp = new LinkedHashSet<>();
        for (int i = 0; i < arr.length; i++) {
            tmp.add( arr[i] );
        }
        return tmp.toArray();
    }

    public static Object[] threeClear(Object[] arr) {
        List list = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            if (!list.contains( arr[i] )) {
                list.add( arr[i] );
            }
        }
        return list.toArray();
    }

    public static Object[] fourClear(Object[] arr) {
        int t = 0;

        //临时数组
        Object[] xinArr = new Object[arr.length];

        for (int i = 0; i < arr.length; i++) {
            //声明标记，是否重复
            boolean isRepeat = true;
            for (int j = i + 1; j < arr.length; j++) {
                //如果有重复元素，将标记置为false
                if (arr[i] == arr[j]) {
                    isRepeat = false;
                    break;
                }
            }
            //标记为true表示没有重复元素
            if (isRepeat) {
                xinArr[t] = arr[i];
                t++;
            }
        }
        //去重后数组
        Object[] newArr = new Object[t];
        System.arraycopy( xinArr, 0, newArr, 0, t );
        return newArr;
    }


    public static void main(String[] args) {
        String[] str = {"a", "b", "b", "c", "f", "d", "e"};

        String str1 = String.valueOf( oneClear( str ) );
        System.out.println( "\nMethod 1:" );
        printArray( oneClear( str ) );
        System.out.println("\n Method 2:" );
        printArray( twoClear( str ) );
        System.out.println("\n Method 3:" );
        printArray( threeClear( str ) );
        System.out.println("\n Method 4:" );
        printArray( fourClear( str ) );
    }

    public static void printArray(Object[] arr) {
        for (Object object : arr) {
            System.out.print( object + " " );
        }

    }


}