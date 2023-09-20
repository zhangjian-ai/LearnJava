package com.zhangjian.前期准备.递归;

import java.util.Arrays;

public class RecursionExercise {
    public static void main(String[] args) {
        Exercise exercise = new Exercise();

        // 斐波那契
//        int idx = 10;
//        int fibonacci = exercise.fibonacci(idx);
//        System.out.println(String.format("第 %d 位的斐波那契数值为：%d", idx, fibonacci));
//        System.out.println("-------------------------");

        // 猴子吃桃
//        int day = 9;
//        int peaches = exercise.monkeyEatPeach(day);
//        System.out.println(String.format("第 %d 天的桃子数量：%d", day, peaches));
//        System.out.println("-------------------------");


        // 迷宫
//        int x = 1, y = 1;
//        int[][] map = exercise.createMap();
//        System.out.println("迷宫如下：");
//        exercise.showMap(map);
//        if(exercise.findWay(map, x, y)){
//            System.out.println("\n找到迷宫出口");
//        }else {
//            System.out.println("\n很抱歉，没有找到迷宫出口");
//        }
//        exercise.showMap(map);
//        System.out.println("-------------------------");

        // 汉诺塔
//        exercise.hanoiTower(5, 'A', 'B', 'C');
//        System.out.println("-------------------------");

        // 八皇后
        exercise.eightQueen(0, new int[8], new int[1]);
        System.out.println("-------------------------");

    }

}

class Exercise {
    /**
     * 求指定位置处的斐波那契数是多少
     *
     * @param n
     * @return
     */
    public int fibonacci(int n) {
        if (n <= 0) {
            System.out.println("无效的索引值");
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 猴子吃桃
     * 有一堆桃子，猴子第一天吃了一半后再多吃一个；然后每天猴子都吃剩下的一般，再多吃一个。到第10天的时候，猴子还没吃
     * 发现只剩下1个桃子了，求第一天时有多少个桃子
     *
     * @param day
     * @return
     */
    public int monkeyEatPeach(int day) {
        if (day < 1 || day > 10) {
            System.out.println("无效的天数，有效值范围 1～10");
        }
        if (day == 10) {
            return 1;
        }
        return (monkeyEatPeach(day + 1) + 1) * 2;
    }

    /**
     * 创建一个8行7列的迷宫
     * 0 表示可通行的格子，1 表示墙体
     *
     * @return
     */
    public int[][] createMap() {
        int[][] map = new int[8][7];

        // 迷宫四周都是墙体
        for (int row = 0; row < 8; row++) {
            if (row == 0 || row == 7) {
                Arrays.fill(map[row], 1);
            } else {
                map[row][0] = 1;
                map[row][6] = 1;
            }
        }

        // 迷宫第四行前三格是墙体
        map[3][1] = 1;
        map[3][2] = 1;

        // 返回一个迷宫
        return map;
    }

    public void showMap(int[][] map){
        // 打印迷宫
        for (int[] row : map) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    /**
     * 找到迷宫的出口：
     * 1、约定迷宫出口位置坐标是 row-1,col-1 本例中的出口即是 map[6][5]
     * 2、对于可以通行的位置，当到达后如果可以继续往后走 就标记为2；如果是死胡同则标记为3；
     * 3、对于走过的路要避免不重复走，否则会陷入死循环
     * 4、路径查找方向为 下 右 上 左
     *
     * @param map 迷宫对象
     * @param x   起始行位置
     * @param y   起始列位置
     * @return 是否找到出路
     */
    public boolean findWay(int[][] map, int x, int y) {
        // 判断当前位置是不是通路或没有走过的路
        if (map[x][y] != 0) {
            return false;
        }

        // 递归结束条件
        if (x == 6 && y == 5) {
            map[x][y] = 2;
            return true;
        }

        // 如果当前位置的任一一个方向不是墙体则表示可以走通
        // 在往下一个放下探路时，避免走回头路先把当前路标记成通路
        map[x][y] = 2;
        if (findWay(map, x + 1, y) || findWay(map, x, y + 1) || findWay(map, x - 1, y) || findWay(map, x, y - 1)) {
            return true;
        }

        // 否则表示当前位置是死胡同
        map[x][y] = 3;
        return false;
    }

    /**
     * 汉诺塔传说：
     * 这是印度的一个古老传说，现在衍生成了益智游戏。
     * 大梵天在创造世界时，做了三根金刚石柱子，在一根柱子上从上往下按大小顺序放着64个圆盘，大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放到
     * 另一根柱子上。并且规定，移动过程中大圆盘不能放在小圆盘上面，切每次移动只能移动一个圆盘。
     *
     * 本例将 A柱 圆盘移动到 C柱
     *
     * @param num 汉诺塔第一根柱子上圆盘的数量
     * @param A A柱
     * @param B B柱
     * @param C C柱
     */
    public void hanoiTower(int num, char A, char B, char C){
        // 如果只有一个圆盘则直接移动
        if(num == 1){
            System.out.println(A + "->" + C);
        }else {
            // 如果有多个圆盘就把最下面当成一个，上面剩下所有的当成一个。即整体看做两个圆盘来移动
            // 1、现将上面的放到 B 柱
            hanoiTower(num - 1, A, C, B);
            // 2、把最下面的一个圆盘放到 C柱
            hanoiTower(1, A, B, C);
            // 3、把 B柱 上的圆盘放到 C柱 ，这样就完成了移动
            hanoiTower(num - 1, B, A, C);
        }
    }

    /**
     * 八皇后问题：
     * 在 8*8 格的国际象棋棋盘上摆上8个皇后，使其不能互相攻击。即任意两个皇后不能处于同一行、同一列、同一斜线上
     * @param row 摆放皇后时的行数，开始行数是第 0 行
     * @param res 保存结果的数组。索引表示行数，值表示当前行的皇后摆放在第几列
     */
    public void eightQueen(int row, int[] res, int[] count){
        // 摆放皇后
        if( 0<= row && row <= 7){

            for(int i = 0;i < 8; i++){
                // 每一次循环就是找了中可能的一种摆放位置
                if(row == 0){
                    Arrays.fill(res, -1);
                }
                // 判断当前行当前列是否有其他皇后，只要数组中记录了当前列号表示该列已经被其他皇后占领
                boolean isExist = false;
                for(int idx: res){
                    if(idx == i){
                        isExist = true;
                        break;
                    }
                }

                // 如果当前列可用，就判断斜线上是否有皇后，就是判断当前格子所在行记录的列是不是当前列，如果是就代表被占用
                if(!isExist){
                    // 当前格子所在的坐标，斜线从当前格子发散，有四部分
                    int x = i;
                    int y = row;

                    // 左上
                    boolean lu = true;

                    while (x >=0 && y <= 7){
                        if(res[y] == x){
                            lu = false;
                            break;
                        }
                        x -= 1;
                        y += 1;
                    }

                    // 左下
                    x = i;
                    y = row;
                    boolean ld = true;

                    while (x >=0 && y >= 0){
                        if(res[y] == x){
                            ld = false;
                            break;
                        }
                        x -= 1;
                        y -= 1;
                    }

                    // 右上
                    x = i;
                    y = row;
                    boolean ru = true;

                    while (x <= 7 && y <= 7){
                        if(res[y] == x){
                            ru = false;
                            break;
                        }
                        x += 1;
                        y += 1;
                    }

                    // 右下
                    x = i;
                    y = row;
                    boolean rd = true;

                    while (x <= 7 && y >= 0){
                        if(res[y] == x){
                            rd = false;
                            break;
                        }
                        x += 1;
                        y -= 1;
                    }

                    if (lu && ld && ru && rd){
                        // 将当前列号记录为当前行皇后的位置
                        res[row] = i;
                    }

                }

                // 如果当前行还能放，就尝试往下一行放
                if(res[row] != -1){
                    eightQueen(row + 1, res, count);

                    if(res[7] != -1){
                        count[0] += 1;
                        System.out.print("第" + count[0] + "种解法是:");
                        for(int val: res){
                            System.out.print(val + " ");
                        }
                        System.out.println();
                    }
                    res[row] = -1;
                }
            }
        }
    }

}