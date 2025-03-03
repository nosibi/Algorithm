package strange;

public class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean toUpper = true;
        for(char c : s.toCharArray()){
            if(!Character.isAlphabetic(c)){
                sb.append(c);
                toUpper = true;
            }else{
                //알파벳인 경우
                if(toUpper){
                    sb.append(Character.toUpperCase(c));
                }else{
                    sb.append(Character.toLowerCase(c));
                }
                //작업이 끝나면 toUpper를 반대로 바꾼다
                toUpper = !toUpper;
            }
        }
        String answer = "";
        answer = sb.toString();
        return answer;
    }

    //아이디어는 좋았으나....split로 공백을 제거해버리면 원본 공백을 유지할 수 없다
    //예를 들어, 테스트 케이스가 " Hello My name is " 일 경우 다시 join을 해도 "Hello My name is"가 되어 앞 뒤 공백이 사라지게 됨
//    public String solution(String s) {
//        String[] strings = s.split(" ");
//        for(int i = 0; i < strings.length; i++){
//            strings[i] = rule(strings[i]);
//        }
//        String answer = "";
//        answer = String.join(" ",strings);
//        return answer;
//    }
//
//    private String rule(String s){
//        char[] chars = s.toCharArray();
//        for(int i = 0; i < chars.length; i++){
//            if(i%2 == 0){
//                chars[i] = Character.toUpperCase(chars[i]);
//            }else chars[i] = Character.toLowerCase(chars[i]);
//        }
//        //chars.toString()으로 하면 배열의 메모리 주소를 문자열로 변환하게 되므로 사용하지 않도록 주의!
//        return new String(chars);
//    }
}
