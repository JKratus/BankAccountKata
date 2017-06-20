package fr.sgib.bankkata;

import fr.sgib.bankkata.domain.AccountOperation;
import fr.sgib.bankkata.domain.Transaction;
import fr.sgib.bankkata.domain.TransactionRepository;
import fr.sgib.bankkata.infrastructure.Clock;
import fr.sgib.bankkata.infrastructure.InMemoryTransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryTest {

    @Mock
    Clock clock;

    public static final String TODAY = "19/06/2017";

    private TransactionRepository transactionRepository;

    @Before
    public void setUp() throws Exception {
        transactionRepository = new InMemoryTransactionRepository(clock);
    }

    @Test
    public void create_and_store_a_deposit_transaction() throws Exception {

        when(clock.todayAsString()).thenReturn(TODAY);

        transactionRepository.addDeposit(100);

        List<Transaction> transactions = transactionRepository.getAllTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(new Transaction(AccountOperation.DEPOSIT, TODAY, 100));
    }

    @Test
    public void create_and_store_a_withdrawal_transaction() throws Exception {

        when(clock.todayAsString()).thenReturn(TODAY);

        transactionRepository.addWithdrawal(100);

        List<Transaction> transactions = transactionRepository.getAllTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(new Transaction(AccountOperation.WITHDRAWAL, TODAY, -100));
    }

}
