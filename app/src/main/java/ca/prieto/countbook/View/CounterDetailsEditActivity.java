package ca.prieto.countbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class CounterDetailsEditActivity extends AppCompatActivity {
    CounterRepository instance;
    String counterId;

    EditText counterName;
    EditText initialValue;
    EditText comment;

    TextInputLayout counterNameWrapper;
    TextInputLayout initialValueWrapper;
    TextInputLayout commentWrapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details_edit);

        instance = CounterRepository.getInstance();
        Intent intent = getIntent();
        counterId = intent.getStringExtra(CounterDetailsActivity.CounterDetailsMessage);

        counterName = (EditText) findViewById(R.id.editCounterName);
        counterName.setText(CounterRepository.getInstance().getCounterById(counterId).getName());

        initialValue = (EditText) findViewById(R.id.editInitialValue);
        initialValue.setText(CounterRepository.getInstance().getCounterById(counterId).getInitialValue().toString());

        comment = (EditText) findViewById(R.id.editComment);
        comment.setText(CounterRepository.getInstance().getCounterById(counterId).getComment());

        counterNameWrapper = (TextInputLayout) findViewById(R.id.editCounterNameWrapper);
        initialValueWrapper = (TextInputLayout) findViewById(R.id.editInitialValueWrapper);
        commentWrapper = (TextInputLayout) findViewById(R.id.editCommentWrapper);
    }

    public void updateCounter(View view) {
        if ( !counterName.getText().toString().trim().equalsIgnoreCase("") ||
                !initialValue.getText().toString().trim().equalsIgnoreCase("") ) {

            Counter updatedCounter = new Counter(
                    counterName.getText().toString(),
                    Integer.parseInt(initialValue.getText().toString()),
                    comment.getText().toString());
            CounterController.updateCounter(updatedCounter, counterId);
            finish();

        } else {
            counterNameWrapper.setErrorEnabled(true);
            counterNameWrapper.setError("This field cannot be blank!");
            initialValueWrapper.setErrorEnabled(true);
            initialValueWrapper.setError("This field cannot be blank");
        }
    }

    public void deleteCounter(View view) {
        CounterController.deleteCounter(counterId);
        finish();
    }
}
