package ru.ohapegor.test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Test {

    private static String directory = "C:/Egor/Work/med/environment";

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get(directory), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try {
                    if (file.toString().contains("Dockerfile")|| file.toString().contains(".sh")) {
                        String content = new String(Files.readAllBytes(file));
                        if (content.contains("\r")) {
                            int count = 0;
                            for (char c : content.toCharArray()) {
                                if (c == '\r') {
                                    count++;
                                }
                            }
                            System.err.println(String.format("found '%d' '\\r' in '%s'", count, file));
                            String fixed = content.replaceAll("\r", "");
                            Files.write(file, fixed.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("write to " + file + " failed");
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
