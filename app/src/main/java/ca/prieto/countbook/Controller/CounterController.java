package ca.prieto.countbook.Controller;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.Model.CounterRepository;

/**
 * Created by Jessica on 2017-09-27.
 */

public class CounterController {

    private CounterController() {
    }

    public static void addCounter(String name, int initialValue, String comment) {
        Counter newCounter = new Counter(name, initialValue, comment);
        CounterRepository.getInstance().addCounter(newCounter);
    }

    public static String incrementCounter(String counterId, Integer curr) {
        curr++;
        CounterRepository.getInstance().changeCounter(counterId, curr);
        return curr.toString();
    }

    public static String decrementCounter(String counterId, Integer curr) {
        if (curr > 0) curr--;
        CounterRepository.getInstance().changeCounter(counterId,curr);
        return curr.toString();
    }

    public static void resetCounter(String counterId, Integer initialValue) {
        CounterRepository.getInstance().changeCounter(counterId,initialValue);
    }
}
