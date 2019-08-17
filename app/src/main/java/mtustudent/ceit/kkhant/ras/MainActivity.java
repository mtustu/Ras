package mtustudent.ceit.kkhant.ras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private RecyclerView test1;
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("test1");
        test1 = (RecyclerView) findViewById(R.id.list_re);
        test1.setHasFixedSize(true);
        test1.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    Intent loginIntent = new Intent(MainActivity.this,Login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                    finish();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.addOp)
        {
            Intent intent = new Intent(MainActivity.this,Menu.class);
            startActivity(intent);

        }
        else if(id == R.id.logout)
        {
            mAuth.signOut();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerOptions<testClass> options =
                new FirebaseRecyclerOptions.Builder<testClass>()
                        .setQuery(mdatabase, testClass.class)
                        .build();
        FirebaseRecyclerAdapter<testClass,testViewHolder> FRA= new FirebaseRecyclerAdapter<testClass, testViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull testViewHolder viewHolder, int position, @NonNull testClass model) {
                final String post_key = getRef(position).getKey().toString();
                viewHolder.setName(model.getFoodName());
                viewHolder.setprice(model.getPrice());
                viewHolder.setPhoto(model.getImage());
                viewHolder.setTime(model.getEstTime());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,SimpleSingletest.class);
                        intent.putExtra("Post id",post_key);
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public testViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test1_row,parent,false);
                return new testViewHolder(view);
            }


        };
        FRA.startListening();
        test1.setAdapter(FRA);


    }

    public static class testViewHolder extends RecyclerView.ViewHolder{


        View mview;
        testViewHolder(View itemView)
        {
            super(itemView);
            mview =itemView;
        }
        public void setName(String name)
        {
            TextView textView_name = mview.findViewById(R.id.fname);
            textView_name.setText(name);
        }
        public void setprice(String price)
        {
            TextView textView_price = mview.findViewById(R.id.price);
            textView_price.setText(price);
        }
        public void setPhoto(String fphoto)
        {
            ImageView imageView_fphoto = (ImageView) mview.findViewById(R.id.fphoto);
            Picasso.get().load(fphoto).into(imageView_fphoto);

        }
        public void setTime(String Desc)
        {
            TextView textView_time = mview.findViewById(R.id.estTime);
            textView_time.setText(Desc);
        }




    }
}
