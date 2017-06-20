package fr.sgib.bankkata.domain;

import java.util.List;

public interface TransactionRepository {

    void addDeposit(int amount);

    void addWithdrawal(int amount);

    List<Transaction> getAllTransactions();
}
