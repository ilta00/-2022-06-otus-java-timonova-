package ru.otus.timonova;

public class Customer implements Comparable<Customer>, Cloneable {
    private final long SCORES_MAX = 233;
    private final int NAME_LENGTH = 4;
    private final long id;
    private String name;
    private long scores;

    //todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {
        if (name.contains("Ivan")) {
            this.id = id;
            this.name = name.substring(0, NAME_LENGTH);
            this.scores = scores > SCORES_MAX ? SCORES_MAX : scores;
        } else {
            this.id = id;
            this.name = name;// name.substring(0, NAME_LENGTH);
            this.scores = scores;// > SCORES_MAX ? SCORES_MAX : scores;
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores > SCORES_MAX ? SCORES_MAX : scores;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (scores != customer.scores) return false;
        return name != null ? name.equals(customer.name) : customer.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (scores ^ (scores >>> 32));
        return result;
    }

    @Override
    public int compareTo(Customer o) {
        return (int) (this.scores - o.scores);
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }

}
