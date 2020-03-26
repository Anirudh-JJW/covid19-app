package com.jayskhatri.covid19_track.ui.helpline;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jayskhatri.covid19_track.R;

import java.util.Objects;

public class HelpLineFragment extends Fragment {

    final private String TAG = HelpLineFragment.class.getSimpleName();
    private CardView callHelpline, callTollFree, mailToHelp, whatsappHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help_line, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        callHelpline = (CardView) Objects.requireNonNull(getActivity()).findViewById(R.id.card_view_call_helpline);
        callTollFree = (CardView) getActivity().findViewById(R.id.card_view_call_toll_free);
        mailToHelp = (CardView) getActivity().findViewById(R.id.card_view_mail);
        whatsappHelp = (CardView) getActivity().findViewById(R.id.card_view_whatsapp_help);

        callHelpline.setOnClickListener(view -> callHelp("+911123978046"));

        callTollFree.setOnClickListener(view -> callHelp("1075"));
        mailToHelp.setOnClickListener(view -> mail("ncov2019@gov.in"));
        whatsappHelp.setOnClickListener(this::whatsappHelp);
    }

    private void callHelp(String num){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+num));
        startActivity(intent);
    }

    private void mail(String email){
        final Intent send = new Intent(Intent.ACTION_SENDTO);

        final String uriText = "mailto:" + Uri.encode(email);

        final Uri uri = Uri.parse(uriText);

        send.setData(uri);
        startActivity(Intent.createChooser(send,"Send Email"));
    }

    private void whatsappHelp(View v){
        Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
        String url = "https://wa.me/41798931892?text=hi";
        intentWhatsapp.setData(Uri.parse(url));
        intentWhatsapp.setPackage("com.whatsapp");
        startActivity(intentWhatsapp);
    }
}
