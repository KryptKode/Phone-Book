package com.kryptkode.cyberman.phonebook.fragments;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kryptkode.cyberman.phonebook.R;

/**
 * Created by Cyberman on 5/14/2017.
 */

public class ModifyAddFragment extends Fragment {

    public ModifyAddFragment() {
    }

    public interface ModifyAddFragmentListener{
        public void saveContact();
    }

    ModifyAddFragmentListener modifyAddFragmentListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_add, container, false);


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_save);
        floatingActionButton.setOnClickListener(saveNewContact);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        modifyAddFragmentListener = (ModifyAddFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
       modifyAddFragmentListener = null;
    }

    View.OnClickListener saveNewContact = new View.OnClickListener(){
        public void onClick(View view){
            modifyAddFragmentListener.saveContact();
        }
    };
}
