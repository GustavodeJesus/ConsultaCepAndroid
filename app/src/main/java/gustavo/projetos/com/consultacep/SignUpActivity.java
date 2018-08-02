package gustavo.projetos.com.consultacep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {
    private EditText etZipCode;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etZipCode = (EditText) findViewById(R.id.et_zip_code);
        etZipCode.addTextChangedListener(new ZipCodeListener(this));

        Spinner spStates = (Spinner) findViewById(R.id.sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.states,
                        android.R.layout.simple_spinner_item);
        spStates.setAdapter(adapter);

        util = new Util(this,
                R.id.et_street,
                R.id.tv_zip_code_search,
                R.id.et_complement,
                R.id.et_neighbor,
                R.id.et_city,
                R.id.sp_state);
    }

    private String getZipCode() {
        return etZipCode.getText().toString();
    }

    public String getUriRequest() {
        return "https://viacep.com.br/ws/" + getZipCode() + "/json";
    }

    public void lockFieds(boolean isToLock) {
        util.lockFields(isToLock);
    }

    public void searchZipCode(View view) {
        /* TODO */
    }

    public void setAddressFields(Address address) {
        setField(R.id.et_street, address.getLogradouro());
        setField(R.id.et_complement, address.getComplemento());
        setField(R.id.et_neighbor, address.getBairro());
        setField(R.id.et_city, address.getLocalidade());
        setSpinner(R.id.sp_state, R.array.states, address.getUf());
    }

    public void setField(int fieldId, String data) {
        ((EditText) findViewById(fieldId)).setText(data);
    }

    public void setSpinner(int fieldId, int arrayId, String uf) {
        Spinner spinner = (Spinner) findViewById((fieldId));
        String[] states = getResources().getStringArray(arrayId);

        for (int i = 0; i < states.length; i++) {
            if (states[i].endsWith("(" + uf + ")")) {
                spinner.setSelection(i);
                break;
            }
        }
    }

}
