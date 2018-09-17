package com.software.tkouleris.morsecode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Camera camera;
    private Camera.Parameters parameters;
    private Button BtnEncode;
    private EditText txt_Plaintext;
    boolean RunningMorseCode = false;
    private MorseCodeAlphabet MorseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MorseCode = new MorseCodeAlphabet();

        BtnEncode = (Button) findViewById(R.id.btn_Encode);
        txt_Plaintext = (EditText)findViewById(R.id.txt_PlainText);
        BtnEncode.setOnClickListener(new FlashOnOffListener());

        if (isFlashSupported()) {
            InitCamera();
        } else {
            showNoFlashAlert();
        }
    }

    private class FlashOnOffListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String plainText = txt_Plaintext.getText().toString().toUpperCase();


            //Remove empty spaces
            plainText.replaceAll("\\s+","");
            String codedText = "";

            if (RunningMorseCode) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.stopPreview();
                RunningMorseCode = false;
            } else {
                codedText = EncodeTextInMorseCode(plainText, MorseCode);
                FlashCodedText(codedText);
            }
        }

    }


    private void InitCamera()
    {
        camera = Camera.open();
        parameters = camera.getParameters();
    }


    private void FlashCodedText(String codedText)
    {
        int i = 0;
        while(codedText.length() > i)
        {
            if( codedText.charAt(i) == '.' ) {
                FlashDot();
            }
            else if( codedText.charAt(i) == '-' ) {
                FlashDash();
            }
            else{
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }

    private String EncodeTextInMorseCode(String plainText, MorseCodeAlphabet alphabet)
    {
        char plainLetter;
        String codedLetter;
        String codedTextTemp = "";
        int i = 0;
        while(plainText.length() > i)
        {
            plainLetter = plainText.charAt(i);
            codedLetter = alphabet.getMorseCoeLetter(Character.toString(plainLetter) );
            codedTextTemp = codedTextTemp + codedLetter+"\\";
            i++;
        }

        return codedTextTemp;
    }

    private void FlashDot()
    {

        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
        camera.startPreview();
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
        camera.stopPreview();

    }

    private void FlashDash()
    {
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
        camera.startPreview();
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
        camera.stopPreview();
    }


    private void showNoFlashAlert() {
        new AlertDialog.Builder(this)
                .setMessage("Your device hardware does not support flashlight!")
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Error")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
    }

    private boolean isFlashSupported() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    protected void onDestroy() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        super.onDestroy();
    }


}

