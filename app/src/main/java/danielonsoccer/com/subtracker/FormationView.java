package danielonsoccer.com.subtracker;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;


public class FormationView extends Activity {

    Chronometer playerDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation_view);
        playerDuration = (Chronometer)findViewById(R.id.chronometer);
        playerDuration.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formation_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openSubList(View view) {
        PlayerListFragment dialog = PlayerListFragment.newInstance(R.array.player_list_array);
        dialog.show(getFragmentManager(), "PlayerListFragment");
    }

    public void doItemClick(String playerName) {
        Button button = (Button)findViewById(R.id.button);
        button.setText(playerName);
        // Substitute player
        playerDuration.setBase(SystemClock.elapsedRealtime());
    }
}
