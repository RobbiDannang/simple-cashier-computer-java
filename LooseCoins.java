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

public class LooseCoins extends Fragment {

    EditText edit100, edit50, edit25, edit10, edit5, edit1;
    TextView text100, text50, text25, text10, text5, text1, totalAmount, totalDenomination;
    Button calc;
    int totalDen;
    float s100, s50, s25, s10, s5, s1, sTotal;

    OnViewSelectedListener _mListener;

    public LooseCoins() {
        // Required empty public constructor
    }

    public static LooseCoins newInstance() {
        LooseCoins fragment = new LooseCoins();
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
        View rootView = inflater.inflate(R.layout.fragment_loose_coins, container, false);

        calc = (Button) rootView.findViewById(R.id.coinsButton);

        //Denominations
        edit100 = (EditText) rootView.findViewById(R.id.e100);
        edit50 = (EditText) rootView.findViewById(R.id.e50);
        edit25 = (EditText) rootView.findViewById(R.id.e25);
        edit10 = (EditText) rootView.findViewById(R.id.e10);
        edit5 = (EditText) rootView.findViewById(R.id.e5);
        edit1 = (EditText) rootView.findViewById(R.id.e1);

        //Amounts
        text100 = (TextView) rootView.findViewById(R.id.l100);
        text50 = (TextView) rootView.findViewById(R.id.l50);
        text25 = (TextView) rootView.findViewById(R.id.l25);
        text10 = (TextView) rootView.findViewById(R.id.l10);
        text5 = (TextView) rootView.findViewById(R.id.l5);
        text1 = (TextView) rootView.findViewById(R.id.l1);

        //Last Row
        totalAmount = (TextView) rootView.findViewById(R.id.totalAmount);
        totalDenomination = (TextView) rootView.findViewById(R.id.totalDenomination);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    try{
                        s100 = Integer.parseInt(edit100.getText().toString());
                        s50 = Integer.parseInt(edit50.getText().toString());
                        s25 = Integer.parseInt(edit25.getText().toString());
                        s10 = Integer.parseInt(edit10.getText().toString());
                        s5 = Integer.parseInt(edit5.getText().toString());
                        s1 = Integer.parseInt(edit1.getText().toString());
                    }
                    catch(Exception e){
                        s100 = 0;
                        s50 = 0;
                        s25 = 0;
                        s10 = 0;
                        s5 = 0;
                        s1 = 0;
                    }

                    DecimalFormat form = new DecimalFormat("0.00");

                    //Total Denomination
                    totalDen = (int) (s100 + s50 + s25 + s10 + s5 + s1);

                    //Total Amount
                    s100 *= 1.00;
                    s50  *= 0.50;
                    s25  *= 0.25;
                    s10  *= 0.10;
                    s5   *= 0.05;
                    s1   *= 0.01;
                    sTotal= s100 + s50 + s25 + s10 + s5 + s1;

                    text100.setText("$ " + form.format(s100));
                    text50.setText("$ " + form.format(s50));
                    text25.setText("$ " + form.format(s25));
                    text10.setText("$ " + form.format(s10));
                    text5.setText("$ " + form.format(s5));
                    text1.setText("$ " + form.format(s1));

                    //Last Row Totals
                    totalDenomination.setText(String.valueOf(totalDen));
                    totalAmount.setText("$ " + form.format(sTotal));

                    //Across Fragments
                    _mListener.onViewSelected(2, sTotal);
                }
                catch (Exception e){
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
