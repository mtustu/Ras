package mtustudent.ceit.kkhant.ras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText nameField;
    private EditText emailField;
    private EditText passField;
    private FirebaseAuth mAuth;
    private DatabaseReference mdatebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = (EditText) findViewById(R.id.shopName);
        emailField = (EditText) findViewById(R.id.email);
        passField = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
   //     mdatebase = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Register.this,Login.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void register(View view) {
        final String name = nameField.getText().toString().trim();

        String email = emailField.getText().toString().trim();

        String password = passField.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password))
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                      //  String user_id = mAuth.getCurrentUser().getUid();
                      //  DatabaseReference current_user_db = mdatebase.child(user_id);
                      //  current_user_db.child("Name").setValue(name);
                      //  current_user_db.child("image").setValue("default");
                        Intent intent = new Intent(Register.this,Login.class);
                        startActivity(intent);

                       /* Intent mainIntent = new Intent(Register.this,SetupActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);  */
                        finish();

                    }
                    else {
                        Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
