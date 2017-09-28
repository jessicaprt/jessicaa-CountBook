package ca.prieto.countbook.Model;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jessica on 2017-09-27.
 */

public class CounterRepository {
    private List<Counter> counters;
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

    public class CannotFindCounterException extends RuntimeException {

    }
}
