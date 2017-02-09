package com.roaddeum.simplecashiercomputer;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by diannerobbi on 1/18/17.
 */

public class CoinRolls extends Fragment {

    OnViewSelectedListener _mListener;

    EditText edit25, edit10, edit5, edit1;
    TextView text25, text10, text5, text1, totalAmt, totalDenominations;
    Button calc;
    int total;
    float s25, s10, s5, s1, sTotal;

    public CoinRolls() {
        // Required empty public constructor
    }

    public static CoinRolls newInstance() {
        CoinRolls fragment = new CoinRolls();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_coin_rolls, container, false);

        calc = (Button) rootView.findViewById(R.id.rollsButton);

        //Denominations
        edit25 = (EditText) rootView.findViewById(R.id.d25);
        edit10 = (EditText) rootView.findViewById(R.id.d10);
        edit5 = (EditText) rootView.findViewById(R.id.d5);
        edit1 = (EditText) rootView.findViewById(R.id.d1);

        //Totals
        text25 = (TextView) rootView.findViewById(R.id.t25);
        text10 = (TextView) rootView.findViewById(R.id.t10);
        text5 = (TextView) rootView.findViewById(R.id.t5);
        text1 = (TextView) rootView.findViewById(R.id.t1);

        //Last Row
        totalAmt = (TextView) rootView.findViewById(R.id.totalAmt);
        totalDenominations = (TextView) rootView.findViewById(R.id.totalDenominations);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    try{
                        s25 = Integer.parseInt(edit25.getText().toString());
                        s10 = Integer.parseInt(edit10.getText().toString());
                        s5 = Integer.parseInt(edit5.getText().toString());
                        s1 = Integer.parseInt(edit1.getText().toString());
                    }
                    catch(Exception e){
                        s25 = 0;
                        s10 = 0;
                        s5 = 0;
                        s1 = 0;
                    }

                    DecimalFormat form = new DecimalFormat("0.00");

                    //Total denominations
                    total = (int)(s25 + s10 + s5 + s1);

                    //Amounts
                    s25  *= 10.00;
                    s10  *= 5.00;
                    s5   *= 2.00;
                    s1   *= 0.50;
                    sTotal = s25 + s10 + s5 + s1;

                    text25.setText("$ " + form.format(s25));
                    text10.setText("$ " + form.format(s10));
                    text5.setText("$ " + form.format(s5));
                    text1.setText("$ " + form.format(s1));

                    //Last Row Totals
                    totalAmt.setText("$ " + form.format(sTotal));
                    totalDenominations.setText(Integer.toString(total));

                    _mListener.onViewSelected(1, sTotal);
                }
                catch(Exception e){
                    calc.setText("Exception caught.");
                }
            }
        });

        return rootView;
    }

    public interface OnViewSelectedListener{
        void onViewSelected(int position, float total);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            _mListener = (OnViewSelectedListener) activity;
        }
        catch(ClassCastException c){
            throw new ClassCastException(activity.toString() + " must implement onViewSelected.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mListener = null;
    }
}
