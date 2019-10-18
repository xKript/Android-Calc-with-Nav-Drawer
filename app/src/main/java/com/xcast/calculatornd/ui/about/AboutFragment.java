package com.xcast.calculatornd.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.xcast.calculatornd.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutFragment extends Fragment
{

    private AboutViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        Element versionElement = new Element();
        versionElement.setTitle(getString(R.string.version)+" 1.1");
        View aboutPage = new AboutPage(getContext())
                .setDescription(getString(R.string.description))
                .isRTL(false)
                .setImage(R.drawable.calculator_256)
                .addItem(versionElement)
                .addGroup(getString(R.string.connect_us))
                .addEmail("juanpablo495@hotmail.com")
                .addFacebook("juanpablolel")
                .addTwitter("almostHuman19")
                .addGitHub("xKript","JuanP")
                .addInstagram("juanpabloxt")
                .create();
        return aboutPage;
    }
}