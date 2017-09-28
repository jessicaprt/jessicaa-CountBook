package ca.prieto.countbook.Controller;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.Model.CounterRepository;

/**
 * Created by Jessica on 2017-09-27.
 */

public class CounterController {
    public void createCounter(String name, int initialValue, String comment) {
        Counter newCounter = new Counter(name,initialValue,comment);
    }
}
