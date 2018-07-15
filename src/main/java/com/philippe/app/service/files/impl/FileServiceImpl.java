package com.philippe.app.service.files.impl;

import com.philippe.app.service.files.FileService;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.function.Consumer;

public class FileServiceImpl implements FileService {

    private static final String DATA_TO_READ = "dataToRead";

    @Override
    public void deleteTxtFiles() throws IOException {
        Path dataToReadPath = FileSystems.getDefault().getPath(DATA_TO_READ);

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s/*.txt", DATA_TO_READ));
        Consumer<Path> pathConsumer = path -> {
            try {
                if (pathMatcher.matches(path)) {
                    Files.deleteIfExists(path);
                }
            } catch (IOException e) {
                System.err.println("Exception occurred while deleting file: " + e.getMessage());
            }
        };
        Files.walk(dataToReadPath).forEach(pathConsumer);
    }
}
