package ru.otus.timonova;

import ru.otus.timonova.banknotes.*;
import ru.otus.timonova.exceptions.CannotGetSumException;
import ru.otus.timonova.exceptions.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String... args) {
        AutomatedTellerMachine atm = new Atm1050100200Banknotes();

        //Принимает банкноты разных номиналов
        atm.putBanknote(new Banknote10());
        atm.putBanknote(new Banknote10());
        atm.putBanknote(new Banknote50());
        atm.putBanknote(new Banknote100());
        atm.putBanknote(new Banknote100());
        atm.putBanknote(new Banknote100());
        atm.putBanknote(new Banknote100());
        atm.putBanknote(new Banknote200());
        atm.putBanknote(new Banknote200());

        //Выдает сумму остатка денежных средств
        System.out.println("Баланс: " + atm.getBalance());

        //Выдает запрошенную сумму минимальным количеством банкнот
        int testSum = 460;
        List<Banknote> banknoteList;
        System.out.println("Сумма: " + testSum);
        try {
            banknoteList = atm.getSum(testSum);
            System.out.println(Arrays.toString(banknoteList.toArray()));
            System.out.println("Баланс после выдачи средств: " + atm.getBalance());
        } catch (CannotGetSumException ex) {
            System.out.println("Невозможно выдать сумму имеющимися купюрами!");
        } catch (NotEnoughMoneyException ex) {
            System.out.println("Недостаточно средств!");
        }
    }
}
