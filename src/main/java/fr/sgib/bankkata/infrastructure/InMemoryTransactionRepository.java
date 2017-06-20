package fr.sgib.bankkata.infrastructure;

import fr.sgib.bankkata.domain.AccountOperation;
import fr.sgib.bankkata.domain.Transaction;
import fr.sgib.bankkata.domain.TransactionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

    private Clock clock;
    private List<Transaction> transactions = new ArrayList<>();

    public InMemoryTransactionRepository(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void addDeposit(int amount) {
        Transaction depositTransaction = new Transaction(AccountOperation.DEPOSIT, clock.todayAsString(), amount);
        transactions.add(depositTransaction);
    }

    @Override
    public void addWithdrawal(int amount) {
        Transaction withdrawalTransaction = new Transaction(AccountOperation.WITHDRAWAL, clock.todayAsString(), -amount);
        transactions.add(withdrawalTransaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
