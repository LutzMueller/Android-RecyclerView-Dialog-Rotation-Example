package de.cubinea.rotationexample;

/**
 * DialogClickHandler
 * Has to be implemented by fragments using the {@link ExampleDialogFragment}.
 */
interface DialogClickHandler {

    /**
     * Handles clicks on the positive button of a dialog.
     *
     * @param requestCode The request code passed to the dialog fragment identifying which dialog is displayed.
     */
    void onOkClick(int requestCode);
}
