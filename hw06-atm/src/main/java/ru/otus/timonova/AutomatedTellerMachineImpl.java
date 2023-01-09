package ru.otus.timonova;

import ru.otus.timonova.exceptions.CannotGetSumException;
import ru.otus.timonova.exceptions.NotEnoughMoneyException;

import java.util.*;

public class AutomatedTellerMachineImpl implements AutomatedTellerMachine {

    MoneyVault moneyVault = new MoneyVaultImpl();

    public AutomatedTellerMachineImpl(Banknote... args) {
        moneyVault.initMoneyVault(args);
    }

    public void putBanknote(Banknote banknote) {
        moneyVault.putBanknote(banknote);
    }

    public int getBalance() {
        return moneyVault.getBalance();
    }

    public List<Banknote> getSum(int sum) throws NotEnoughMoneyException, CannotGetSumException {
        return moneyVault.getSum(sum);
    }

}
