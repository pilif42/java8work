package com.philippe.app.domain;

import lombok.Getter;

@Getter
public class KafkaMetadataDTO {

    public static final long UNSET_LONG = -1L;

    private static final int UNSET_PARTITION = -1;

    private static final String PARTITION_ERROR_MSG = "A partition must be set using method partition.";
    private static final String OFFSET_ERROR_MSG = "A single offset or a range of offset must be set. Not both.";

    private int partition = UNSET_PARTITION;

    private long offset = UNSET_LONG;
    private long minOffset = UNSET_LONG;
    private long maxOffset = UNSET_LONG;

    private KafkaMetadataDTO() {
    }

    public static KafkaMetadataDTO builder() {
        return new KafkaMetadataDTO();
    }

    public KafkaMetadataDTO partition(int partition) {
        this.partition = partition;
        return this;
    }

    public KafkaMetadataDTO minOffset(long minOffset) {
        this.minOffset = minOffset;
        return this;
    }

    public KafkaMetadataDTO maxOffset(long maxOffset) {
        this.maxOffset = maxOffset;
        return this;
    }

    public KafkaMetadataDTO offset(long offset) {
        this.offset = offset;
        return this;
    }

    public KafkaMetadataDTO build() {
        verifyData();
        return this;
    }

    private void verifyData() {
        if (partition == UNSET_PARTITION) {
            throw new IllegalArgumentException(PARTITION_ERROR_MSG);
        }

        if (!((offset != UNSET_LONG && minOffset == UNSET_LONG && maxOffset == UNSET_LONG) ||
                (offset == UNSET_LONG && minOffset != UNSET_LONG && maxOffset != UNSET_LONG))) {
            throw new IllegalArgumentException(OFFSET_ERROR_MSG);
        }
    }
}
