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

    public static PlayerListFragment newInstance(int itemList) {
        PlayerListFragment fragment = new PlayerListFragment();
        Bundle args = new Bundle();
        args.putInt("itemList", itemList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int itemList = getArguments().getInt("itemList");
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.player_list)
                .setItems(itemList, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Resources res = getResources();
                        String[] playerList = res.getStringArray(itemList);
                        ((FormationView) getActivity()).doItemClick(playerList[which]);
                    }
                })
                .create();
    }
}
