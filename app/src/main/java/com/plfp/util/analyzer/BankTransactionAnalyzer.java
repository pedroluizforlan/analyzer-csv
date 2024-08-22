package com.plfp.util.analyzer;


import com.plfp.model.BankTransaction;
import com.plfp.util.parser.BankStatementCSVParser;
import com.plfp.util.parser.BankStatementParser;
import com.plfp.util.processor.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final String RESOURCES = "app/src/main/resources/";

    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        List<BankTransaction> transacoesBancarias = bankStatementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(transacoesBancarias);

        collectSummary(bankStatementProcessor);
    }

    public void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("Total: R$" +
                bankStatementProcessor.calculateTotalAmount());
        System.out.println("In August: R$" +
                bankStatementProcessor.calculateTotalInMonth(Month.AUGUST));
        System.out.println("By description: R$" +
                bankStatementProcessor.calculateTotalForCategory("Pix"));
    }
}
