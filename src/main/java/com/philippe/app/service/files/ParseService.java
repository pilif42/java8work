package com.philippe.app.service.files;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public interface ParseService extends Closeable {
    Stream<JsonNode> parse(final File file) throws IOException;
}
