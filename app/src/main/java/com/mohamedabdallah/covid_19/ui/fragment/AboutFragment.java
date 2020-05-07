package com.mohamedabdallah.covid_19.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mohamedabdallah.covid_19.R;

public class AboutFragment extends Fragment {
    private ImageView facebook,linkedin,gmail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_about, container, false);

        facebook=view.findViewById(R.id.facebooky);
        linkedin=view.findViewById(R.id.linkediny);
        gmail=view.findViewById(R.id.gmaily);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFacebook();

            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLinkedIn();

            }
        });
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        return  view;
    }

    private void openFacebook()
    {
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100006851673761"));
//        startActivity(browserIntent);

        String profile_url = "https://www.facebook.com/profile.php?id=100047805403025";
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(profile_url));
            intent.setPackage("com.facebook.android");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            //if the user doesn't have a LinkedIn account, open an intent to a browser
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(profile_url)));
        }

    }

    private void openLinkedIn(){

        String profile_url = "https://www.linkedin.com/in/mohamed-abdallah-092973b0/";
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(profile_url));
            intent.setPackage("com.linkedin.android");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            //if the user doesn't have a LinkedIn account, open an intent to a browser
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(profile_url)));
        }
    }

    private void sendMail(){
        String recipientList = "mohamed.abdallah5012@gmail.com";
        String[] recipients = recipientList.split(",");

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(intent,"Pick an Email Client !"));
        }catch (ActivityNotFoundException e)
        {
            Toast.makeText(getActivity(),"There are no Email Clients installed",Toast.LENGTH_SHORT).show();

        }
    }

}
