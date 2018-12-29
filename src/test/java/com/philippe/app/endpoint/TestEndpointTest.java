package com.philippe.app.endpoint;

import com.philippe.app.domain.User;
import com.philippe.app.service.kafka.Publisher;
import com.philippe.app.service.mapper.BeanMapper;
import com.philippe.app.util.CustomObjectMapper;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static com.philippe.app.util.MvcHelper.postJson;
import static com.philippe.app.util.MockMvcControllerAdviceHelper.mockAdviceFor;
import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestEndpointTest {

    @InjectMocks
    private TestEndpoint testEndpoint;

    @Mock
    private Publisher publisher;

    @Spy
    private MapperFacade mapperFacade = new BeanMapper();

    private MockMvc mockMvc;

    private static final UUID USER_ID = UUID.fromString("7bc5d41b-0549-40b3-ba76-42f6d4cf3fd9");
    private static final String USER_INVALID_JSON =
            "{\"description\":\"a\",\"category\":\"BAD_CAT\",\"createdBy\":\"u\"}";
    private static final String USER_VALID_JSON =
            "{\"name\":\"lionel\",\"favouriteColor\":\"blaugrana\",\"favouriteNumber\":10}";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(testEndpoint)
                .setHandlerExceptionResolvers(mockAdviceFor(RestExceptionHandler.class))
                .setMessageConverters(new MappingJackson2HttpMessageConverter(new CustomObjectMapper()))
                .build();
    }

    @Test
    public void createUser_happyPath() throws Exception {
        // Given
        when(publisher.send(any(User.class))).thenReturn(true);

        // When
        ResultActions actions = mockMvc.perform(postJson(String.format("/tester/%s/users", USER_ID),
                USER_VALID_JSON));

        // Then
        actions.andExpect(status().isCreated());
        actions.andExpect(handler().handlerType(TestEndpoint.class));
        actions.andExpect(handler().methodName("createUser"));
        actions.andExpect(jsonPath("$.*", hasSize(2)));
// TODO       actions.andExpect(jsonPath("$.id", is(USER_ID.toString())));
        actions.andExpect(jsonPath("$.created", is(true)));
    }

    // TODO
//    @Test
//    public void createUserBadJson() throws Exception {
//        ResultActions actions = mockMvc.perform(postJson(String.format("/tester/%s/users", USER_ID),
//                USER_INVALID_JSON));
//
//        actions.andExpect(status().isBadRequest());
//        actions.andExpect(handler().handlerType(TestEndpoint.class));
//        actions.andExpect(handler().methodName("createUser"));
//        actions.andExpect(jsonPath("$.error.code", is(CustomException.Fault.VALIDATION_FAILED.name())));
//        actions.andExpect(jsonPath("$.error.message", isA(String.class)));
//        actions.andExpect(jsonPath("$.error.timestamp", isA(String.class)));
//    }
}
