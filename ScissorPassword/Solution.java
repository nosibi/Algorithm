package ScissorPassword;

public class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++){
            sb.append(push(chars[i],n));
        }
        String answer = "";
        answer = sb.toString();
        return answer;
    }

    private char push(char c, int n){
        if(!Character.isAlphabetic(c)) return c;
        int start = Character.isUpperCase(c) ? 'A' : 'a';
        int position = c - start;
        position = (position + n) % ('Z' - 'A' + 1);
        char push = (char)(position + start);
        return push;
    }
}
