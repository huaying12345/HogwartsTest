/**
 * FileName: GetPosition
 * Author:   huaying
 * Date:     2021-3-11 17:08
 * Description: 返回 s2 在 s1 中的位置，要求不能使用 indexOf，并且输入任何字符串都能返回位置，如果没有，返回 0；
 * version: IT2021
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package review;


/**
 * @author：huaying
 * Date: 2021-3-11 17:08
 * @Description：返回 s2 在 s1 中的位置，要求不能使用 indexOf，并且输入任何字符串都能返回位置，如果没有，返回 0；
 */

public class GetPosition {

    public static Integer getPosition(String s1, String s2){
        int  n = 0 ;
        if (s1.isEmpty() || s2.isEmpty()){
            return n;
        }
        int l1 = s1.length();
        int l2 = s2.length();
        for(int i = 0; i < l1 - l2 + 1; i++){
            String str = s1.substring(i, l2 + i) ;
            if(str.equals(s2)){
                return i;
            }
        }
        return n;
    }


    public static void main(String[] args) {
        String s1 = "www.taobao.com";
        String s2 = "taobao";
        Integer index = getPosition(s1, s2);
        System.out.println(index);
    }
}