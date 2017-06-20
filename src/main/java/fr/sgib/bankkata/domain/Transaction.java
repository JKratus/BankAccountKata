package fr.sgib.bankkata.domain;

import java.util.Objects;

public class Transaction {

    private AccountOperation operation;
    private final String date;
    private final int amount;

    public Transaction(AccountOperation operation, String date, int amount) {
        this.operation = operation;
        this.date = date;
        this.amount = amount;
    }

    public String operation() {
        return operation.name();
    }

    public String date() {
        return date;
    }

    public Integer amount(){
        return Integer.valueOf(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount &&
                operation == that.operation &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, date, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "operation=" + operation +
                ", date='" + date + '\'' +
                ", amountToDouble=" + amount +
                '}';
    }
}
