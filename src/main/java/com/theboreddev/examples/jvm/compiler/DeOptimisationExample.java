package com.theboreddev.examples.jvm.compiler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DeOptimisationExample {

    public static void main(String[] args) {

        LogPrinter logPrinter;

        for (int i = 0; i < 1000000; i++) {
            if (i < 700000) {
                logPrinter = new ConsoleLogPrinter();
            } else {
                logPrinter = new FileLogPrinter();
            }
            logPrinter.printLog("This is log number " + i + " using printer " + logPrinter.getClass().getSimpleName());
        }
    }

    interface LogPrinter {
        void printLog(String log);
    }

    static class ConsoleLogPrinter implements LogPrinter {

        @Override
        public void printLog(String log) {
            System.out.print("");
        }
    }

    static class FileLogPrinter implements LogPrinter {

        @Override
        public void printLog(String log) {
            try (FileWriter fw = new FileWriter("logs.txt", true);
                 BufferedWriter bf = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bf)) {
                pw.println(log);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
