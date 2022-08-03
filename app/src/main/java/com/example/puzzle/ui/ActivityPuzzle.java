package com.example.puzzle.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.puzzle.classes.ID;
import com.example.puzzle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityPuzzle extends AppCompatActivity {

    ImageView imageView, img_animal_qes;
    Button btn_yes, btn_no;
    TextView txt_animal_qes, txt_name_of_animal_act, txt_score_act, txt_score, txt_correct, txt_wrong;
    Boolean flag;
    int k = 0, count = 5;
    ArrayList<String> ANS = new ArrayList() {
    };
    ArrayList<String> features = new ArrayList() {
    };
    RatingBar rat_bar;
    Map<String, Integer> features_n = new HashMap<>();
    android.app.Dialog dialog, dialog2;
    Map<String, String> features_qes = new HashMap<>();
    int rating = 5, score_qes = 1, count_level = 1;

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    String id;
    private static final int SHORT_DELAY = 5;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_puzzle);

//       mp = MediaPlayer.create(ActivityPuzzle.this, R.raw.bomb);
        txt_wrong = findViewById(R.id.txt_wrong);
        txt_correct = findViewById(R.id.txt_correct);
        txt_name_of_animal_act = findViewById(R.id.txt_name_of_animal_act);
        txt_score_act = findViewById(R.id.txt_score_act);
        txt_score = findViewById(R.id.txt_score);
        rat_bar = findViewById(R.id.rat_bar);
        imageView = findViewById(R.id.animal_img_choose);
        txt_animal_qes = findViewById(R.id.txt_animal_qes);
        btn_yes = findViewById(R.id.btn_puzzle_yes);
        btn_no = findViewById(R.id.btn_puzzle_no);
        img_animal_qes = findViewById(R.id.img_animal_qes);
        Intent intent = getIntent();
        int image_int_anim = intent.getIntExtra("img_animal", 0);
        String name_animal_ = intent.getStringExtra("name_animal");
        imageView.setImageResource(image_int_anim);

        txt_name_of_animal_act.setText(name_animal_);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Animals").child(mAuth.getCurrentUser().getUid());

        features_n.put("tail", R.drawable.tail);
        features_n.put("legs", R.drawable.legs);
        features_n.put("toothed", R.drawable.toothed);
        features_n.put("predator", R.drawable.predator);
        features_n.put("fins", R.drawable.fins);
        features_n.put("venomous", R.drawable.venomous);
        features_n.put("breathes", R.drawable.breathes);
        features_n.put("backbone", R.drawable.backbone);
        features_n.put("eggs", R.drawable.eggs);
        features_n.put("feathers", R.drawable.feathers);
        features_n.put("hair", R.drawable.hair);
        features_n.put("aquatic", R.drawable.aquatic);
        features_n.put("airborne", R.drawable.airborne);
        features_n.put("milk", R.drawable.milk);
        features_n.put("domestic", R.drawable.domestic);

        features_qes.put("tail", "Does this animal have a tail?");
        features_qes.put("legs", "Does this animal have a legs?");
        features_qes.put("toothed", "Does this animal have a toothed?");
        features_qes.put("predator", "Does this animal have a predator?");
        features_qes.put("fins", "Does this animal have a fins?");
        features_qes.put("venomous", "Does this animal have a venomous?");
        features_qes.put("breathes", "Does this animal have a breathes?");
        features_qes.put("backbone", "Does this animal have a backbone?");
        features_qes.put("eggs", "Does this animal have an eggs?");
        features_qes.put("feathers", "Does this animal have a feathers?");
        features_qes.put("hair", "Does this animal have a hair?");
        features_qes.put("aquatic", "Does this animal have an aquatic?");
        features_qes.put("airborne", "Does this animal have an airborne?");
        features_qes.put("milk", "Does the this have a milk?");
        features_qes.put("domestic", "Does this animal have a domestic?");


        Map<String, String[]> dictionary = new HashMap<>();
        dictionary.put("domestic", new String[]{"calf", "crap", "cavy", "chicken", "dove", "goat", "hamster", "honeybee", "parakeet", "pony", "pussycat", "reindeer"});
        dictionary.put("backbone", new String[]{"aardavrk", "antelope", "bass", "bear", "boar", "buffalo", "calf", "carp", "catfish", "cavy", "cheetah", "chicken", "crow", "deer", "dogfish", "dolphin", "dove", "duck", "elephant", "flamingo", "frog", "fruitbat", "giraffe", "gnat", "goat", "gorilla", "gull", "haddock", "hamster", "hare", "hawk", "herring", "kiwi", "lark", "leopard", "lion", "lynx", "mink", "mole", "mongoose", "newt", "opossum", "oryx", "ostrich", "parakeet", "penguin", "pheasant", "pike", "piranha", "pitviper", "platypus", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "rhea", "seahorse", "seal", "sealion", "seasnake", "skimmer", "skua", "slowworm", "sole", "sparrow", "squirrel", "stingray", "swan", "toad", "tortoise", "tuatara", "tuna", "vampire", "vole", "vulture", "wallaby", "wolf", "wren"});
        dictionary.put("breathes", new String[]{"aardavrk", "antelope", "bear", "boar", "buffalo", "claf", "cavy", "cheetah", "chicken", "crow", "deer", "dolphin", "dove", "duck", "elephant", "flamingo", "flea", "frog", "fruitbat", "giraffe", "gnat", "goat", "gorilla", "gull", "hamster", "hare,hawk", "honeybee", "housefly", "kiwi", "ladybird", "lark", "leopard", "lion", "lynx", "mink", "mole", "mongoose", "moth", "newt", "opossum", "oryx", "ostrich", "parakeet", "penguin", "pheasant", "pitviper", "platypus", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "rhea", "scorpion", "seal", "sealion", "skimmer", "skua", "slowworm", "slug", "sparrow", "squirrel", "swan", "termite", "toad", "tortoise", "tuatara", "vampire", "vole", "vulture", "wallaby", "wasp", "wolf", "worm", "wren"});
        dictionary.put("venomous", new String[]{"frog", "honeybee", "pitviper", "scorpion", "seasnake", "seawasp", "stingray", "wasp"});
        dictionary.put("fins", new String[]{"bass", "carp", "catfish", "chub", "dogfish", "dolphin", "haddock", "herring", "pike", "piranha", "porpoise", "seahorse", "seal", "sealion", "sole", "stingray", "tuna"});
        dictionary.put("predator", new String[]{"aardvark", "bass", "bear", "boar", "catfish", "cheetah", "chub", "clam", "crab", "crayfish", "crow", "dogfish", "dolphin", "frog", "gull", "hawk", "herring", "kiwi", "ladybird", "leopard", "lion", "lobster", "lynx", "mink", "mole", "mongoose", "newt", "octopus", "opossum", "penguin", "pike", "piranha", "pitviper", "platypus", "polecat", "porpoise", "pum", "pussycat", "raccoon", "rhea", "scorpion", "seal", "sealion", "seasnake", "seawasp", "skimmer", "skua", "slowworm", "starfish", "stingray", "tuatara", "tuna", "vulture", "wolf"});
        dictionary.put("toothed", new String[]{"aardvark", "antelope", "bass", "bear", "boar", "buffalo", "calf", "carp", "catfish", "cavy", "cheetah", "chub", "deer", "dogfish", "dolphin", "elephant", "frog", "fruitbat", "giraffe", "goat", "gorilla", "haddock", "hamster", "hare", "herring", "leopard", "lion", "lynx", "mink", "mole", "mongoose", "newt", "opossum", "oryx", "pike", "piranha", "pitviper", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "seahorse", "seal", "sealion", "seasnake", "slowworm", "sole", "squirrel", "stingray", "toad", "tuatara", "tuna", "vampirebat", "vole", "wallaby", "wolf"});
        dictionary.put("legs", new String[]{"aardvark", "antelope", "bear", "boar", "buffalo", "calf", "cavy", "cheetah", "chicken", "crab", "crayfish", "crow", "deer", "dove", "duck", "elephant", "flamingo", "flea", "frog", "fruitbat", "giraffe", "gnat", "goat", "gorilla", "gull", "hamster", "hare", "hawk", "honeybee", "housefly", "kiwi", "ladybird", "lark", "leopard", "lion", "lobster", "lynx", "mink", "mole", "mongoose", "moth", "newt", "octopus", "opossum", "oryx", "ostrich", "parakeet", "penguin", "pheasant", "platypus", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "rhea", "scorpion", "seahorse", "seal", "sealion", "seasnake", "seawasp", "skimmer", "skua", "slowworm", "slug", "sole", "sparrow", "squirrel", "starfish", "stingray", "swan", "termite", "toad", "tortoise", "tuatara", "vole", "vulture", "wallaby", "wasp", "wolf", "wren"});
        dictionary.put("tail", new String[]{"antelope", "bass", "bear", "boar", "buffalo", "calf", "carp", "catfish", "cavy", "cheetah", "cat", "chicken", "chub", "clam", "crab", "crayfish", "crow", "deer", "dogfish", "dolphin", "dove", "duck", "elephant", "flamingo", "flea", "frog", "frog", "fruitbat", "giraffe", "gnat", "goat", "gorilla", "gull", "haddock", "hamster", "hare", "hawk", "herring", "honeybee", "housefly", "kiwi", "ladybird", "lark", "leopard", "lion", "lobster", "lynx", "mink", "mole", "mongoose", "moth", "newt", "octopus", "opossum", "oryx", "ostrich", "parakeet", "penguin", "pheasant", "pike", "piranha", "pitviper", "platypus", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "rhea", "scorpion", "seahorse", "seal", "sealion", "seasnake", "seawasp", "skimmer", "skua", "slowworm", "slug", "sole", "sparrow", "squirrel", "starfish", "stingray", "swan", "termite", "toad", "tortoise", "tuatara", "tuna", "vole", "vulture", "wallaby", "wasp", "wolf", "worm", "wren"});
        dictionary.put("milk", new String[]{"aardvark", "antelope", "bear", "boar", "buffalo", "calf", "cavy", "cheetah", "deer", "dolphin", "elephant", "fruitbat", "giraffe", "goat", "gorilla", "hamster", "hare", "leopard", "lion", "lynx", "mink", "mole", "mongoose", "opossum", "oryx", "platypus", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "seal", "sealion", "squirrel", "vampire", "vole", "wallaby", "wolf"});
        dictionary.put("airborne", new String[]{"chicken", "crow", "dove", "duck", "flamingo", "fruitbat", "gnat", "gull", "hawk", "honeybee", "housefly", "ladybird", "lark", "moth", "parakeet", "pheasant", "skimmer", "skua", "sparrow", "swan", "vampire", "vulture", "wasp", "wren"});
        dictionary.put("aquatic", new String[]{"bass", "carp", "catfish", "chub", "crab", "crayfish", "dogfish", "dolphin", "duck", "frog", "gull", "haddock", "herring", "lobster", "mink", "newt", "octopus", "penguin", "pike", "piranha", "platypus", "porpoise", "seahorse", "seal", "sealion", "seasnake", "seawasp", "skimmer", "skua", "sole", "starfish", "stingray", "swan", "toad", "tuna"});
        dictionary.put("hair", new String[]{"aardvark", "antelope", "bear", "boar", "buffalo", "calf", "cavy", "cheetah", "deer", "elephant", "fruitbat", "giraffe", "goat", "gorilla", "hamster", "hare", "honeybee", "housefly", "leopard", "lion", "lynx", "mink", "mole", "mongoose", "moth", "opossum", "oryx", "platypus", "polecat", "pony", "puma", "pussycat", "raccoon", "reindeer", "seal", "sealion", "squirrel", "vampire", "vole", "wallaby", "wasp", "wolf"});
        dictionary.put("feathers", new String[]{"chickencrow", "dove", "duck", "flamingo", "gull", "hawk", "kiwi", "lark", "ostrich", "parakeet", "penguin", "pheasant", "rhea", "skimmer", "skua", "sparrow", "swan", "vulture", "wren"});
        dictionary.put("eggs", new String[]{"bass", "carp", "catfish", "chicken", "chub", "clam", "crab", "crayfish", "crow", "dogfish", "dove", "duck", "flamingo", "flea", "frog", "frog", "gnat", "gull", "haddock", "hawk", "herring", "honeybee", "housefly", "kiwi", "ladybird", "lark", "lobster", "moth", "newt", "octopus", "ostrich", "parakeet", "penguin", "pheasant", "pike", "piranha", "pitviper", "platypus", "rhea", "seahorse", "seawasp", "skimmer", "skua", "slowworm", "slug", "sole", "sparrow", "starfish", "stingray", "swan", "termite", "toad", "tortoise", "tuatara", "tuna", "vulture", "wasp", "worm", "wren"});

        Boolean flag1 = true;
        for (String key : dictionary.keySet()) {
            flag1 = true;
            for (int k = 0; k < dictionary.get(key).length; k++) {
                if (name_animal_.equals(dictionary.get(key)[k])) {
                    flag1 = false;
                    ANS.add("True");
                }
            }
            if (flag1) {
                ANS.add("False");
            }
            features.add(key);
            Log.e("key ", key);
        }
        txt_animal_qes.setText(features_qes.get(features.get(0)));
        img_animal_qes.setImageResource(features_n.get(features.get(0)));
        // Log.e(" ghhhh", ANS.get(0) +""+ANS.get(1)+ANS.get(2)+ANS.get(3) +""+ANS.get(4)+ANS.get(5)+ANS.get(6) +""+ANS.get(7)+ANS.get(8));
        //Log.e(" ghhhh", features.get(0) +""+features.get(1)+features.get(2)+features.get(3) +""+features.get(4)+features.get(5)+features.get(6) +""+features.get(7)+features.get(8));


        btn_yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "WrongConstant"})
            @Override
            public void onClick(View v) {
//        mp.start();
                flag = true;
                txt_score_act.setText(++score_qes + "/15");
                count_level++;
                if (count_level == 5) {
                    txt_score.setText("Level 2");
                } else if (count_level == 10) {
                    txt_score.setText("Level 3");
                }
                // Toast.makeText(ActivityPuzzle.this, "Yes Pressed", Toast.LENGTH_SHORT).show();
                while (flag) {

                    flag = false;
                    if (k < 15) {
                        if (ANS.get(k).equals("True")) {
                            count++;
                            txt_correct.setVisibility(View.VISIBLE);
                            txt_wrong.setVisibility(View.GONE);

                        } else {
                            txt_correct.setVisibility(View.GONE);
                            txt_wrong.setVisibility(View.VISIBLE);
                            count--;
                            rating--;
                            if (rating > 0) {
                                rat_bar.setRating(rating);
                            } else {
                                rat_bar.setRating(rating);
                                showDialogQuestion2();
                                new android.os.Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog2.dismiss();
                                        Intent intent2 = new Intent(ActivityPuzzle.this, Dashboard.class);
                                        startActivity(intent2);

                                    }
                                }, 3000);
                            }
                        }

                        ++k;
                        if (k == 15) {
                            txt_score_act.setText("15/15");
                            showDialogQuestion();
                            new android.os.Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    Intent intent2 = new Intent(ActivityPuzzle.this, Dashboard.class);
                                    startActivity(intent2);
                                }
                            }, 3000);

                            break;
                        }

                        txt_animal_qes.setText(features_qes.get(features.get(k)));
                        img_animal_qes.setImageResource(features_n.get(features.get(k)));

                    }

                    Log.e("lol", " " + k);

                }
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "WrongConstant"})
            @Override
            public void onClick(View v) {
//            mp.start();
                flag = true;
                txt_score_act.setText(++score_qes + "/15");
                count_level++;
                if (count_level == 6) {
                    txt_score.setText("Level 2");
                } else if (count_level == 11) {
                    txt_score.setText("Level 3");
                }
                // Toast.makeText(ActivityPuzzle.this, "No Pressed", Toast.LENGTH_SHORT).show();
                while (flag) {

                    flag = false;
                    if (k < 15) {
                        if (ANS.get(k).equals("False")) {
                            count++;
                            txt_correct.setVisibility(View.VISIBLE);
                            txt_wrong.setVisibility(View.GONE);
                        } else {
                            txt_correct.setVisibility(View.GONE);
                            txt_wrong.setVisibility(View.VISIBLE);
                            count--;
                            rating--;

                            if (rating > 0) {
                                rat_bar.setRating(rating);
                            } else {
                                rat_bar.setRating(rating);
                                showDialogQuestion2();
                                new android.os.Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog2.dismiss();
                                        Intent intent2 = new Intent(ActivityPuzzle.this, Dashboard.class);
                                        startActivity(intent2);
                                    }
                                }, 3000);

                            }

                        }
                        ++k;
                        if (k == 15) {
                            txt_score_act.setText("15/15");
                            showDialogQuestion();
                            new android.os.Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();

                                    Intent intent2 = new Intent(ActivityPuzzle.this, Dashboard.class);
                                    startActivity(intent2);
                                }
                            }, 3000);

                            break;
                        }
                        txt_animal_qes.setText(features_qes.get(features.get(k)));
                        img_animal_qes.setImageResource(features_n.get(features.get(k)));

                    }

                    Log.e("lol", " " + k);

                }

            }
        });

    }

    private void showDialogQuestion() {
        dialog = new android.app.Dialog(this);
        dialog.setContentView(R.layout.final_puzzle);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();


        ID id_rating = new ID();
        id_rating.setId(rating + "");
        id = mDatabase.push().getKey();
        mDatabase.child(id).setValue(id_rating).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else {
                }
            }
        });
    }

    private void showDialogQuestion2() {
        dialog2 = new android.app.Dialog(this);
        dialog2.setContentView(R.layout.losefinal);
        dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog2.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog2.setCancelable(false);
        // status_qu_feel=(GifAnimationMetaData) dialog.findViewById(R.id.status_qu_feel);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog2.show();
    }
}



