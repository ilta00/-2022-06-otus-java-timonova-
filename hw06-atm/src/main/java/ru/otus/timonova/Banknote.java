package ru.otus.timonova;

public enum Banknote {
    TEN(10),
    FIFTY(50),
    ONEHUHDRED(100),
    TWOHUNDRED(200);

    private int nominal;

    Banknote(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }

    @Override
    public String toString() {
        return String.valueOf(nominal);
    }
}
