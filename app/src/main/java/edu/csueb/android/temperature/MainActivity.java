
package edu.csueb.android.temperature;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
public class MainActivity extends Activity {
    public static class ConverterUtil {
        // converts to Celsius
        public static float convertFahrenheitToCelsius(float fahrenheit) {
            return ((fahrenheit - 32) * 5 / 9);
        }
        // converts to fahrenheit
        public static float convertCelsiusToFahrenheit(float celsius) {
            return ((celsius * 9) / 5) + 32;
        }
    }
    private EditText text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.editText);
    }
    // this method is called at button click because we assigned the name to
// the "OnClick property" of the button
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            RadioButton celsiusButton =
                    (RadioButton) findViewById(R.id.radioButton);
            RadioButton fahrenheitButton =
                    (RadioButton) findViewById(R.id.radioButton1);
            if (text.getText().length() == 0) {
                Toast.makeText(this, "Please enter a valid number",
                        Toast.LENGTH_LONG).show();
                return;
            }
            float inputValue = Float.parseFloat(text.getText().toString());
            if (celsiusButton.isChecked()) {
                text.setText(String
                        .valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                celsiusButton.setChecked(false);
                fahrenheitButton.setChecked(true);
            } else {
                text.setText(String
                        .valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                fahrenheitButton.setChecked(false);
                celsiusButton.setChecked(true);
            }
        }
    }
}