package com.philippe.app.service.mapper;

import com.philippe.app.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomMapperServiceImplTest {

    private static final Integer A_NUMBER = 13;

    private static final String USER_NAME = "John";
    private static final String A_COLOUR = "green";

    private static final UUID A_UUID = UUID.randomUUID();

    @InjectMocks
    private CustomMapperServiceImpl customMapperService;

    @Test
    public void testConvertUser() {
        final User inputUser = new User();
        inputUser.setId(A_UUID);
        inputUser.setName(USER_NAME);
        inputUser.setFavoriteColor(A_COLOUR);
        inputUser.setFavoriteNumber(A_NUMBER);

        final example.avro.User avroContainer = customMapperService.convert(inputUser);

        assertEquals(A_UUID.toString(), avroContainer.getId());
        assertEquals(USER_NAME, avroContainer.getName());
        assertEquals(A_COLOUR, avroContainer.getFavoriteColor());
        assertEquals(A_NUMBER, avroContainer.getFavoriteNumber());
    }
}
