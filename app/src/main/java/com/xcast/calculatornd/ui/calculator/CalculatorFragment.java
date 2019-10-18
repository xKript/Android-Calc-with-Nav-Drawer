package com.xcast.calculatornd.ui.calculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.xcast.calculatornd.MainActivity;
import com.xcast.calculatornd.MathEvaluator;
import com.xcast.calculatornd.R;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment
{
    private CalculatorViewModel calculatorViewModel;
    private TextView result,operation;
    private final ArrayList<String> trigonometricOps;
    private static String lastOperation = "";
    private Button inverse;

    public CalculatorFragment()
    {
        this.trigonometricOps = new ArrayList<String>()
        {{
            add("sin");
            add("cos");
            add("tan");
        }};
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof MainActivity)
        {
            MainActivity ma = (MainActivity)context;
            ma.setCalcFragment(this);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);

        result = root.findViewById(R.id.result);
        operation = root.findViewById(R.id.operation);
        Button clean = root.findViewById(R.id.button_clean);
        inverse = root.findViewById(R.id.buttonInverse);

        clean.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                CharSequence text = operation.getText();
                if(text==null || text.toString().isEmpty()) {return;}
                String oldTxt = text.toString();
                String newTxt = oldTxt.substring(0,oldTxt.length()-1);
                operation.setText(newTxt);
                result.setText(MathEvaluator.evaluate(newTxt));
            }
        });

        clean.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                result.setText("");
                operation.setText("");
                return true;
            }
        });

        return root;
    }

    public void buttonClick(View view)
    {
        Button source = (Button)view;
        String key = source.getText().toString();
        String oldText = getOperation();
        String newOperation = oldText+key;
        String parenthesis = (trigonometricOps.contains(key))?"(":"";
        operation.setText(newOperation+parenthesis);
        result.setText(MathEvaluator.evaluate(newOperation));
    }

    public void invertNumber(View v)
    {
        String o = getOperation();
        if(o.isEmpty()) {return;}
        String newOp = "1/("+o+")";
        String r = MathEvaluator.evaluate(newOp);
        if(!r.isEmpty())
        {
            result.setText(r);
            operation.setText(newOp);
        }
    }

    private String getOperation()
    {
        CharSequence curOperation = operation.getText();
        return (curOperation!=null)?curOperation.toString():"";
    }


}