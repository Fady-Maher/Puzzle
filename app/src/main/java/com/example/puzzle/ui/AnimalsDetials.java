package com.example.puzzle.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static android.view.Gravity.START;
import com.bumptech.glide.Glide;
import com.example.puzzle.classes.AnimalsPuzzle;
import com.example.puzzle.adapter.OnClickListenerItemPuzzle;
import com.example.puzzle.R;
import com.example.puzzle.adapter.RecyclePuzzleAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimalsDetials extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView=null;
    ArrayList<AnimalsPuzzle>animalsPuzzles=null;
    RecyclePuzzleAdapter recyclePuzzleAdapter=null;
    ImageView image_mammel;
    android.app.Dialog dialog_q;
    Button btn_puzzle,btn_vet;
    String name_mammel;
    FirebaseAuth mAuth;
    CircleImageView profileCircleImageView;
    TextView textViewUser;
    NavigationView navigationView;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount acct=null;
    DrawerLayout drawer;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences1;
    android.app.Dialog dialog;
    String name;
    int [] image ={R.drawable.amphibians, R.drawable.birds, R.drawable.fish, R.drawable.insects, R.drawable.invertebrates, R.drawable.mammal,R.drawable.reptiles};
    String [] names ={"amphibians","birds", "fish", "insect", "invertebrates", "mammal","reptiles"};

    String []fish={"bass","carp","catfish","chub","clam","crab","crayfish","dogfish","dolphin","haddock","herring","lobster","pike","piranha","seahorse","sole","stingray","tuna"};
    int []fish_n={R.drawable.bass,R.drawable.carp,R.drawable.catfish,R.drawable.chub,R.drawable.clam,R.drawable.crab,R.drawable.crayfish,R.drawable.dogfish,R.drawable.dolphin,R.drawable.haddock,R.drawable.herring,R.drawable.lobster,R.drawable.pike,R.drawable.piranha,R.drawable.seahorse,R.drawable.sole,R.drawable.stingray,R.drawable.tuna};

    String [] invertebrates={"octopus","scorpion","seawasp","slug","starfish","worm"};
    int []invertebrates_n={R.drawable.octopus,R.drawable.scorpion,R.drawable.seawasp,R.drawable.slug,R.drawable.starfish,R.drawable.worm};

    String [] amphibians={"frog","toad"};
    int []amphibians_n={R.drawable.frog,R.drawable.toad};

    String []reptiles={"pitviper","seasnake","slowworm","tortoise","tuatara"};
    int [] reptiles_n={R.drawable.pitviper,R.drawable.seasnake,R.drawable.slowworm,R.drawable.tortoise,R.drawable.tuatara};

    String []birds={"chicken","crow","dove","duck","flamingo","gull","hawk","kiwi","ladybird","lark","ostrich","parakeet","penguin","pheasant","rhea","skimmer","skua","sparrow","swan","vulture","wren"};
    int [] birds_n={R.drawable.chicken,R.drawable.crow,R.drawable.dove,R.drawable.duck,R.drawable.flamingo,R.drawable.gull,R.drawable.hawk,R.drawable.kiwi,R.drawable.ladybird,R.drawable.lark,R.drawable.ostrich,R.drawable.parakeet,R.drawable.penguin,R.drawable.pheasant,R.drawable.rhea,R.drawable.skimmer,R.drawable.skua,R.drawable.sparrow,R.drawable.swan,R.drawable.vulture,R.drawable.wren};

    String []mammal={"aardvark","antelope","bear","boar","buffalo","calf","cavy","cheetah","deer",
            "elephant","fruitbat","giraffe","goat","gorilla","hamster","hare","leopard","lion","lynx",
            "mink","mole","mongoose","opossum","oryx","platypus","polecat","pony","porpoise","puma","pussycat",
            "raccoon","reindeer","seal","sealion","squirrel","vole","wallaby","wolf"};
    int [] mammal_n={R.drawable.aardvark,R.drawable.antelope,R.drawable.bear,R.drawable.boar,R.drawable.buffalo,
            R.drawable.calf,R.drawable.cavy,R.drawable.cheetah,R.drawable.deer,R.drawable.elephant,R.drawable.fruitbat,
            R.drawable.giraffe,R.drawable.goat,R.drawable.gorilla,R.drawable.hamster,R.drawable.hare,R.drawable.leopard,
            R.drawable.lion,R.drawable.lynx,R.drawable.mink,R.drawable.mole,R.drawable.mongoose,R.drawable.opossum,R.drawable.oryx,
            R.drawable.platypus,R.drawable.polecat,R.drawable.pony,R.drawable.porpoise,R.drawable.puma,R.drawable.pussycat,
            R.drawable.raccoon,R.drawable.reindeer
            ,R.drawable.seal,R.drawable.sealion,R.drawable.squirrel,R.drawable.vole,R.drawable.wallaby,R.drawable.wolf};

    String []insects={"flea","gnat","honeybee","housefly","moth","termite","wasp"};
    int [] insects_n={R.drawable.flea,R.drawable.gnat,R.drawable.honeybee,R.drawable.housefly,
            R.drawable.moth,R.drawable.termite,R.drawable.wasp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animals_detials);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image_mammel=findViewById(R.id.image_mammel);

        profileCircleImageView=findViewById(R.id.profileCircleImageView);
        textViewUser=findViewById(R.id.textViewUser);

        bottomNavigationView.setSelectedItemId(R.id.navigationHome);
        recyclerView=findViewById(R.id.rec_puzzle);
        animalsPuzzles=new ArrayList<>();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        acct = GoogleSignIn.getLastSignedInAccount(this);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            name=mAuth.getCurrentUser().getDisplayName();
            Uri uri=mAuth.getCurrentUser().getPhotoUrl();
            textViewUser.setText("Hello "+name);
            if(uri==null){
                profileCircleImageView.setImageResource(R.drawable.user_defult);
            }
            else {
                Glide.with(this).load(uri).into(profileCircleImageView);
            }
        }



        Intent intent= getIntent();
         name_mammel = intent.getStringExtra("name");
        int image_int_anim=intent.getIntExtra("image",0);
        image_mammel.setImageResource(image_int_anim);
        if(name_mammel.equals("fish")){
            for(int i=0;i<fish.length;i++){
                animalsPuzzles.add(new AnimalsPuzzle(fish_n[i],fish[i]));
            }

        }else if(name_mammel.equals("invertebrates")){
            for(int i=0;i<invertebrates.length;i++){
                animalsPuzzles.add(new AnimalsPuzzle(invertebrates_n[i],invertebrates[i]));
            }

        }else if(name_mammel.equals("amphibians")){
            for(int i=0;i<amphibians.length;i++){
                animalsPuzzles.add(new AnimalsPuzzle(amphibians_n[i],amphibians[i]));
            }
        }else if(name_mammel.equals("reptiles")){
            for(int i=0;i<reptiles.length;i++){
                animalsPuzzles.add(new AnimalsPuzzle(reptiles_n[i],reptiles[i]));
            }

        }else if(name_mammel.equals("birds")){
            for(int i=0;i<birds.length;i++){
                animalsPuzzles.add(new AnimalsPuzzle(birds_n[i],birds[i]));
            }

        }else if(name_mammel.equals("insects")){
            for(int i=0;i<insects.length;i++){
                animalsPuzzles.add(new AnimalsPuzzle(insects_n[i],insects[i]));
            }

        }
        else if(name_mammel.equals("mammal")){
            for(int i=0;i<mammal.length;i++){
                animalsPuzzles.add(new AnimalsPuzzle(mammal_n[i],mammal[i]));
            }

        }

        recyclePuzzleAdapter=new RecyclePuzzleAdapter(animalsPuzzles,this);
        recyclerView.setAdapter(recyclePuzzleAdapter);
        recyclePuzzleAdapter.setOnClickListenerItem(new OnClickListenerItemPuzzle() {
            @Override
            public void onClick(AnimalsPuzzle position) {

                showDialog(position);
            }
        });

    }

    private void showDialog(AnimalsPuzzle position) {
        dialog_q =new android.app.Dialog(AnimalsDetials.this);
        dialog_q.setContentView(R.layout.details_name);
        dialog_q.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window= dialog_q.getWindow();
        window.setGravity(Gravity.CENTER);
        btn_puzzle=dialog_q.findViewById(R.id.btn_puzzle);
        btn_vet=dialog_q.findViewById(R.id.btn_vet);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        dialog_q.show();

        btn_puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ActivityPuzzle.class);
                intent.putExtra("img_animal",position.animal_img);
                intent.putExtra("name_animal",position.animal_name);
                startActivity(intent);
                dialog_q.dismiss();
            }
        });
        btn_vet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Dialog.class);
                intent.putExtra("name_mammel",name_mammel);
                Log.e("name ",name_mammel);
                intent.putExtra("img_animal",position.animal_img);
                intent.putExtra("name_animal",position.animal_name);
                intent.putExtra("user_name",name);
                startActivity(intent);
                dialog_q.dismiss();
            }
        });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigationMyProfile:
                    Intent intent=new Intent(getApplicationContext(), User.class);
                    startActivity(intent);
                    break;
                case R.id.navigationHome:
                    break;
                case  R.id.navigationMenu:
                    drawer.openDrawer(GravityCompat.START);
                   break;
            }
            return true;
        }
    };



    private void signOut() {




        if (acct != null) {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
        }
        sharedPreferences1=getSharedPreferences("email_login",MODE_PRIVATE);
        editor=sharedPreferences1.edit();
        FirebaseAuth.getInstance().signOut();
        editor.putBoolean("login",false);
        editor.apply();
        Intent intent=new Intent(AnimalsDetials.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            Toast.makeText(getApplicationContext(),"User account sign out.",Toast.LENGTH_SHORT).show();


    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.nav_logout:
                signOut();
                break;
            case R.id.nav_about:
                Intent intent2 = new Intent(AnimalsDetials.this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_contact_us:
                dialog =new android.app.Dialog(this);
                dialog.setContentView(R.layout.contact_us_layout);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window= dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
                Button face = dialog.findViewById(R.id.facebook);
                Button gog = dialog.findViewById(R.id.tele);
                dialog.show();
                face.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent browse=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hossam.elfnan.1"));
                        startActivity(browse);
                        dialog.dismiss();

                    }
                });

                gog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO , Uri.parse("mailto:" + "fadymaher263@gmail.com"));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Contact Owner");
                        startActivity(emailIntent);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.nav_home:
                break;
            case R.id.nav_settings:
                Intent settingIntent=new Intent();
                settingIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",getPackageName(),null);
                settingIntent.setData(uri);
                startActivity(settingIntent);
                break;
            case R.id.nav_share_app:
                shareApp();
                break;
            case R.id.nav_feedback:
                Intent intent = new Intent(AnimalsDetials.this, FeedbackActivity.class);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(START);
        return true;
    }

    private void shareApp() {
        int applicationNameId = getApplicationContext().getApplicationInfo().labelRes;
        final String appPackageName = getApplicationContext().getPackageName();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, getApplicationContext().getString(applicationNameId));
        String text = "Install this Animal’s World App : ";
        String link = "https://www.mediafire.com/file/1n5kfu64fkrpb4o/app-debug.apk/file?=id" + appPackageName;
        i.putExtra(Intent.EXTRA_TEXT, text + " " + link);
        startActivity(Intent.createChooser(i, "Share link:"));
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if(navigationView.getCheckedItem()!=null){
            navigationView.getCheckedItem().setChecked(false);
        }
        else if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(START);
            bottomNavigationView.setSelectedItemId(R.id.navigationHome);
        }else{
            finish();
        }
    }
}


