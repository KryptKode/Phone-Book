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
 * A placeholder fragment containing a simple view.
 */
public class PhoneBookFragment extends Fragment {



    public interface PhoneBookFragmentListener {
        void addContact();
    }
    PhoneBookFragmentListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phone_book, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(addNewContact);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (PhoneBookFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    View.OnClickListener addNewContact = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.addContact();
        }
    };
}
