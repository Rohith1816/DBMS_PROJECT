package com.example.HM.models;

public class Vacancy {
    private String Name;
    private int Capacity;
    private int remainingVacancy;

    public Vacancy() {
    }

    public Vacancy(String name, int capacity, int remainingVacancy) {
        this.Name = name;
        this.Capacity = capacity;
        this.remainingVacancy = remainingVacancy;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public int getRemainingVacancy() {
        return remainingVacancy;
    }

    public void setRemainingVacancy(int vacancy) {
        remainingVacancy = vacancy;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "Name='" + Name + '\'' +
                ", Capacity=" + Capacity +
                ", remainingVacancy=" + remainingVacancy +
                '}';
    }
}
