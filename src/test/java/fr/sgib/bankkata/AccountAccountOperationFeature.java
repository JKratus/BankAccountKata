package fr.sgib.bankkata;

import fr.sgib.bankkata.domain.Account;
import fr.sgib.bankkata.domain.StatementPrinter;
import fr.sgib.bankkata.domain.TransactionRepository;
import fr.sgib.bankkata.infrastructure.Clock;
import fr.sgib.bankkata.infrastructure.Console;
import fr.sgib.bankkata.infrastructure.InMemoryTransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountAccountOperationFeature {

    @Mock Console console;
    @Mock Clock clock;

    private Account account;
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() throws Exception {
        transactionRepository = new InMemoryTransactionRepository(clock);
        statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void print_statement_containing_all_operations(){

        when(clock.todayAsString()).thenReturn("01/06/2017", "05/06/2017", "18/06/2017");

        account.deposit(1000);
        account.withdraw(500);
        account.deposit(400);

        account.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("DEPOSIT | 18/06/2017 | 400 | 900");
        inOrder.verify(console).printLine("WITHDRAWAL | 05/06/2017 | -500 | 500");
        inOrder.verify(console).printLine("DEPOSIT | 01/06/2017 | 1000 | 1000");
    }
}
