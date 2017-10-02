package ca.prieto.countbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class CounterEditActivity extends AppCompatActivity {
    CounterRepository instance;
    String counterId;

    private EditText counterName;
    private EditText initialValue;
    private EditText comment;
    private EditText currentValue;

    private TextInputLayout counterNameWrapper;
    private TextInputLayout initialValueWrapper;
    private TextInputLayout currentValueWrapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details_edit);

        instance = CounterRepository.getInstance();
        Intent intent = getIntent();
        counterId = intent.getStringExtra(CounterActivity.CounterDetailsMessage);

        counterNameWrapper = (TextInputLayout) findViewById(R.id.editCounterNameWrapper);
        initialValueWrapper = (TextInputLayout) findViewById(R.id.editInitialValueWrapper);
        currentValueWrapper = (TextInputLayout) findViewById(R.id.editCurrentValueWrapper);

        renderView();
    }

    public void renderView() {
        counterName = (EditText) findViewById(R.id.editCounterName);
        counterName.setText(CounterRepository.getInstance().getCounterById(counterId).getName());

        initialValue = (EditText) findViewById(R.id.editInitialValue);
        initialValue.setText(CounterRepository
                                .getInstance()
                                .getCounterById(counterId)
                                .getInitialValue()
                                .toString());

        comment = (EditText) findViewById(R.id.editComment);
        comment.setText(CounterRepository
                                .getInstance()
                                .getCounterById(counterId)
                                .getComment());

        currentValue = (EditText) findViewById(R.id.editCurrentValue);
        currentValue.setText(CounterRepository
                                .getInstance()
                                .getCounterById(counterId)
                                .getCurrentValue()
                                .toString());
    }

    public void updateCounter(View view) {
        if ( !counterName.getText().toString().trim().equalsIgnoreCase("") &&
                !initialValue.getText().toString().trim().equalsIgnoreCase("") &&
                !currentValue.getText().toString().trim().equalsIgnoreCase("") ) {

            CounterController.updateCounter(counterId, counterName.getText().toString(),
                                            initialValue.getText().toString(),
                                            currentValue.getText().toString(),
                                            comment.getText().toString());
            finish();

        } else {
            counterNameWrapper.setErrorEnabled(true);
            counterNameWrapper.setError("This field cannot be blank!");
            initialValueWrapper.setErrorEnabled(true);
            initialValueWrapper.setError("This field cannot be blank");
            currentValueWrapper.setErrorEnabled(true);
            currentValueWrapper.setError("This field cannot be blank");
        }
    }

    public void deleteCounter(View view) {
        CounterController.deleteCounter(counterId);
        finish();
    }
}
