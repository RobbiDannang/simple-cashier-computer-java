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

public class Bills extends Fragment {
    EditText edit100, edit50, edit20, edit10, edit5, edit2, edit1;
    TextView text100, text50, text20, text10, text5, text2, text1, textTotal, textDen;
    Button calc;
    int s100, s50, s20, s10, s5, s2, s1, sTotal;

    OnViewSelectedListener _mListener;

    public Bills() {
        // Required empty public constructor
    }

    public static Bills newInstance() {
        Bills fragment = new Bills();
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
        View rootView = inflater.inflate(R.layout.fragment_bills, container, false);

        calc = (Button) rootView.findViewById(R.id.billsButton);

        //Denominations
        edit100 = (EditText) rootView.findViewById(R.id.den100);
        edit50 = (EditText) rootView.findViewById(R.id.den50);
        edit20 = (EditText) rootView.findViewById(R.id.den20);
        edit10 = (EditText) rootView.findViewById(R.id.den10);
        edit5 = (EditText) rootView.findViewById(R.id.den5);
        edit2 = (EditText) rootView.findViewById(R.id.den2);
        edit1 = (EditText) rootView.findViewById(R.id.den1);

        //Total Amounts
        text100 = (TextView) rootView.findViewById(R.id.tot100);
        text50 = (TextView) rootView.findViewById(R.id.tot50);
        text20 = (TextView) rootView.findViewById(R.id.tot20);
        text10 = (TextView) rootView.findViewById(R.id.tot10);
        text5 = (TextView) rootView.findViewById(R.id.tot5);
        text2 = (TextView) rootView.findViewById(R.id.tot2);
        text1 = (TextView) rootView.findViewById(R.id.tot1);

        //Last Row Totals
        textTotal = (TextView) rootView.findViewById(R.id.textTotal);
        textDen = (TextView) rootView.findViewById(R.id.textDen);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    try {
                        s100 = Integer.parseInt(edit100.getText().toString());
                    }
                    catch(Exception e){
                        s100 = 0;
                    }
                    try{
                        s50 = Integer.parseInt(edit50.getText().toString());
                    }
                    catch(Exception e) {
                        s50 = 0;
                    }
                    try{
                        s20 = Integer.parseInt(edit20.getText().toString());
                    }
                    catch(Exception e) {
                        s20 = 0;
                    }try{
                        s10 = Integer.parseInt(edit10.getText().toString());
                    }
                    catch(Exception e) {
                        s10 = 0;
                    }try{
                        s5 = Integer.parseInt(edit5.getText().toString());
                    }
                    catch(Exception e) {
                        s5 = 0;
                    }try{
                        s2 = Integer.parseInt(edit2.getText().toString());
                    }
                    catch(Exception e) {
                        s2 = 0;
                    }try{
                        s1 = Integer.parseInt(edit1.getText().toString());
                    }
                    catch(Exception e) {
                        s1 = 0;
                    }


                    textDen.setText(Integer.toString(s1 + s2 + s5 + s10 + s20 + s50 + s100));
                    //Total amount
                    s100 *= 100;
                    s50  *= 50;
                    s20  *= 20;
                    s10  *= 10;
                    s5   *= 5;
                    s2   *= 2;
                    s1   *= 1;

                    sTotal = s100 + s50 + s20 + s10 + s5 + s2 + s1;

                    DecimalFormat form = new DecimalFormat("0.00");

                    text100.setText("$ " + form.format(s100));
                    text50.setText("$ " + form.format(s50));
                    text20.setText("$ " + form.format(s20));
                    text10.setText("$ " + form.format(s10));
                    text5.setText("$ " + form.format(s5));
                    text2.setText("$ " + form.format(s2));
                    text1.setText("$ " + form.format(s1));

                    textTotal.setText("$ " + form.format(sTotal));

                    _mListener.onViewSelected(0, (float)sTotal);

                } catch (Exception e) {
                    calc.setText("Exception occurred.");
                }

            }
        });

        return rootView;
    }

    public interface OnViewSelectedListener {

        void onViewSelected(int position, float total);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            _mListener = (OnViewSelectedListener) activity;
        }
        catch(ClassCastException c){
            throw new ClassCastException(activity.toString() + "must implement onViewSelected");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mListener = null;
    }

}
