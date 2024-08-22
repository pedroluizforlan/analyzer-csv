package com.plfp.util.processor;

import com.plfp.model.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessorTest {

    @Test
    public void shouldCalculateTotalAmount(){
        List<BankTransaction> bankTransactions = new ArrayList<>();
        bankTransactions.add(new BankTransaction(31L,5460.00,"Alianca","NuBank", LocalDate.of(2025, Month.AUGUST,21)));
        bankTransactions.add(new BankTransaction(32L,30000.00,"Casamento","NuBank",LocalDate.of(2025, Month.SEPTEMBER,28)));
        bankTransactions.add(new BankTransaction(33L,300000.00,"Carro","NuBank",LocalDate.of(2025, Month.SEPTEMBER,30)));

        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        Assert.assertEquals(335460.00,bankStatementProcessor.calculateTotalAmount(),0.0d);
    }

    @Test
    public void shouldFindTransactionsGreaterThanEqual(){
        List<BankTransaction> bankTransactions = new ArrayList<>();
        bankTransactions.add(new BankTransaction(31L,5460.00,"Alianca","NuBank", LocalDate.of(2025, Month.AUGUST,21)));
        bankTransactions.add(new BankTransaction(32L,30000.00,"Casamento","NuBank",LocalDate.of(2025, Month.SEPTEMBER,28)));
        bankTransactions.add(new BankTransaction(33L,300000.00,"Carro","NuBank",LocalDate.of(2025, Month.SEPTEMBER,30)));

        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        Assert.assertEquals(1,bankStatementProcessor.findTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == Month.AUGUST &&
                bankTransaction.getAmount() >= 1000.00).size());
    }
}
