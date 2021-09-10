package com.practise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegexMatch {
    /* mississippi  mis*is*p*. */
    public boolean isMatch(String s, String p) {
        char[] chars = s.toCharArray();
        List<String> patterns = new ArrayList<>();
        for (int i = 0; i < p.length(); i++){
            if ( (i != p.length() - 1) && (p.charAt(i + 1) == '*' )){
                patterns.add(p.substring(i, i+2));
                i++;
            }else {
                patterns.add(p.substring(i, i + 1));
            }
        }
        String[] pUnits = patterns.toArray(new String[]{});
        System.out.println(Arrays.toString(pUnits));
        return isMatch(chars, pUnits, 0, 0);
    }

    public boolean isMatch(char[] chars, String[] pUnits, int c_index, int p_index){
        if ( (c_index == chars.length) && (p_index == pUnits.length) ){
            return true;
        }
        if ( (c_index < chars.length) && (p_index == pUnits.length) ){
            return false;
        }
        if ( (c_index == chars.length) && (p_index < pUnits.length) ){
            if (pUnits[p_index].length() == 1){
                return false;
            }
            return isMatch(chars, pUnits, c_index, p_index + 1);
        }


        if (pUnits[p_index].length() == 1){
            if ( ( chars[c_index] == pUnits[p_index].charAt(0) ) || pUnits[p_index].charAt(0) == '.'){
                return isMatch(chars, pUnits, c_index + 1, p_index + 1);
            }else{
                return false;
            }
        }else{
            boolean res1 = false;
            boolean res2 = false;
            if ((chars[c_index] == pUnits[p_index].charAt(0)) || (pUnits[p_index].charAt(0) == '.')){
//                char pre = pUnits[p_index].charAt(0);
//                pUnits[p_index] = String.valueOf(chars[c_index]) + "*";
                res1 = true && isMatch(chars, pUnits, c_index + 1, p_index);
//                pUnits[p_index] = String.valueOf(pre) + "*";
            }else{
                res1 = false;
            }

            res2 = isMatch(chars, pUnits, c_index, p_index + 1);
            return res1 || res2;
        }
    }


}
