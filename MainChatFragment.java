package com.example.itcapstone.burglarkitten;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;



public class MainChatFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Firebase mFirebaseRef;
    private String mUsername;
    EditText mEditText;
    EditText userNameText;
    FirebaseListAdapter mListAdapter;



    public MainChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainChatFragment newInstance(String param1, String param2) {
        MainChatFragment fragment = new MainChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onLoginButtonClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Enter your email address and password")
                .setTitle("Log in");

        LayoutInflater inflater = this.getLayoutInflater(null);
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AlertDialog dlg = (AlertDialog) dialog;
                final String email = ((TextView)dlg.findViewById(R.id.email)).getText().toString();
                final String password =((TextView)dlg.findViewById(R.id.password)).getText().toString();

                mFirebaseRef.createUser(email, password, new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        mFirebaseRef.authWithPassword(email, password, null);
                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        mFirebaseRef.authWithPassword(email, password, null);
                    }
                });

            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_main_chat, container, false);
        Button sendButton = (Button) rootView.findViewById(R.id.sendButton);
        //Button loginButton = (Button) rootView.findViewById(R.id.login);
        Button userNameButton = (Button) rootView.findViewById(R.id.userButton);
        final EditText userNameText = (EditText) rootView.findViewById(R.id.username_text);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mUsername != null && !mUsername.equals("")) {
                    onSendButtonClick(v);
                }
                else{
                    Toast.makeText(getContext(), "Must Enter User Name", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onLoginButtonClick(v);
            }
        });
        */

        userNameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mUsername = userNameText.getText().toString();
                Toast.makeText(getContext(), "User Name Selected", Toast.LENGTH_LONG).show();
            }
        });


        Firebase.setAndroidContext(getContext());
        mFirebaseRef = new Firebase("https://burglar-kitten.firebaseio.com/");
        mEditText = (EditText) rootView.findViewById(R.id.message_text);

        mListAdapter = new FirebaseListAdapter<ChatMessage>(mFirebaseRef, ChatMessage.class,
                R.layout.message_layout, getActivity()) {
            @Override
            protected void populateView(View v, ChatMessage model) {
                ((TextView)v.findViewById(R.id.username_text_view)).setText(model.getName());
                ((TextView)v.findViewById(R.id.message_text_view)).setText(model.getMessage());
            }
        };
        setListAdapter(mListAdapter);
/*
        mFirebaseRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if(authData != null) {
                    mUsername = ((String)authData.getProviderData().get("email"));
                    rootView.findViewById(R.id.login).setVisibility(View.INVISIBLE);
                }
                else {
                    mUsername = null;
                    rootView.findViewById(R.id.login).setVisibility(View.VISIBLE);
                }
            }
        });*/
        return rootView;
    }

    public void onSendButtonClick(View v) {
        String message = mEditText.getText().toString();
        if(message != null && !message.equals("")) {
            mFirebaseRef.push().setValue(new ChatMessage(mUsername, message));
            mEditText.setText("");
        }
        else{
            Toast.makeText(getContext(), "Cant Send Empty Message", Toast.LENGTH_LONG).show();
        }
    }


}
