package com.practise1;

public class PalindromeSolution {
    /**
     * method: expand from center
     * 2 for loop need to be optimized
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        int max = 0;
        for (int i = 0; i < chars.length; i++){
            int len = 0;
            while(true){
                if ((i - len < 0) ||
                    (i + len >= chars.length) ||
                    (chars[i + len] != chars[i - len])){
                    break;
                }
                len++;
            }
            int realLen = (len - 1) * 2 + 1;
            if (realLen > max){
                max = realLen;
                start = i - (len - 1);
                end = i + (len - 1);
            }
        }
        for (int i = 0; i < chars.length - 1; i++){
            int len = 0;
            while(true){
                if ((i - len < 0) ||
                    (i + 1 + len >= chars.length) ||
                    (chars[i + 1 + len] != chars[i - len])){
                    break;
                }
                len++;
            }
            int realLen = (len - 1) * 2 + 2;
            if (realLen > max){
                max = realLen;
                start = i - (len - 1);
                end = i + 1 + (len - 1);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = start; i <= end; i++){
            res.append(chars[i]);
        }
        return res.toString();
    }

    /**
     * method: dp
     */
    public String longestPalindrome2(String s) {
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        /* update state of items for that the length is 1 and 2*/
        for (int i = 0; i < chars.length - 1; i++){
            dp[i][i] = true;
            if (chars[i] == chars[i+1]){
                dp[i][i+1] = true;
            }else{
                dp[i][i+1] = false;
            }
        }
        dp[chars.length - 1][chars.length - 1] = true;

        /* update state of items for that the length is greater than and equals to 3 */
        for (int L = 3; L <= chars.length; L++){
            for (int j = 0; j <= chars.length - L; j++){
                dp[j][j + L - 1] = (chars[j] == chars[j + L - 1]) && (dp[j + 1][j + L - 1 - 1]);
            }
        }

        int start = 0;
        int end = 0;
        int max = 0;
        for (int L = 1; L <= chars.length; L++){
            for (int j = 0; j <= chars.length - L; j++){
                if (dp[j][j+L-1] == true){
                    if (L > max){
                        max = L;
                        start = j;
                        end = j + L - 1;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
