package de.cubinea.rotationexample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExampleDialogFragment extends DialogFragment {

    public static final int REQUEST_CODE_EXAMPLE_DIALOG = 1;

    private static final String TAG = "ExampleDialogFragment";
    private static final String TITLE_KEY = "title";
    private static final String MESSAGE_KEY = "message";
    private static final String BUTTON_KEY = "button";
    private static final String REQUEST_KEY = "request";

    public static void showDialog(final Fragment fragment, String title, String message, String buttonText,
                                          int requestCode) {

        showOkDialog(fragment, title, message, buttonText, requestCode);
    }

    private static void showOkDialog(Fragment fragment, String title, String message, String buttonText, int requestCode) {

        if (!(fragment instanceof DialogClickHandler)) {
            throw new IllegalArgumentException("Fragment has to implement DialogClickHandler.");
        }

        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, title);
        bundle.putString(MESSAGE_KEY, message);
        bundle.putString(BUTTON_KEY, buttonText);
        bundle.putInt(REQUEST_KEY, requestCode);

        ExampleDialogFragment dialogFragment = new ExampleDialogFragment();
        dialogFragment.setTargetFragment(fragment, requestCode);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(fragment.getFragmentManager(), TAG);
        dialogFragment.setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        Bundle arguments = getArguments();

        final String title = arguments.getString(TITLE_KEY);
        final String buttonText = arguments.getString(BUTTON_KEY);
        final int requestCode = arguments.getInt(REQUEST_KEY);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(title);
            String message = arguments.getString(MESSAGE_KEY);
            builder.setMessage(message);

            builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ((DialogClickHandler) getTargetFragment()).onOkClick(requestCode);
                }
            });

        // Create the AlertDialog object and return it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(),
                        R.color.colorPrimary));
            }
        });
        return alertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getTheme() {
        return R.style.AppTheme;
    }

}
