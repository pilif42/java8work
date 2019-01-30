package com.philippe.app.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.philippe.app.domain.KafkaMetadataDTO;
import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class KafkaMetadataDTOSerializerTest {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(KafkaMetadataDTO.class, new KafkaMetadataDTOSerializer())
            .create();

    private static final int PARTITION = 1;

    private static final long OFFSET = 13;
    private static final long MIN_OFFSET = 13L;
    private static final long MAX_OFFSET = 130L;

    private static final String METADATA_KAFKA_PARTITIONS_SINGLE_EVENT =
            format("{\"partition\":%d,\"offset\":%d}", PARTITION, OFFSET);
    private static final String METADATA_KAFKA_PARTITIONS_RANGE_OF_EVENTS =
            format("{\"partition\":%d,\"minOffset\":%d,\"maxOffset\":%d}", PARTITION, MIN_OFFSET, MAX_OFFSET);

    @Test
    public void singleEvent() {
        final KafkaMetadataDTO kafkaMetadataDTO = KafkaMetadataDTO.builder()
                .partition(PARTITION)
                .offset(OFFSET)
                .build();
        assertEquals(METADATA_KAFKA_PARTITIONS_SINGLE_EVENT, GSON.toJson(kafkaMetadataDTO));
    }

    @Test
    public void rangeOfEvents() {
        final KafkaMetadataDTO kafkaMetadataDTO = KafkaMetadataDTO.builder()
                .partition(PARTITION)
                .minOffset(MIN_OFFSET)
                .maxOffset(MAX_OFFSET)
                .build();
        assertEquals(METADATA_KAFKA_PARTITIONS_RANGE_OF_EVENTS, GSON.toJson(kafkaMetadataDTO));
    }
}
