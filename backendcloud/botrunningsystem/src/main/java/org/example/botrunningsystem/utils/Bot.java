//package org.example.botrunningsystem.utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bot implements org.example.botrunningsystem.utils.BotInterface{
//    static class Cell {
//        public int x, y;
//        public Cell(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    private boolean check_tail_increasing(int step) {  // 检验当前回合，蛇的长度是否增加
//        if (step <= 10) return true;
//        return step % 3 == 1;
//    }
//
//    public List<Cell> getCells(int sx, int sy, String steps) {
//        steps = steps.substring(1, steps.length() - 1);
//        List<Cell> res = new ArrayList<>();
//
//        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
//        int x = sx, y = sy;
//        int step = 0;
//        res.add(new Cell(x, y));
//        for (int i = 0; i < steps.length(); i ++ ) {
//            int d = steps.charAt(i) - '0';
//            x += dx[d];
//            y += dy[d];
//            res.add(new Cell(x, y));
//            if (!check_tail_increasing( ++ step)) {
//                res.remove(0);
//            }
//        }
//        return res;
//    }
//
//    @Override
//    public Integer nextMove(String input) {
//        String[] strs = input.split("#");
//        int[][] g = new int[13][14];
//        for (int i = 0, k = 0; i < 13; i ++ ) {
//            for (int j = 0; j < 14; j ++, k ++ ) {
//                if (strs[0].charAt(k) == '1') {
//                    g[i][j] = 1;
//                }
//            }
//        }
//
//        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);
//        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);
//
//        List<Cell> aCells = getCells(aSx, aSy, strs[3]);
//        List<Cell> bCells = getCells(bSx, bSy, strs[6]);
//
//        for (Cell c: aCells) g[c.x][c.y] = 1;
//        for (Cell c: bCells) g[c.x][c.y] = 1;
//
//        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
//        for (int i = 0; i < 4; i ++ ) {
//            int x = aCells.get(aCells.size() - 1).x + dx[i];
//            int y = aCells.get(aCells.size() - 1).y + dy[i];
//            if (x >= 0 && x < 13 && y >= 0 && y < 14 && g[x][y] == 0) {
//                return i;
//            }
//        }
//
//        return 0;
//    }
//}
//
package org.example.botrunningsystem.utils;

import java.util.*;

public class Bot implements org.example.botrunningsystem.utils.BotInterface {
    static class Cell {
        public int x, y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean check_tail_increasing(int step) {
        if (step <= 10) return true;
        return step % 3 == 1;
    }

    public List<Cell> getCells(int sx, int sy, String steps) {
        steps = steps.substring(1, steps.length() - 1);
        List<Cell> res = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for (int i = 0; i < steps.length(); i++) {
            int d = steps.charAt(i) - '0';
            x += dx[d];
            y += dy[d];
            res.add(new Cell(x, y));
            if (!check_tail_increasing(++step)) {
                res.remove(0);
            }
        }
        return res;
    }

    static class State {
        int x, y, step;
        List<Integer> path;

        public State(int x, int y, int step, List<Integer> path) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.path = new ArrayList<>(path);
        }
    }

    private int heuristic(int x, int y, int targetX, int targetY) {
        return Math.abs(x - targetX) + Math.abs(y - targetY);
    }

    private List<Integer> getPossibleMoves(int[][] grid, int sx, int sy) {
        List<Integer> possibleMoves = new ArrayList<>();
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int x = sx + dx[i];
            int y = sy + dy[i];
            if (x >= 0 && x < 13 && y >= 0 && y < 14 && grid[x][y] == 0) {
                possibleMoves.add(i);
            }
        }
        return possibleMoves;
    }

    @Override
    public Integer nextMove(String input) {
        String[] strs = input.split("#");
        int[][] g = new int[13][14];
        for (int i = 0, k = 0; i < 13; i++) {
            for (int j = 0; j < 14; j++, k++) {
                if (strs[0].charAt(k) == '1') {
                    g[i][j] = 1;
                }
            }
        }

        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);
        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);

        List<Cell> aCells = getCells(aSx, aSy, strs[3]);
        List<Cell> bCells = getCells(bSx, bSy, strs[6]);

        for (Cell c : aCells) g[c.x][c.y] = 1;
        for (Cell c : bCells) g[c.x][c.y] = 1;

        List<Integer> possibleMoves = getPossibleMoves(g, aCells.get(aCells.size() - 1).x, aCells.get(aCells.size() - 1).y);

        if (possibleMoves.isEmpty()) {
            return 0;  // Default move if no safe move is found
        }

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.step));
        Set<String> visited = new HashSet<>();
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

        pq.offer(new State(aCells.get(aCells.size() - 1).x, aCells.get(aCells.size() - 1).y, 0, new ArrayList<>()));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            String stateStr = cur.x + "," + cur.y;
            if (visited.contains(stateStr)) continue;
            visited.add(stateStr);

            for (int move : possibleMoves) {
                int nx = cur.x + dx[move], ny = cur.y + dy[move];
                if (nx >= 0 && nx < 13 && ny >= 0 && ny < 14 && g[nx][ny] == 0) {
                    List<Integer> newPath = new ArrayList<>(cur.path);
                    newPath.add(move);
                    pq.offer(new State(nx, ny, cur.step + 1, newPath));
                    if (newPath.size() == 1) {
                        return newPath.get(0);
                    }
                }
            }
        }

        return possibleMoves.get(0);  // Return the first possible move if no better move is found
    }
}
