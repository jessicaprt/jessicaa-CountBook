package ca.prieto.countbook.Model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ca.prieto.countbook.CountBookApplication;

/**
 * Created by Jessica on 2017-09-27.
 */

public class CounterRepository {
    private List<Counter> counters;
    private List<ICounterObserver> observers;
    private static final String FILENAME = "file.sav";
    private static CounterRepository instance = new CounterRepository();
    public static CounterRepository getInstance() {
        return instance;
    }

    public CounterRepository() {
        this.counters = new ArrayList<Counter>();
        this.observers = new ArrayList<ICounterObserver>();
        loadFromFile();
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

    public void addCounterToList(Counter counter) {
        this.counters.add(counter);
        notifyObserverOfChange();
        saveInFile();
    }

    public void removeCounterFromList(Counter counterToBeDeleted) {
        this.counters.remove(counterToBeDeleted);
        notifyObserverOfChange();
        saveInFile();
    }
    
    public void changeCounterValue(Counter counter, Integer value) {
        counter.setCurrentValue(value);
        counter.setDate();
        notifyObserverOfChange();
        saveInFile();
    }

    public void updateCounterInfo(Counter currentCounter, String counterName, String initialValue, String currentValue, String comment) {
        currentCounter.setName(counterName);
        currentCounter.setInitialValue(Integer.parseInt(initialValue));
        currentCounter.setCurrentValue(Integer.parseInt(currentValue));
        currentCounter.setComment(comment);
        currentCounter.setDate();
        notifyObserverOfChange();
        saveInFile();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = CountBookApplication.getAppContext().openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counters = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            counters = new ArrayList<Counter>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = CountBookApplication.getAppContext().openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public class CannotFindCounterException extends RuntimeException {

    }
}
