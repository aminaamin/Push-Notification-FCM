package com.crymzee.amina.pushnotificationfcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView tv_resp_fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_resp_fb=findViewById(R.id.tv_resp_fb);

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (!task.isSuccessful()) {
                           String msg = task.getException().getMessage();
                            Log.d(TAG, msg+"msg f");

                        }
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            String token=task.getResult().getToken();
                            Log.d(TAG, "onComplete: Token"+token);
                            tv_resp_fb.setText(token);
                            // token for emulator:
                            // f84Go6pCmJA:APA91bH7-cOrd_ZbdRShQK8od5MArLXUwYn3nuZHey0j5f7QLO6o7VUk5lpWljKp3tSdZ7Qu_rnyNwPAPJTGLG4xm2JH67pE-Ihh-Yj_nRU5Z1NIeNDDoCBM1NWAT5mSVaiWTG_2pZSa
                            // token for Device:
                            // diBE_X8HzWU:APA91bFIdpdvpxWNC5JqTY8HYKIrDt0giPexZhRrw-UxAA8nIOBpnS_mvAsgiIkNw1i9AaoET5IYTpAGpbCLzXyuMyfqyNMJn7uBzt6shQj7gw4WdEuJ1XYAFlpD-BE7VZvVSiv01Jfq
                        }
                        else {
                            tv_resp_fb.setText("Token Generated failed");
                        }
                    }
                });

    }
}