package com.plfp.util.processor.summarizer;

import com.plfp.model.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulate, BankTransaction bankTransaction);
}
