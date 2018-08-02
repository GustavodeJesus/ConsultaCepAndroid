package gustavo.projetos.com.consultacep;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

public class AddressRequest extends AsyncTask<Void, Void, Address> {
    private WeakReference<SignUpActivity> activity;

    public AddressRequest(SignUpActivity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.get().lockFieds(true);
    }


    @Override
    protected Address doInBackground(Void... voids) {

        try {
            String jsonString = JsonRequest.request(activity.get().getUriRequest());
            Gson gson = new Gson();

            return gson.fromJson(jsonString, Address.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Address address) {
        super.onPostExecute(address);

        if(activity.get() != null) {
            activity.get().lockFieds(false);

            if (address != null) {
                activity.get().setAddressFields(address);
            }
        }
    }
}
