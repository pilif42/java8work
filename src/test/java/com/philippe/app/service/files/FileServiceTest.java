package com.philippe.app.service.files;

import com.philippe.app.service.files.impl.FileServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
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
    @Test @Ignore("Fails when running on Ubuntu. TODO.")
    public void deleteTxtFiles() throws IOException {
        fileService.deleteTxtFiles();
    }
}
