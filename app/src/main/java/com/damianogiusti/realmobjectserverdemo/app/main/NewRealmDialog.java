package com.damianogiusti.realmobjectserverdemo.app.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.damianogiusti.realmobjectserverdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class NewRealmDialog extends DialogFragment {

    public static final String TAG = NewRealmDialog.class.getSimpleName();

    public static NewRealmDialog newInstance() {
        return new NewRealmDialog();
    }

    @BindView(R.id.txtRealmName) EditText txtRealmName;

    public interface Listener {
        void onSaveRequested(String realmName);
    }

    private NewRealmDialog.Listener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof NewRealmDialog.Listener) {
            listener = (NewRealmDialog.Listener) activity;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.new_realm_dialog_layout, null, false);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.save, (dialog, which) -> listener.onSaveRequested(txtRealmName.getText().toString()))
                .setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
