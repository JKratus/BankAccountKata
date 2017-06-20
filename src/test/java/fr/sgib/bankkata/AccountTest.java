package fr.sgib.bankkata;

import fr.sgib.bankkata.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void save_deposit_transaction() throws Exception {

        account.deposit(1000);

        verify(transactionRepository).addDeposit(1000);
    }

    @Test
    public void save_withdrawal_transaction() throws Exception {

        account.withdraw(1000);

        verify(transactionRepository).addWithdrawal(1000);
    }

    @Test
    public void print_a_statement() throws Exception {

        List<Transaction> transactions = Arrays.asList(new Transaction(AccountOperation.DEPOSIT, "19/06/2017", 100));

        when(transactionRepository.getAllTransactions()).thenReturn(transactions);

        account.printStatement();

        verify(statementPrinter).print(transactions);
    }
}
