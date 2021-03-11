/**
 * FileName: Test
 * Author:   huaying
 * Date:     2021-2-26 17:12
 * Description:
 * version: IT2021
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


/**
 * @author：huaying
 * Date: 2021-2-26 17:12
 * @Description：
 */

public class Sort {

    public static int[] selectSort(int[] arr){
        int len = arr.length;
        int tmp, minIndex;
        for(int i = 0;i< len-1;i++){
            minIndex = i;
            for(int j = i+1;j< len;j++){
                if(arr[j]<arr[minIndex]) {
                    minIndex =j;
                }
            }
            tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
       return arr;

    }

    public static int[] bubbleSort(int[] a){
        int len = a.length;
        int tmp;
        for(int i=0; i< len -1; i++){
            for(int j =i+1; j<len; j++){
                if (a[i]>a[j]){
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        return a;
    }


    public static int[] quickSort(int[] a,int start, int end){
        if(start > end){
            return a;
        }

        int i = start;
        int j = end;
        int base = a[start];

        while(i<j){

            while(a[j] >= base && j>i){
                j--;
            }
            while(a[i]< base && i<j){
                i++;
            }
            if(i<j){
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        a[start] =  a[i];
        a[i] =base ;
        quickSort( a, start, i-1);
        quickSort( a, i+1, end );
        return a ;
    }


    public static void  main(String args[]){
         int a[] = {11,2,3,5,7,12,19};
//         int b[] = selectSort(a);
//         for(int i =0; i<b.length;i++){
//             System.out.println("选择排序后的数组: "+ b[i]);
//         }

//        int c[] = bubbleSort(a);
//        for(int i =0; i<c.length;i++){
//            System.out.println("冒泡排序后的数组: "+ c[i]);
//        }

        int d[] = quickSort(a,0,6);
        for(int i =0; i<d.length;i++){
            System.out.println("快速排序后的数组: " + d[i]);
        }
//        quickSort(a,0,6);
    }

}