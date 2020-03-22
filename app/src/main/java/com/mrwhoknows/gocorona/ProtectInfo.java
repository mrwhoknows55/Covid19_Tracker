package com.mrwhoknows.gocorona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProtectInfo extends AppCompatActivity {

    TextView title, sub, body;
    ClickableSpan ministryLink, helplines1, helplines2, helplines3, helplines4, myInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_info);

        title = findViewById(R.id.infoTitle);
        sub = findViewById(R.id.infoSub);
        body = findViewById(R.id.infoBody);


        ministryLink = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.mohfw.gov.in/"));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        helplines1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode("1075")));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        helplines2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode("+91-11-23978046")));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        helplines3 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
               Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+"ncov2019@gov.in"));
               startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        helplines4 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+" ncov2019@gmail.com"));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        myInfo = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://instagram.com/mr_whoknows/"));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        if (getIntent().hasExtra("view")) {
            switch (getIntent().getIntExtra("view", 0)) {
                case 1:
                    case1();
                    break;

                case 2:
                    case2();
                    break;

                case 3:
                    case3();
                    break;

                case 4:
                    case4();
                    break;
            }
        }

    }


    private void case4() {
        title.setText(R.string.aboutTitle);

        SpannableString me = new SpannableString(getResources().getString(R.string.aboutSub));
        me.setSpan(myInfo, 23, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sub.setText(me);
        sub.setMovementMethod(LinkMovementMethod.getInstance());

        body.setText(R.string.aboutBody);
    }

    private void case3() {
        title.setText(R.string.helplines);

        SpannableString helpMinistryLink = new SpannableString(getResources().getString(R.string.infoSubHelp));
        helpMinistryLink.setSpan(ministryLink, 22, 52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sub.setText(helpMinistryLink);
        sub.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString helpNumbers1 = new SpannableString("1. Toll free:  1075\n");
        SpannableString helpNumbers2 = new SpannableString("2. Toll free:  +91-11-23978046\n");
        SpannableString helpMail1 = new SpannableString("3. Email ID:  ncov2019@gov.in\n");
        SpannableString helpMail2 = new SpannableString("4. Email ID:  ncov2019@gmail.com");

        helpNumbers1.setSpan(helplines1, 15, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helpNumbers2.setSpan(helplines2, 15, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helpMail1.setSpan(helplines3, 14, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        helpMail2.setSpan(helplines4, 14, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        body.append(helpNumbers1);
        body.append(helpNumbers2);
        body.append(helpMail1);
        body.append(helpMail2);
        body.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void case2() {
        title.setText(R.string.infoTitleSymptoms);
        sub.setText(getString(R.string.infoSubSymptoms));
        body.setText(R.string.infoBodySymptoms);
    }

    private void case1() {
        title.setText(R.string.infoTitleProtect);
        sub.setText(R.string.infoSubProtect);
        body.setText(R.string.infoBodyProtect);
    }


}
