package triangle;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] solution(int n) {
        //2차원 배열의 삼각형 불러오기
        int[][] triangle = triangle(n);
        //리스트에 요소 추가(0인 요소는 추가하지 않음)
        List<Integer> solutionList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i+1; j++){
                solutionList.add(triangle[i][j]);
            }
        }
        //리스트 크기의 1차원 배열 생성
        int[] answer = new int[solutionList.size()];
        //리스트 요소를 1차원 배열로 옮기기
        for(int i = 0; i < solutionList.size(); i++){
            answer[i] = solutionList.get(i);
        }
        return answer;
    }
    //2차원 배열의 삼각형 생성
    public int[][] triangle(int n){
        int[][] triangle = new int[n][n];
        int end = 0;
        for(int i = 1; i <= n; i++){
            end += i;
        }
        int value = 1;
        int x = 0;
        int y = 0;
        //사이클
        while(true){
            //하단 이동
            while(true){
                triangle[y][x] = value;
                value++;
                if(y+1 == n || triangle[y+1][x] != 0) break;
                y++;
            }
            if(value > end) break;
            x++;
            //우측 이동
            while(true){
                triangle[y][x] = value;
                value++;
                if(x+1 == n || triangle[y][x+1] != 0) break;
                x++;
            }
            if(value > end) break;
            y--;
            x--;
            //좌대각 이동
            while(true){
                triangle[y][x] = value;
                value++;
                if((x==1 && y==1) || triangle[y-1][x-1] != 0) break;
                x--;
                y--;
            }
            y++;
            if(value > end) break;
        }
        return triangle;
    }
}
