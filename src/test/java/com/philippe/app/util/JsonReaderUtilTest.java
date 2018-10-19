package com.philippe.app.util;

import org.junit.Test;

import static com.philippe.app.util.JsonReaderUtil.beautifyJsonString;

public class JsonReaderUtilTest {
    @Test
    public void testBeautifyJsonString() throws Exception {
        beautifyJsonString("{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}");
    }
}
