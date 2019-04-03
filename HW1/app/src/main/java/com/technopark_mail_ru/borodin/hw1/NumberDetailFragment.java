package com.technopark_mail_ru.borodin.hw1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class NumberDetailFragment extends Fragment {

    private Integer number;
    private Integer numberColor;

    public NumberDetailFragment() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberColor() {
        return numberColor;
    }

    public void setNumberColor(Integer numberColor) {
        this.numberColor = numberColor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_detail_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null){
            TextView text = (TextView) view.findViewById((R.id.number_detail_text));
            text.setText(valueOf(number));
            text.setTextColor(numberColor);
        }
    }
}
