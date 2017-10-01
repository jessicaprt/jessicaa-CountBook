package ca.prieto.countbook.Model;

import java.util.ArrayList;
import java.util.List;

import ca.prieto.countbook.View.AddCounterActivity;

/**
 * Created by Jessica on 2017-09-27.
 */

public class CounterRepository {
    private List<Counter> counters;
    private List<ICounterObserver> observers;

    private static CounterRepository instance = new CounterRepository();
    public static CounterRepository getInstance() {

        return instance;
    }

    public CounterRepository() {
        this.counters = new ArrayList<Counter>();
        this.observers = new ArrayList<ICounterObserver>();
    }

    public List<Counter> getCounterList() {

        return this.counters;
    }

    public Counter getCounterById(String id) {
        for (Counter counter:counters) {
            if ( id.equals(counter.getId()) ) {
                return counter;
            }
        }
        throw new CannotFindCounterException();
    }

    public void addObserver(ICounterObserver observer) {
        this.observers.add(observer);
        observer.onCounterUpdated();
    }

    public void removeObserver(ICounterObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObserverOfChange() {
        if (observers != null) {
            for (ICounterObserver observer : observers) {
                observer.onCounterUpdated();
            }
        }
    }

    public void addCounter(Counter counter) {
        this.counters.add(counter);
        notifyObserverOfChange();
    }
    
    public void changeCounter(Counter counter, Integer value) {
        counter.setCurrentValue(value);
        notifyObserverOfChange();
    }

    public void changeCounter2 (Counter counter) {
        int currentValue = counter.getCurrentValue() - 1;
        counter.setCurrentValue(currentValue);
        notifyObserverOfChange();
    }

    public void removeCounter(Counter counterToBeDeleted) {
        this.counters.remove(counterToBeDeleted);
        notifyObserverOfChange();
    }

    public void updateCounter(Counter currentCounter, Counter updatedCounter) {
        currentCounter.setName(updatedCounter.getName());
        currentCounter.setInitialValue(updatedCounter.getInitialValue());
        currentCounter.setComment(updatedCounter.getComment());
        notifyObserverOfChange();
    }

    public class CannotFindCounterException extends RuntimeException {

    }
}
