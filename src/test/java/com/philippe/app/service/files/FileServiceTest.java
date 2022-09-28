package com.philippe.app.service.files;

import com.philippe.app.service.files.impl.FileServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    @InjectMocks
    private FileServiceImpl fileService;

    /**
     * Step 1: create a file ending in .txt in /dataToRead
     * Step 2: run the test
     * Step 3: verify files ending in .txt have been deleted
     *
     * @throws IOException
     */
    @Test @Disabled("Fails when running on Ubuntu. TODO.")
    public void deleteTxtFiles() throws IOException {
        fileService.deleteTxtFiles();
    }
}
