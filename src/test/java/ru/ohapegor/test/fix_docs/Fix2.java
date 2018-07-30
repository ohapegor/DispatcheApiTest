package ru.ohapegor.test.fix_docs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Fix2 {

    private String directory = "c:\\Egor\\Work\\med\\med_docks\\sql\\";

    @Test
    void fix() throws IOException {
        Files.walkFileTree(Paths.get(directory), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.err.println("visit : "+file);
                String content = new String(Files.readAllBytes(file));
                if (content.contains("COMMIT WORK;")) {
                    String fixed = content.replaceAll("COMMIT WORK;", "");
                    Files.write(file, fixed.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
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
