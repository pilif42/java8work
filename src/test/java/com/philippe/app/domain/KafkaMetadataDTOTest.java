package com.philippe.app.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KafkaMetadataDTOTest {

    private final static int PARTITION = 0;

    private final static long UNSET_LONG = -1L;
    private final static long MIN_OFFSET = 10L;
    private final static long MAX_OFFSET = 100L;

    private final static String PARTITION_ERROR_MSG = "A partition must be set using method partition.";
    private final static String OFFSET_ERROR_MSG = "A single offset or a range of offset must be set. Not both.";

    @Test
    public void testHappyPathWithSingleOffset() {
        final KafkaMetadataDTO kafkaMetadataDTO = KafkaMetadataDTO.builder()
                .partition(PARTITION)
                .offset(MIN_OFFSET)
                .build();
        assertEquals(PARTITION, kafkaMetadataDTO.getPartition());
        assertEquals(MIN_OFFSET, kafkaMetadataDTO.getOffset());
        assertEquals(UNSET_LONG, kafkaMetadataDTO.getMinOffset());
        assertEquals(UNSET_LONG, kafkaMetadataDTO.getMaxOffset());
    }

    @Test
    public void testHappyPathWithOffsetRange() {
        final KafkaMetadataDTO kafkaMetadataDTO = KafkaMetadataDTO.builder()
                .partition(PARTITION)
                .minOffset(MIN_OFFSET)
                .maxOffset(MAX_OFFSET)
                .build();
        assertEquals(PARTITION, kafkaMetadataDTO.getPartition());
        assertEquals(UNSET_LONG, kafkaMetadataDTO.getOffset());
        assertEquals(MIN_OFFSET, kafkaMetadataDTO.getMinOffset());
        assertEquals(MAX_OFFSET, kafkaMetadataDTO.getMaxOffset());
    }

    @Test
    public void testErrorPath_noPartitionSet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> KafkaMetadataDTO.builder().offset(MIN_OFFSET).build());
        assertEquals(PARTITION_ERROR_MSG, exception.getMessage());
    }

    @Test
    public void testErrorPath_noOffsetSet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> KafkaMetadataDTO.builder().partition(PARTITION).build());
        assertEquals(OFFSET_ERROR_MSG, exception.getMessage());
    }

    @Test
    public void testErrorPathWithSingleOffset_minOffsetAlsoSet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> KafkaMetadataDTO.builder().partition(PARTITION).offset(MIN_OFFSET).minOffset(MIN_OFFSET).build());
        assertEquals(OFFSET_ERROR_MSG, exception.getMessage());
    }

    @Test
    public void testErrorPathWithSingleOffset_maxOffsetAlsoSet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> KafkaMetadataDTO.builder().partition(PARTITION).offset(MIN_OFFSET).maxOffset(MAX_OFFSET).build());
        assertEquals(OFFSET_ERROR_MSG, exception.getMessage());
    }

    @Test
    public void testErrorPathWithOffsetRange_singleOffsetAlsoSet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> KafkaMetadataDTO.builder().partition(PARTITION).offset(MIN_OFFSET).minOffset(MIN_OFFSET).maxOffset(MAX_OFFSET).build());
        assertEquals(OFFSET_ERROR_MSG, exception.getMessage());
    }
}
