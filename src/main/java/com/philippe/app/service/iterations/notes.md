To iterate through a queue and remove items (inside a Storm bolt: execute method):

This works:

final Queue<Put> failedMutatesQueue = hBaseWriteManager.getFailedMutatesQueue();
            failedMutatesQueue.forEach(put -> {
                FailureDTO failureDTO = eventHBaseTableMapper.transform(put);
                if (failureDTO != null) {
                    collector.emit(AGGREGATOR_BOLT_STREAM_ID_FROM_HBASE, tuple, new Values(failureDTO.getWindowId(),
                            failureDTO.getShardId(), null, failureDTO.getEventGuid()));
                }
                failedMutatesQueue.remove(put);
            });


This is KO:

final Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            Put put = queue.poll();
            if (put != null) {
                FailureDTO failureDTO = eventHBaseTableMapper.transform(put);
                if (failureDTO != null) {
                    collector.emit(AGGREGATOR_BOLT_STREAM_ID_FROM_HBASE, tuple, new Values(failureDTO.getWindowId(),
                            failureDTO.getShardId(), null, failureDTO.getEventGuid()));

                }
            }
        }
