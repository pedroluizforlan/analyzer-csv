package com.plfp.util.exporter;

import com.plfp.model.SummaryStatistics;

@FunctionalInterface
public interface Exporter {
    String export(final SummaryStatistics summaryStatistics);
}
