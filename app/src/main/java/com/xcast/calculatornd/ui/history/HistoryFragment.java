package com.xcast.calculatornd.ui.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.xcast.calculatornd.R;

import java.util.Map;

public class HistoryFragment extends Fragment
{

    private HistoryViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        toolsViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TextView textView = root.findViewById(R.id.text_history);
        toolsViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                //textView.setText(s);
            }
        });

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.history_file),Context.MODE_PRIVATE);
        String history = "Last operations:\n";
        Map h = sharedPref.getAll();
        for(Object str : h.values())
        {
            history += str.toString()+"\n";
        }

        textView.setText(history);

        return root;
    }
}