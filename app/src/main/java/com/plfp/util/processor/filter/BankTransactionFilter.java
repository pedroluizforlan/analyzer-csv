package com.plfp.util.processor.filter;

import com.plfp.model.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
