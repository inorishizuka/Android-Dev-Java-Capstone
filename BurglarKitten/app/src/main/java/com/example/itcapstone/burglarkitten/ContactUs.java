package com.example.itcapstone.burglarkitten;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactUs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUs extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText editTextName;
    private EditText editTextComment;
    private EditText editTextEmail;
    private EditText editTextAddress;
    private EditText editCity;
    private EditText editZipCode;
    private EditText editState;

    public ContactUs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUs.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUs newInstance(String param1, String param2) {
        ContactUs fragment = new ContactUs();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);
        Button submit = (Button) rootView.findViewById(R.id.buttonSubmit);
        editTextName = (EditText)rootView.findViewById(R.id.editTextName);
        editTextComment = (EditText)rootView.findViewById(R.id.editTextComment);
        editTextEmail = (EditText)rootView.findViewById(R.id.editTextEmail);
        editTextAddress = (EditText)rootView.findViewById(R.id.editTextAddress);
        editCity = (EditText)rootView.findViewById(R.id.editCity);
        editZipCode = (EditText)rootView.findViewById(R.id.editZipCode);
        editState = (EditText)rootView.findViewById(R.id.editState);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1=editTextName.getText().toString();
                String str2=editCity.getText().toString();
                String str3=editState.getText().toString();
                String str4=editTextAddress.getText().toString();
                String str5=editTextComment.getText().toString();
                String str6=editTextEmail.getText().toString();
                String str7=editZipCode.getText().toString();
                if(!str1.equals("") && !str2.equals("") && !str3.equals("") && !str4.equals("") && !str5.equals("") && !str6.equals("") && !str7.equals("")) {
                    submitRegistration();
                }
                else{
                    Toast.makeText(getContext(), "Please Fill in all Fields", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    public void submitRegistration() {

        // You can change the email address that messages are sent to by changing the string below
            String to = "m6682@yahoo.com";
            String subject = "Feedback From " + editTextName.getText().toString();
            String message = "Return Email: " + editTextEmail.getText().toString() + "\n\n" + "User Address: " + "\n\n" + editTextAddress.getText().toString() + "\n" + editCity.getText().toString() + ", " + editState.getText().toString() + "\n" + editZipCode.getText().toString() + "\n" + editTextComment.getText().toString();
            Intent mail = new Intent(Intent.ACTION_SEND);
            mail.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            mail.putExtra(Intent.EXTRA_SUBJECT, subject);
            mail.putExtra(Intent.EXTRA_TEXT, message);
            mail.setType("message/rfc822");
            startActivity(Intent.createChooser(mail, "Send email via:"));
        }

    }


