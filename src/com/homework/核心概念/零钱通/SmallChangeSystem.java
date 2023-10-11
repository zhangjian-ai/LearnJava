package com.homework.核心概念.零钱通;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSystem {
    // 时间格式化对象，用于格式化时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // 退出标识
    private boolean exit = false;

    // 交互操作
    private Scanner scanner = new Scanner(System.in);

    // 零钱明细
    private String detail = "\n-----------零钱明细-----------\n";

    // 账户金额
    private double account = 0.0;


    public void start() throws InterruptedException {
        System.out.println("你好，零钱通服务启动...");
        Thread.sleep(2000);

        // 调用主菜单
        mainMenu();
    }

    private void mainMenu() {
        String menu = "\n===========零钱通菜单===========\n"
                + "\t 1. 零钱明细\n"
                + "\t 2. 收益入账\n"
                + "\t 3. 消费支出\n"
                + "\t 4. 系统退出\n"
                + "请输入对应的编号继续操作:";

        do {
            System.out.print(menu);
            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    detail();
                    break;
                case 2:
                    income();
                    break;
                case 3:
                    pay();
                    break;
                case 4:
                    sysExit();
                    break;
                default:
                    System.out.print("操作编号有误，请重新输入\n");
            }

        } while (!exit);

    }

    private void detail() {
        System.out.println(detail);
    }

    private void income() {
        // 获取入账金额
        System.out.print("本次收入金额: ");
        double money = scanner.nextDouble();

        // 校验收入应该是整数
        if (money <= 0) {
            System.out.println("收益入账金额错误，收入金额应大于0");
            return;
        }

        // 账户增加
        account += money;

        // 增加零钱明细。生成记录时都用格式化器处理当前时间
        detail += "收益入账\t+" + money + "\t" + sdf.format(new Date()) + "\t余额: " + account + "\n";
    }

    private void pay() {
        // 获取消费支出
        System.out.print("消费支出金额: ");
        double money = scanner.nextDouble();

        // 校验收入应该是整数
        if (money <= 0 || money > account) {
            System.out.println("消费支出金额错误，消费金额应大于0小于" + account);
            return;
        }

        // 账户减少
        account -= money;

        // 增加零钱明细。生成记录时都用格式化器处理当前时间
        detail += "消费支出\t-" + money + "\t" + sdf.format(new Date()) + "\t余额: " + account + "\n";
    }

    private void sysExit() {
        // 获取消费支出
        System.out.print("您确认要退出系统吗？y/n : ");
        String flag = scanner.next();

        if (!flag.equals("y") && !flag.equals("n")) {
            System.out.println("退出指令不合法");
        }

        if (flag.equals("y")) {
            exit = true;
            System.out.println("零钱通退出，再见！");
        }
    }

}
