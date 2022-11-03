package ru.otus.timonova;


import java.util.*;

public class CustomerService {
    private TreeMap<Customer, String> customers = new TreeMap<>();

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry smallestEntry = customers.firstEntry();
        if (smallestEntry == null) return null;
        try {
            return new AbstractMap.SimpleEntry<>(((Customer) smallestEntry.getKey()).clone(),
                    (String) smallestEntry.getValue());
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextEntry = customers.higherEntry(customer);
        if (nextEntry == null) {
            return null;
        }
        try {
            return new AbstractMap.SimpleEntry<>(((Customer) nextEntry.getKey()).clone(),
                    (String) nextEntry.getValue());
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
