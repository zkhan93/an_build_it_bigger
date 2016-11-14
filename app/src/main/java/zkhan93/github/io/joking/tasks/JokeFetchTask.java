package zkhan93.github.io.joking.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.lang.ref.WeakReference;

import zkhan93.github.io.gcpjoker.jokerApi.JokerApi;
import zkhan93.github.io.joking.callbacks.JokeTaskCallback;

/**
 * Created by zeeshan on 11/14/2016.
 */

public class JokeFetchTask extends AsyncTask<Void, Void, String> {
    WeakReference<JokeTaskCallback> jokeTaskCallbackRef;

    public JokeFetchTask(JokeTaskCallback jokeTaskCallback) {
        jokeTaskCallbackRef = new WeakReference<JokeTaskCallback>(jokeTaskCallback);
    }

    private static JokerApi jokerApiService = null;

    @Override
    protected String doInBackground(Void... params) {
        if (jokerApiService == null) {  // Only do this once
            JokerApi.Builder builder = new JokerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.2.5:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokerApiService = builder.build();
        }

        try {
            return jokerApiService.tellJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        jokeTaskCallbackRef.get().showJoke(joke);
    }
}
