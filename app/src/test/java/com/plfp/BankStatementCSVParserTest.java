package com.plfp;

import java.time.LocalDate;
import com.plfp.model.BankTransaction;
import com.plfp.util.parser.BankStatementCSVParser;
import com.plfp.util.parser.BankStatementParser;
import org.junit.Assert;
import org.junit.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception{
        final String line = "31,5460.00,Alianca,NuBank,21-08-2025";
        BankTransaction result = statementParser.parseFrom(line);
        BankTransaction expected = new BankTransaction(31L,5460.00,"Alianca","NuBank",LocalDate.of(2025, Month.AUGUST,21));
        double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(),result.getDate());
        Assert.assertEquals(expected.getId(),result.getId());
        Assert.assertEquals(expected.getAmount(),result.getAmount(), tolerance);
        Assert.assertEquals(expected.getBank(),result.getBank());
        Assert.assertEquals(expected.getDescription(),result.getDescription());
    }

    @Test
    public void shouldParseLines() throws Exception{
        double tolerance = 0.0d;
        List<String> lines = new ArrayList<>();
        lines.add("31,5460.00,Alianca,NuBank,21-08-2025");
        lines.add("32,30000.00,Casamento,NuBank,28-09-2025");
        lines.add("33,300000.00,Carro,Caixa,30-09-2025");

        List<BankTransaction> listOfBankTransactions = statementParser.parseLinesFrom(lines);

        List<BankTransaction> expected = new ArrayList<>();
        expected.add(new BankTransaction(31L,5460.00,"Alianca","NuBank",LocalDate.of(2025, Month.AUGUST,21)));
        expected.add(new BankTransaction(32L,30000.00,"Casamento","NuBank",LocalDate.of(2025, Month.SEPTEMBER,28)));
        expected.add(new BankTransaction(33L,300000.00,"Carro","NuBank",LocalDate.of(2025, Month.SEPTEMBER,30)));

        Assert.assertEquals(expected.get(0).getId(),listOfBankTransactions.get(0).getId());
        Assert.assertEquals(expected.get(0).getBank(),listOfBankTransactions.get(0).getBank());
        Assert.assertEquals(expected.get(0).getAmount(),listOfBankTransactions.get(0).getAmount(),tolerance);
        Assert.assertEquals(expected.get(0).getDescription(),listOfBankTransactions.get(0).getDescription());
        Assert.assertEquals(expected.get(0).getDate(),listOfBankTransactions.get(0).getDate());
        Assert.assertEquals(expected.size(),listOfBankTransactions.size());

    }
}
