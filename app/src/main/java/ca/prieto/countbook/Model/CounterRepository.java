package ca.prieto.countbook.Model;

import java.util.ArrayList;
import java.util.List;

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

    public void notifyObserverOfChange() {
        for (ICounterObserver observer : observers) {
            observer.onCounterUpdated();
        }
    }

    public void addCounter(Counter counter) {
        this.counters.add(counter);
        notifyObserverOfChange();
    }

    public class CannotFindCounterException extends RuntimeException {

    }
}
