package suslova.yekaterina.syn_android07;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import static suslova.yekaterina.syn_android07.Follower.getIcon;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner spinner;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);
        textView.setTextIsSelectable(true);
        imageView = findViewById(R.id.imageView);
        JSONUpdateTask task = new JSONUpdateTask();
        task.execute(spinner.getSelectedItem().toString());
    }

    // Кнопка "Обновить"
    public void onClick(View view) {
        new JSONUpdateTask().execute(spinner.getSelectedItem().toString());
    }

    private class JSONUpdateTask extends AsyncTask<String, Void, Follower> {



        @Override
        protected Follower doInBackground(String... params) {
            return FollowerBuilder.buildStars(params[0]);
        }
        // ----------------------------------------------------------------------------


        @Override
        protected void onPostExecute(final Follower follower) {
            super.onPostExecute(follower);
            imageView.post(new Runnable() { //  Используем синхронизацию с UI
                @Override
                public void run() {

                    if (getIcon() != null) {
                        imageView.setImageBitmap(getIcon());
                    } else {
                        imageView.setImageResource(R.mipmap.ic_launcher); // Установка своей иконки при ошибке
                    }
                    imageView.invalidate(); // Принудительная прорисовка картинки на всякий случай
                }
            });

            textView.post(new Runnable() { //  с использованием синхронизации UI
                @Override
                public void run() {
                    textView.setText("");
                    if (follower.getLogin() != null) {

                        textView.append("Login: " + follower.getLogin() + "\n");
                        textView.append("Ссылка на профиль: " + follower.getUserURL()+ "\n");
                        textView.append("Ссылка на репозиторий: " + follower.getRepository());
                    } else {
                        textView.append("Нет данных" + "\n");
                        textView.append("Проверьте подключение с интернетом");
                    }
                }
            });

        }
        // ------------------------------------------------------------------------------------

    }

}
