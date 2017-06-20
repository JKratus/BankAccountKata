package fr.sgib.bankkata.domain;

import fr.sgib.bankkata.infrastructure.Console;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {

    private static final String HEADER = "OPERATION | DATE | AMOUNT | BALANCE";

    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(HEADER);

        AtomicInteger runningBalance = new AtomicInteger(0);

        transactions.stream()
                .map(transaction -> statementFormatter(transaction, runningBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }

    private String statementFormatter(Transaction transaction, AtomicInteger runningBalance) {

        StringBuilder statements = new StringBuilder();
        statements.append(transaction.operation()).append(" | ")
                .append(transaction.date()).append(" | ")
                .append(transaction.amount()).append(" | ")
                .append(runningBalance.addAndGet(transaction.amount()));

        return statements.toString();
    }
}
