package com.technopark_mail_ru.borodin.hw1;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NumberListFragment extends Fragment {
    private Integer numberOfValues = R.integer.default_number_of_values;
    private GridListener listener;

    void fillList(List<String> listToFill) {
        for (int i = 0; i < numberOfValues; i++) {
            listToFill.add(i + "");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            numberOfValues = savedInstanceState.getInt("numberOfValues");
        }

        View view = inflater.inflate(R.layout.number_list_fragment, container, false);
        final ArrayList<String> numbersStrings = new ArrayList<>();
        fillList(numbersStrings);


        RecyclerView recyclerView = view.findViewById(R.id.numbers_list);
        final GridLayoutManager layout = new GridLayoutManager(getActivity(), R.integer.portrait_orientation_grid_columns_size);
        recyclerView.setLayoutManager(layout);
        final NumbersListRecyclerViewAdapter myAdapter = new NumbersListRecyclerViewAdapter(numbersStrings);
        recyclerView.setAdapter(myAdapter);

        Button button = view.findViewById(R.id.add_number_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbersStrings.add(numbersStrings.size() + "");
                numberOfValues++;
                myAdapter.notifyItemInserted(myAdapter.getItemCount());
            }
        });
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            layout.setSpanCount(R.integer.portrait_orientation_grid_columns_size);
        else
            layout.setSpanCount(R.integer.landscape_orientation_grid_columns_size);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (GridListener)context;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("numberOfValues", numberOfValues);
    }

    class NumbersListRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public NumbersListRecyclerViewHolder( View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.number_text);
        }
    }

    class NumbersListRecyclerViewAdapter extends RecyclerView.Adapter<NumbersListRecyclerViewHolder> {

        private List<String> mNumbersStrings;

        public NumbersListRecyclerViewAdapter(List<String> mNumbersStrings) {
            this.mNumbersStrings = mNumbersStrings;
        }

        @NonNull
        @Override
        public NumbersListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View v = inflater.inflate(R.layout.number_list_element, viewGroup, false);

            return new NumberListFragment.NumbersListRecyclerViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull NumbersListRecyclerViewHolder numbersListRecyclerViewHolder, final int i) {
            String str = mNumbersStrings.get(i);
            numbersListRecyclerViewHolder.mTextView.setText(str);

            if (i % 2 == 0)
                numbersListRecyclerViewHolder.mTextView.setTextColor(Color.RED);
            else
                numbersListRecyclerViewHolder.mTextView.setTextColor(Color.BLUE);

            int currentColor = numbersListRecyclerViewHolder.mTextView.getCurrentTextColor();
            final String numberColor = String.format("#%06X", (0xFFFFFF & currentColor));
            numbersListRecyclerViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.itemClicked(i,numberColor);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNumbersStrings.size();
        }
    }

}
