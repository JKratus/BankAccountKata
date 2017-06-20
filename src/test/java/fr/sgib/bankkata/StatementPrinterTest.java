package fr.sgib.bankkata;

import fr.sgib.bankkata.domain.StatementPrinter;
import fr.sgib.bankkata.domain.Transaction;
import fr.sgib.bankkata.infrastructure.Console;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static fr.sgib.bankkata.domain.AccountOperation.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterTest {

    @Mock
    Console console;

    private StatementPrinter statementPrinter;
    private List<Transaction> NO_TRANSACTIONS = Lists.emptyList();

    @Before
    public void setUp() throws Exception {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void always_print_the_header() throws Exception {

        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
    }

    @Test
    public void print_one_transaction_after_header() throws Exception {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(DEPOSIT, "18/06/2017", 400)
        );

        statementPrinter.print(transactions);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("DEPOSIT | 18/06/2017 | 400 | 400");
    }

    @Test
    public void print_all_transactions_in_reverse_order() throws Exception {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(DEPOSIT, "01/06/2017", 1000),
                new Transaction(WITHDRAWAL, "05/06/2017", -500),
                new Transaction(DEPOSIT, "18/06/2017", 400)
        );

        statementPrinter.print(transactions);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("DEPOSIT | 18/06/2017 | 400 | 900");
        inOrder.verify(console).printLine("WITHDRAWAL | 05/06/2017 | -500 | 500");
        inOrder.verify(console).printLine("DEPOSIT | 01/06/2017 | 1000 | 1000");
    }
}
