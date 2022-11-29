package ru.job4j.io;

import java.io.*;

public class Analizy {
    //split[0].equals("500") "500".equals(split[0])
    public void unavailable(String source, String target) {
        StringBuilder log = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                String line;
                while ((line = in.readLine()) != null) {
                    String[] split = line.split(" ");
                    if (log.length() == 0 && "500".equals(split[0]) || "400".equals(split[0])) {
                        log = new StringBuilder(split[1]);
                    } else if (log.length() != 0 && "200".equals(split[0]) || "300".equals(split[0])) {
                        log.append(";").append(split[1]).append(";");
                        out.println(log);
                        log = new StringBuilder();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Analizy test = new Analizy();
        test.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
