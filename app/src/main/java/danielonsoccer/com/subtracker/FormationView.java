package danielonsoccer.com.subtracker;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class FormationView extends Activity {

    Chronometer playerDuration;
    TextView substitutionTimerTextView;
    CountDownTimer substitutionTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout.
        setContentView(R.layout.activity_formation_view);

        // Start the player timer.
        playerDuration = (Chronometer) findViewById(R.id.lf_chronometer);
        playerDuration.start();

        // Starts the substitution countdown timer.
        substitutionTimerTextView = (TextView) findViewById(R.id.substitution_interval_timer);
        substitutionTimer = new CountDownTimer(300000, 1000) {

            // Display the time remaining on every tick in MM:SS format.
            public void onTick(long millisUntilFinished) {
                substitutionTimerTextView.setText(String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            // Vibrate the phone three times when the timer expires to indicate that substitutions
            // should be executed.
            public void onFinish() {
                Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context
                        .VIBRATOR_SERVICE);
                long[] vibratePattern = {0, 500, 500, 500, 500, 500, 500};
                vibrator.vibrate(vibratePattern, -1);
                this.start();
            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu.
        getMenuInflater().inflate(R.menu.menu_formation_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks.
        int id = item.getItemId();

        // Open the settings.
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Method called when a player is clicked.
    // Opens the dialogue to pick a substitute.
    public void openSubList(View view) {
        PlayerListFragment dialog = PlayerListFragment.newInstance(R.array.player_list_array);
        dialog.show(getFragmentManager(), "PlayerListFragment");
    }

    // Method called when the "execute substitutions" button is clicked.
    public void resetCard(View view) {
        CardView cardView = (CardView) findViewById(R.id.lf_card);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cardView, "scaleY", 1.0f);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    // Code executed after a substitute is queued.
    public void doItemClick(String playerName) {
        TextView textView = (TextView) findViewById(R.id.current_lf);
        textView.setText(playerName);
        CardView cardView = (CardView) findViewById(R.id.lf_card);
        cardView.setPivotY(100);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cardView, "scaleY", 2.0f);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        // Substitute player
        playerDuration.setBase(SystemClock.elapsedRealtime());
    }
}
