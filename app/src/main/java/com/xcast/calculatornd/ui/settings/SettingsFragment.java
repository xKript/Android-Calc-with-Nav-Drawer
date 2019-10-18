package com.xcast.calculatornd.ui.settings;

import android.content.Intent;
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

public class SettingsFragment extends Fragment
{

    private SettingsViewModel galleryViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        galleryViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);
            }
        });

        Button applyButton = root.findViewById(R.id.apply);
        final TextView decimalPlaces = root.findViewById(R.id.tv_presicion);

        applyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CharSequence text = decimalPlaces.getText();
                if(text == null || text.toString().isEmpty())
                {
                    return;
                }
                int precision = Integer.parseInt(text.toString());
                if(precision>=0 && precision<=24)
                {
                    MathEvaluator.setPrecision(precision);
                    Toast.makeText(SettingsActivity.this,R.string.settings_applied,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    intent.putExtra("SOURCE","SETTINGS");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SettingsActivity.this,R.string.precision_out_range,Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

    }
}