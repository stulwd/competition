package com.practise1;

public class ZTransformation {
    public String convert(String s, int numRows){
        if (numRows == 1){
            return s;
        }
        int len = s.length();
        int T = numRows * 2 - 2;
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < numRows; r++){
            if (r == 0){        // first line
                int i = 0;
                while(0 + i * T <= chars.length - 1){
                    sb.append(chars[0 + i * T]);
                    i++;
                }
            }else if (r == numRows - 1){    // last line
                int i = 0;
                while(numRows - 1 + i * T <= chars.length - 1){
                    sb.append(chars[numRows - 1 + i * T]);
                    i++;
                }
            }else{
                int i = 0;
                while(true){
                    if (r + i * T <= chars.length - 1){
                        sb.append(chars[r + i * T]);
                    }else{
                        break;
                    }

                    if (T + i * T - r <= chars.length - 1){
                        sb.append(chars[T + i * T - r]);
                    }else{
                        break;
                    }
                    i++;
                }
            }
        }
        return sb.toString();
    }
}
