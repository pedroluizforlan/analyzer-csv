package com.plfp.util.parser;


import com.plfp.model.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
/*
Esta classe tem o objetivo de transformar os dados do arquivo CSV em objetos do tipo BankTransaction
*/
public class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public BankTransaction parseFrom(final String line){
        final String[] columns = line.split(",");

        Long id = Long.valueOf(columns[0]);
        Double amount = Double.valueOf(columns[1]);
        String desc = columns[2];
        String bank = columns[3];
        LocalDate dateTime = LocalDate.parse(columns[4], DATE_PATTERN);

        return new BankTransaction(id,amount,desc,bank,dateTime);
    }

    public List<BankTransaction> parseLinesFrom(final List<String> lines){
        return lines.stream()
                .map(this::parseFrom)
                .collect(Collectors.toList());
    }
}
