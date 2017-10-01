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

    public static void incrementCounter(String counterId) {
        Counter counter = CounterRepository.getInstance().getCounterById(counterId);
        int counterValue = counter.getCurrentValue() + 1;
        CounterRepository.getInstance().changeCounter(counter, counterValue);
    }

    public static void decrementCounter(String counterId) {
        Counter counter = CounterRepository.getInstance().getCounterById(counterId);
        if (counter.getCurrentValue() > 0) {
            int counterValue = counter.getCurrentValue() - 1;
            CounterRepository.getInstance().changeCounter(counter, counterValue);
        }
    }

    public static void resetCounter(String counterId) {
        Counter counter = CounterRepository.getInstance().getCounterById(counterId);
        CounterRepository.getInstance().changeCounter(counter, counter.getInitialValue());
    }

    public static void deleteCounter(String counterId) {
        Counter counterToBeDeleted = CounterRepository.getInstance().getCounterById(counterId);
        CounterRepository.getInstance().removeCounter(counterToBeDeleted);
    }
}
