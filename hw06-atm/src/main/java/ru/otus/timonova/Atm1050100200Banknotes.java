package ru.otus.timonova;

import ru.otus.timonova.banknotes.Banknote;

import java.util.LinkedList;
import java.util.Queue;

public class Atm1050100200Banknotes extends AutomatedTellerMachineImpl {

    Queue<Banknote> queue10 = new LinkedList<>();
    Queue<Banknote> queue50 = new LinkedList<>();
    Queue<Banknote> queue100 = new LinkedList<>();
    Queue<Banknote> queue200 = new LinkedList<>();

    public Atm1050100200Banknotes() {
        banknoteMap.put(10, queue10);
        banknoteMap.put(50, queue50);
        banknoteMap.put(100, queue100);
        banknoteMap.put(200, queue200);
    }
}
