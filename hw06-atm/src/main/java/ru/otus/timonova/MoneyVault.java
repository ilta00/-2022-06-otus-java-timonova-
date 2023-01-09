package ru.otus.timonova;

import ru.otus.timonova.exceptions.CannotGetSumException;
import ru.otus.timonova.exceptions.NotEnoughMoneyException;

import java.util.List;

public interface MoneyVault {

    void initMoneyVault(Banknote... args);

    void putBanknote(Banknote banknote);

    int getBalance();

    List<Banknote> getSum(int sum) throws NotEnoughMoneyException, CannotGetSumException;
}
