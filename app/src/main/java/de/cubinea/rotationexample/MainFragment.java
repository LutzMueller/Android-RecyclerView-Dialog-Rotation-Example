package de.cubinea.rotationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements DialogClickHandler {

    private RecyclerView mRecyclerView;

    private NumberAdapter mAdapter;
    private List<Integer> mNumbers;

    /**
     * Empty constructor required by the android os.
     */
    public MainFragment() {
        // Requires empty public constructor
    }

    /**
     * Wrapper for the constructor that can contain additional initialization code.

     * @return A new and configured FaqFragment.
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        // setup data source
        mNumbers = readNumbers();
    }

    @Override
    public void onResume() {
        super.onResume();

        mAdapter.updateData(mNumbers);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.number_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // adapter gets data in updateData method
        mAdapter = new NumberAdapter(new ArrayList<Integer>());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_dialog, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_dialog) {
            ExampleDialogFragment.showDialog(this, "Example Dialog", "I will survive roation!", "OK",
                    ExampleDialogFragment.REQUEST_CODE_EXAMPLE_DIALOG);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOkClick(int requestCode) {
        // is empty, because we have no actions on dialog button click
    }

    private List<Integer> readNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            numbers.add(i);
        }

        return numbers;
    }

}
