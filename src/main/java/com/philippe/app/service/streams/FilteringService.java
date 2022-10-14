package com.philippe.app.service.streams;

import com.philippe.app.domain.Snapshot;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.springframework.data.util.StreamUtils.toUnmodifiableList;

public class FilteringService {
    private static final String SNAPSHOT_NAME_PATTERN_WITHOUT_BATCH_ID = "solr_data_snapshot_%s_";

    public List<String> getSolrSnapshotNamesList(List<Snapshot> snapshots, String supplyType) {
        final String prefix = String.format(SNAPSHOT_NAME_PATTERN_WITHOUT_BATCH_ID, supplyType);
        return snapshots
                .stream()
                .map(Snapshot::getName)
                .filter(name -> StringUtils.startsWith(name, prefix))
                //sort by descending order of batchID
                .sorted(Comparator.comparing((String snapshotName) -> bySnapshotBatchId(snapshotName, prefix)).reversed())
                .collect(toUnmodifiableList());
    }

    private LocalDate bySnapshotBatchId(String snapshotName, String prefix) {
        return LocalDate.parse(StringUtils.substringAfter(snapshotName, prefix));
    }
}
