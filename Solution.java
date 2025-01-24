package intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    //교점 객체
    private class Intersection{
        private int x;
        private int y;

        public Intersection(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    //교점 객체를 구하는 기능
    private Intersection getIntersection(int a, int b, int e, int c, int d, int f){
        if(((a*d)-(b*c))==0){
            return null;
        }
        float x = (float)((b*f)-(e*d)) / ((a*d)-(b*c));
        float y = (float)((e*c)-(a*f)) / ((a*d)-(b*c));

        if(x%1 != 0 || y%1 != 0){
            return null;
        }
        return new Intersection((int)x,(int)y);
    }

    //교점을 구하는 기능
    private List<Intersection> getIntersectionList(int[][] line){
        List<Intersection> intersectionList = new ArrayList<>();
        for(int i = 0; i < line.length-1; i++){
            for(int j = 1+i; j < line.length; j++){
                if(getIntersection(line[i][0],line[i][1],line[i][2],line[j][0],line[j][1],line[j][2]) != null){
                    intersectionList.add(getIntersection(line[i][0],line[i][1],line[i][2],line[j][0],line[j][1],line[j][2]));
                }
            }
        }
        return intersectionList;
    }

    //최대,최소값을 저장하는 객체
    private class maxAndmin{
        int MIN_X;
        int MAX_X;
        int MIN_Y;
        int MAX_Y;

        public maxAndmin(int MIN_X, int MAX_X, int MIN_Y, int MAX_Y) {
            this.MIN_X = MIN_X;
            this.MAX_X = MAX_X;
            this.MIN_Y = MIN_Y;
            this.MAX_Y = MAX_Y;
        }
        public int getMIN_X() {
            return MIN_X;
        }
        public int getMAX_X() {
            return MAX_X;
        }
        public int getMIN_Y() {
            return MIN_Y;
        }
        public int getMAX_Y() {
            return MAX_Y;
        }
    }

    //최대, 최소 구하는 기능
    private maxAndmin getMaxAndMin(int[][] line){
        List<Intersection> intersectionList = getIntersectionList(line);

        intersectionList.sort(Comparator.comparing(Intersection::getX));
        int MIN_X = intersectionList.getFirst().getX();
        int MAX_X = intersectionList.getLast().getX();

        intersectionList.sort(Comparator.comparing(Intersection::getY));
        int MIN_Y = intersectionList.getFirst().getY();
        int MAX_Y = intersectionList.getLast().getY();

        return new maxAndmin(MIN_X,MAX_X,MIN_Y,MAX_Y);
    }

    //격자판을 생성하는 기능
    public StringBuilder[] getSize(int[][] line){
        maxAndmin maxAndmin = getMaxAndMin(line);
        int MIN_X = maxAndmin.getMIN_X();
        int MAX_X = maxAndmin.getMAX_X();
        int MIN_Y = maxAndmin.getMIN_Y();
        int MAX_Y = maxAndmin.getMAX_Y();

        StringBuilder[] field = new StringBuilder[MAX_Y-MIN_Y+1];
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < MAX_X-MIN_X+1; j++){
                if(field[i] == null){
                    field[i] = new StringBuilder("");
                }
                field[i].append(".");
            }
        }
        return field;
    }

    //교점에 "*"을 찍는 기능
    public StringBuilder[] markStar(int[][] line){
        StringBuilder[] field = getSize(line);
        List<Intersection> intersectionList = getIntersectionList(line);
        maxAndmin maxAndmin = getMaxAndMin(line);

        for(int i = 0; i< intersectionList.size(); i++){
            int x = intersectionList.get(i).getX() - maxAndmin.getMIN_X();
            int y = maxAndmin.getMAX_Y() - intersectionList.get(i).getY();
            field[y].setCharAt(x,'*');
        }
        return field;
    }

    //StringBuffer 배열을 String 배열로 변경
    public String[] solution(int[][] line){
        StringBuilder[] field = markStar(line);
        String[] answer = new String[field.length];
        for(int i = 0; i < field.length; i++){
            answer[i] = field[i].toString();
        }
        return answer;
    }
}
