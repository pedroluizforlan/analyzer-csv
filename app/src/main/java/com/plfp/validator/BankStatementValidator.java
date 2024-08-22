package com.plfp.validator;



import com.plfp.validator.notification.Notification;

import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.time.LocalDate;

public class BankStatementValidator {
    private String id;
    private String amount;
    private String description;
    private String bank;
    private String date;

    public BankStatementValidator(String id, String amount, String description, String bank, String date) {
        this.id = Objects.requireNonNull(id);
        this.amount = Objects.requireNonNull(amount);
        this.description = Objects.requireNonNull(description);
        this.bank = Objects.requireNonNull(bank);
        this.date = Objects.requireNonNull(date);
    }

    public Notification validate() {
        Notification notification = new Notification();
        if (Long.parseLong(this.id) < 0) {
            notification.addError("Number of id is minor then 0");
        }
        if (this.description.length() > 100) {
            notification.addError("The description is too long");
        }
        final LocalDate parsedDate;

        try {
            parsedDate = LocalDate.parse(this.date);

            if (parsedDate.isAfter(LocalDate.now())){
                notification.addError("The date cannot be in future");
            }

        } catch (DateTimeParseException dateTimeParseException) {
            notification.addError("Inválid format for date");
        }


        try{
            Double.parseDouble(this.amount);
        } catch (NumberFormatException numberFormatException){
            notification.addError("Invalid format for amount");
        }

        return notification;
    }
}
