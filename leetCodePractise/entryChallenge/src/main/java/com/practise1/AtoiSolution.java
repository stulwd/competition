package com.practise1;

public class AtoiSolution {

    public int myAtoi(String s) {
        int res = 0;
        int index = 0;
        char sign = '+';
        while(true){
            if(index > s.length() - 1){
                return 0;
            }else if(s.charAt(index) >= '0' && s.charAt(index) <= '9'){
                break;
            }else if (s.charAt(index) == ' '){
                index++;
                continue;
            }else if (s.charAt(index) == '+' || s.charAt(index) == '-'){
                sign = s.charAt(index);
                index++;
                break;
            }else{
                return 0;
            }
        }
        while (true){
            if (index > s.length() - 1){
                break;
            }
            if (!(s.charAt(index) >= '0' && s.charAt(index) <= '9')){
                break;
            }

            if (sign == '+'){
                if (res > 214748364 || ( res == 214748364 && (s.charAt(index) - '0') > 7 )){
                    return 2147483647;
                }
            }
            if (sign == '-'){
                if (res > 214748364 || ( res == 214748364 && (s.charAt(index) - '0') > 8 )){
                    return -2147483648;
                }
            }

            res = res * 10 + (s.charAt(index) - '0');
            index++;
        }

        return (sign == '+')? res : -res;
    }
}
