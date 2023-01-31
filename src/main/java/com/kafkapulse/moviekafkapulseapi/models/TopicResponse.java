package com.kafkapulse.moviekafkapulseapi.models;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class TopicResponse {
    private String toTopic;
    private long toOffset;
    private int toPartition;
}
