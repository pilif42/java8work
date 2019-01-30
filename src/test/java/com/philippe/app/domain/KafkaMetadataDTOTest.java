package com.philippe.app.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class KafkaMetadataDTOTest {

    private final static int PARTITION = 0;

    private final static long UNSET_LONG = -1L;
    private final static long MIN_OFFSET = 10L;
    private final static long MAX_OFFSET = 100L;

    private final static String PARTITION_ERROR_MSG = "A partition must be set using method partition.";
    private final static String OFFSET_ERROR_MSG = "A single offset or a range of offset must be set. Not both.";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(PARTITION_ERROR_MSG);
        KafkaMetadataDTO.builder().offset(MIN_OFFSET).build();
    }

    @Test
    public void testErrorPath_noOffsetSet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(OFFSET_ERROR_MSG);
        KafkaMetadataDTO.builder().partition(PARTITION).build();
    }

    @Test
    public void testErrorPathWithSingleOffset_minOffsetAlsoSet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(OFFSET_ERROR_MSG);
        KafkaMetadataDTO.builder().partition(PARTITION).offset(MIN_OFFSET).minOffset(MIN_OFFSET).build();
    }

    @Test
    public void testErrorPathWithSingleOffset_maxOffsetAlsoSet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(OFFSET_ERROR_MSG);
        KafkaMetadataDTO.builder().partition(PARTITION).offset(MIN_OFFSET).maxOffset(MAX_OFFSET).build();
    }

    @Test
    public void testErrorPathWithOffsetRange_singleOffsetAlsoSet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(OFFSET_ERROR_MSG);
        KafkaMetadataDTO.builder().partition(PARTITION).offset(MIN_OFFSET).minOffset(MIN_OFFSET).maxOffset(MAX_OFFSET).build();
    }
}
