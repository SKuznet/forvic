package com.itmo;

import com.itmo.model.Brass;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    private static List<Brass> list = new Vector<>();

    public static void main(String[] args) {
        Main main = new Main();
        String path = "/tmp/foo/artists.xml";
//      String path = args[0];
        File file = main.getFile(path);
        try {
            Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)));

            while (scanner.hasNext()) {
                String val = scanner.nextLine();
                if (val.equals("<note>")) {
                    continue;
                } else if (val.equals("</note>")) {
                    continue;
                } else {
                    val = val.replace("<artist>", "").replace("</artist>", "").trim();
                }
                list.add(new Brass(val));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Our brasses");

            boolean isTrue = true;
            while (isTrue) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("id: " + i + " brass name: " + list.get(i));
                }

                System.out.println("If you want to remove brass - please press: 'R'");
                System.out.println("If you want to modify brass - please press: 'M'");
                System.out.println("If you want to add brass - please press: 'A'");
                System.out.println("If finish working with brasses - please press: 'X'");

                String value = scanner.next().toUpperCase();

                switch (value) {
                    case "R": {
                        System.out.println("choose id of brasses, that you want to remove");
                        int idForRemove = scanner.nextInt();
                        list.remove(idForRemove);
                        System.out.println("brass with id: " + idForRemove + " removed");
                        break;
                    }

                    case "M": {
                        System.out.println("choose id of brasses, that you want to modify");
                        int idForModify = scanner.nextInt();
                        System.out.println("Enter new name of Brass");
                        String name = scanner.next();
                        list.set(idForModify, new Brass(name));
                        System.out.println("brass with id: " + idForModify + " now is: " + name);
                        break;
                    }

                    case "A": {
                        System.out.println("Enter new name of Brass");
                        String name = scanner.next();
                        list.add(new Brass(name));
                        System.out.println("brass with name: " + name + " created");
                        break;
                    }

                    case "X": {
                        isTrue = false;
                        break;
                    }

                    default: {
                        System.out.println("Please enter correct value");
                    }
                }
            }
        }

        try {
            Collections.sort(list);
            System.out.println(list);
        } finally {
            writeToFile(list, file);
        }
    }

    private static void writeToFile(List<Brass> brasses, File file) {
        StringBuilder builder = new StringBuilder();
        builder.append("<note>");

        for (Brass artist : brasses) {
            builder.append("\n");
            builder.append("<artist>").append(artist.getName()).append("</artist>");
        }

        builder.append("\n").append("</note>");

        try (FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile())) {
            // string to byte
            byte[] buffer = builder.toString().getBytes();

            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("The file has been written");
    }

    private File getFile(String path) {

        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("path should not be null or empty");
        }

        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                prefillFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    private void prefillFile(File file) {
        List<Brass> list = new Vector<>();
        list.add(new Brass("Kirkorov"));
        list.add(new Brass("Baskov"));
        list.add(new Brass("Jony"));

        writeToFile(list, file);
    }

}
