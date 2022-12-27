package ru.otus.timonova.banknotes;

public class Banknote {
    private int nominal;

    public int getNominal() {
        return nominal;
    }

    public Banknote(int nominal) {
        this.nominal = nominal;
    }

    @Override
    public String toString() {
        return String.valueOf(nominal);
    }
}
