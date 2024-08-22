package com.plfp.util.processor;

import com.plfp.model.BankTransaction;
import com.plfp.util.processor.filter.BankTransactionFilter;
import com.plfp.util.processor.summarizer.BankTransactionSummarizer;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double calculateTotalInMonth(Month month) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
                .mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalForCategory(String category){
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
                .mapToDouble(BankTransaction::getAmount).sum();
    }

//    public List<BankTransaction> findTransactionsGreaterThanEqual(double amount){
//        return bankTransactions.stream()
//                .filter(bankTransaction -> bankTransaction.getAmount() >= amount)
//                .collect(Collectors.toList());
//    }
//    public List<BankTransaction> findTransactionsInMonth(Month month){
//        return bankTransactions.stream()
//                .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
//                .collect(Collectors.toList());
//    } FOI SUBISTITUIDO PELO MÉTODO ABAIXO

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter){
        return bankTransactions.stream()
                .filter(bankTransactionFilter::test)
                .collect(Collectors.toList());
    }

    public double summarizeTransactions(BankTransactionSummarizer bankTransactionSummarizer){
        double result = 0;
        for(BankTransaction bankTransaction : bankTransactions){
            result = bankTransactionSummarizer.summarize(result,bankTransaction);
        }
        return result;
    }
}
