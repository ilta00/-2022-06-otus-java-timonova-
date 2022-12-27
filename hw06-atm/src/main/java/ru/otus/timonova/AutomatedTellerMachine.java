package ru.otus.timonova;

import ru.otus.timonova.banknotes.Banknote;
import ru.otus.timonova.exceptions.CannotGetSumException;
import ru.otus.timonova.exceptions.NotEnoughMoneyException;

import java.util.List;

public interface AutomatedTellerMachine {

    void putBanknote(Banknote banknote);

    int getBalance();

    List<Banknote> getSum(int sum) throws NotEnoughMoneyException, CannotGetSumException;
}
