package distance;

public class Solution {
    //방향 배열(상,좌,우,하)
    private static final int[] dx = {0,-1,1,0};
    private static final int[] dy = {-1,0,0,1};
    //1. String 배열을 room 당 2차원 char 배열로 변환
    public int[] solution(String[][] places){
        int[] answer = {};
        answer = new int[places.length];
        for(int i = 0; i < places.length; i++){
            String[] place = places[i];
            char[][] room = new char[place.length][];
            for(int j = 0; j < room.length; j++){
                room[j] = place[j].toCharArray();
            }
            if(isDistanced(room)){
                answer[i] = 1;
            }else answer[i] = 0;
        }
        return answer;
    }
    //2. 2차원 char 배열을 순회하면서 'P'가 있을 경우 이동 시작
    private boolean isDistanced(char[][] room){
        for(int y = 0; y < room.length; y++){
            for(int x = 0; x < room[y].length; x++){
                if(room[y][x] != 'P')
                    continue;
                if(!isDistanced(room,x,y))
                    return false;
            }
        }
        return true;
    }

    //3. 1차 상좌우하 이동
    private boolean isDistanced(char[][] room, int x, int y){
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length)
                continue;
            switch (room[ny][nx]){
                case 'P' : return false;
                case 'O' :
                    if(isNextToPerson(room,nx,ny,3-d) == true){
                        return false;
                    }break;
            }
        }
        return true;
    }
    //4. 1차 이동 후 2차 이동
    private boolean isNextToPerson(char[][] room, int x, int y, int reverse){
        for(int d = 0; d < 4; d++){
            if(d==reverse)
                continue;
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length)
                continue;
            //P가 하나라도 있는 경우를 찾아야 함
            if(room[ny][nx] == 'P'){
                return true;
            }
        }
        return false;
    }
}