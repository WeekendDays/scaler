package org.aliyun.serverless.scaler;

import java.util.ArrayDeque;
import java.util.Deque;

public class RequestCounter {
    private static final long ONE_SECOND_IN_MS = 1000;

    private final Deque<Long> requestTimestamps;

    public RequestCounter() {
        this.requestTimestamps = new ArrayDeque<>();
    }

    public synchronized int calculateRequestsInLastSecond(long timestamp) {
        //移除队列中一分钟以外的时间戳
        while (!requestTimestamps.isEmpty() && timestamp - requestTimestamps.peekFirst() >= ONE_SECOND_IN_MS) {
            requestTimestamps.pollFirst();
        }
        requestTimestamps.addLast(timestamp);
        return requestTimestamps.size();
    }
}
