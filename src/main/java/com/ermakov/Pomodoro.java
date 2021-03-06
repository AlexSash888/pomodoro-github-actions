package com.ermakov;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pomodoro {

    static boolean isTest = false;

    public static void main(String[] args) throws InterruptedException {


        //     -w 1 -b 1 -count 1 -t

        //время работы
        int workMin = 25;

        //время отдыха
        int breakMin = 5;

        // время подходов
        int count = 1;
        int sizePrint = 30;


        boolean isCallHelp = false;


        System.out.println("Chao Pomodoro. Напиши пожалуйста команду");

            String[] cmd = new Scanner(System.in).nextLine().split(" ");

            System.out.println(Arrays.toString(cmd));

            for (int i = 0; i < cmd.length; i++) {

                switch (cmd[i]) {
                    case "-help" -> {
                        printHelpMsg();
                        isCallHelp = true;
                    }
                    case "-w" -> workMin = Integer.parseInt(cmd[++i]);
                    case "-b" -> breakMin = Integer.parseInt(cmd[++i]);
                    case "-count" -> count = Integer.parseInt(cmd[++i]);
                    case "-t" -> isTest = true;
                }
            }


        if (!isCallHelp) {
            System.out.printf("Параметры программы: Работаем %d мин," +
                            " Отдыхаем %d мин, Кол-во подходов %d\n",
                    workMin, breakMin, count);

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                timer(workMin, breakMin, sizePrint);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Pomodoro таймер истек: " + (endTime - startTime) / (1000 * 60));
        }
    }



    private static void printHelpMsg() {
        System.out.println("--help: меню помощи\n");
        System.out.println("\nPomodoro - сделай свое время более эффективным\n");
        System.out.println("-w <time>: время работы, сколько хочешь работать\n");
        System.out.println("-b <time>: время отдыха, сколько хочешь отдыхать\n");
        System.out.println("-count <count>: количество итераций\n");
        System.out.println("-t <test>: тестовая реализация\n");

    }

    private static void timer(int workTime, int breakTime, int sizeProgressBar) throws InterruptedException {
        printProgress("Время вкалывать: ", workTime, sizeProgressBar);

        printProgress("Время отдыхать: ", breakTime, sizeProgressBar);

    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / size;
        rep = 60 * time / length;
        int stretchb = size / (3 * time);
        for (int i = 1; i <= rep; i++) {
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x / w) * 1000;
            x /= stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) + "[" + ("#").repeat(i) + ("-").repeat(rep - i) + "]    ( " + x + "min / " + time + "min )" + "\r");
            if (!isTest) {
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }
}
