package my.edu.tarc.lab2intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_TAG="my.edu.tarc.labintent.MESSAGE";
    private static final int REQUEST_REPLY_CODE =1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main","onCreate");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("Main","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main","onPause");
    }

    public void sendMessage(View view){
        String stringMsg;
        EditText editTextMsg;
        editTextMsg=findViewById(R.id.editTextMessage);
        if(TextUtils.isEmpty(editTextMsg.getText())){
            editTextMsg.setError(getString(R.string.error_message));
            return;
        }
        stringMsg=editTextMsg.getText().toString();

        //Explicit Intent
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra(MESSAGE_TAG,stringMsg);

        //startActivity(intent);
        startActivityForResult(intent,REQUEST_REPLY_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO: Complete the results handling process

        if(requestCode==REQUEST_REPLY_CODE){
            if(resultCode==RESULT_OK){
                if(data.hasExtra(SecondActivity.REPLY_TAG)){
                    String stringReply;
                    TextView textViewReply=findViewById(R.id.textViewReply);
                    stringReply=data.getStringExtra(SecondActivity.REPLY_TAG);
                    textViewReply.setText(stringReply);
                }
            }
        }
    }
}
