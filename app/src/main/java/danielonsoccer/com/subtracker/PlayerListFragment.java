package danielonsoccer.com.subtracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * Created by daniel on 11-Nov-14.
 */
public class PlayerListFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.player_list)
                .setItems(R.array.player_list_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Resources res = getResources();
                        String[] playerList = res.getStringArray(R.array.player_list_array);
                        ((FormationView)getActivity()).doItemClick(playerList[which]);
                    }
                });
        return builder.create();
    }
}
