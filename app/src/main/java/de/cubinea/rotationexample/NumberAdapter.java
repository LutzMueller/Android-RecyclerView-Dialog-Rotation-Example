package de.cubinea.rotationexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class NumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> mNumbers = new ArrayList<>();

    NumberAdapter(final List<Integer> numbers) {
        mNumbers = numbers;
    }

    @Override
    public int getItemCount() {
        return mNumbers.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        final View numberView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_number,
                viewGroup, false);
        return new NumberViewHolder(numberView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        setupNumberView((NumberViewHolder) viewHolder, position);
    }

    private void setupNumberView(final NumberViewHolder viewHolder, final int position) {
        final Integer number = mNumbers.get(position);
        viewHolder.mNumberTextView.setText(String.valueOf(number));
    }

    void updateData(final List<Integer> numbers) {
        mNumbers = numbers;
        notifyDataSetChanged();
    }

    private static class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView mNumberTextView;

        NumberViewHolder(View itemView) {
            super(itemView);
            mNumberTextView = (TextView) itemView.findViewById(R.id.number);
        }
    }

}
