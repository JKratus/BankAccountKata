package fr.sgib.bankkata;

import fr.sgib.bankkata.domain.Account;
import fr.sgib.bankkata.domain.StatementPrinter;
import fr.sgib.bankkata.domain.TransactionRepository;
import fr.sgib.bankkata.infrastructure.Clock;
import fr.sgib.bankkata.infrastructure.Console;
import fr.sgib.bankkata.infrastructure.InMemoryTransactionRepository;

public class BankKataApplication {

    public static void main(String[] args) {

        Clock clock = new Clock();
        Console console = new Console();

        TransactionRepository transactionRepository = new InMemoryTransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter(console);
        Account account = new Account(transactionRepository, statementPrinter);

        account.deposit(1000);
        account.withdraw(500);
        account.deposit(400);

        account.printStatement();
    }
}
