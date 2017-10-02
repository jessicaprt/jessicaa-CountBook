package ca.prieto.countbook.Controller;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.Model.CounterRepository;

/**
 * Created by Jessica on 2017-09-27.
 */

public class CounterController {

    private CounterController() {
    }

    public static Counter getCounter(String counterId) {
        return CounterRepository.getInstance().getCounterById(counterId);
    }

    public static void addCounter(String name, int initialValue, String comment) {
        Counter newCounter = new Counter(name, initialValue, comment);
        CounterRepository.getInstance().addCounterToList(newCounter);
    }

    public static void incrementCounter(String counterId) {
        Counter counter = CounterRepository.getInstance().getCounterById(counterId);
        int counterValue = counter.getCurrentValue() + 1;
        CounterRepository.getInstance().changeCounterValue(counter, counterValue);
    }

    public static void decrementCounter(String counterId) {
        Counter counter = CounterRepository.getInstance().getCounterById(counterId);
        if (counter.getCurrentValue() > 0) {
            int counterValue = counter.getCurrentValue() - 1;
            CounterRepository.getInstance().changeCounterValue(counter, counterValue);
        }
    }

    public static void resetCounter(String counterId) {
        Counter counter = CounterRepository.getInstance().getCounterById(counterId);
        CounterRepository.getInstance().changeCounterValue(counter, counter.getInitialValue());
    }

    public static void deleteCounter(String counterId) {
        Counter counterToBeDeleted = CounterRepository.getInstance().getCounterById(counterId);
        CounterRepository.getInstance().removeCounterFromList(counterToBeDeleted);
    }

    public static void updateCounter(String counterId, String counterName, String initialValue, String currentValue, String comment) {
        Counter currentCounter = CounterRepository.getInstance().getCounterById(counterId);
        CounterRepository.getInstance().updateCounterInfo(currentCounter, counterName, initialValue, currentValue, comment);
    }
}
