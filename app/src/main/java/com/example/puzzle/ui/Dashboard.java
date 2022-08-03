package com.example.puzzle.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.puzzle.adapter.OnClickListenerItem;
import com.example.puzzle.R;
import com.example.puzzle.adapter.RecycleAnimalsAdapter;
import com.example.puzzle.classes.Animals;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    RecycleAnimalsAdapter recycleAnimalsAdapter = null;
    ArrayList<Animals> animals = null;
    private long backPressedTime;
    private Toast back;
    int count = 0;

    int[] image = {R.drawable.amphibians, R.drawable.birds, R.drawable.fish, R.drawable.insects, R.drawable.invertebrates, R.drawable.mammal, R.drawable.reptiles};
    String[] names = {"amphibians", "birds", "fish", "insects", "invertebrates", "mammal", "reptiles"};

    String[] fish = {"bass", "carp", "catfish", "chub", "clam", "crab", "crayfish", "dogfish", "dolphin", "haddock", "herring", "lobster", "pike", "piranha", "seahorse", "sole", "stingray", "tuna"};
    int[] fish_n = {R.drawable.bass, R.drawable.carp, R.drawable.catfish, R.drawable.chub, R.drawable.clam, R.drawable.crab, R.drawable.crayfish, R.drawable.dogfish, R.drawable.dolphin, R.drawable.haddock, R.drawable.herring, R.drawable.lobster, R.drawable.pike, R.drawable.piranha, R.drawable.seahorse, R.drawable.sole, R.drawable.stingray, R.drawable.tuna};

    String[] invertebrates = {"octopus", "scorpion", "seawasp", "slug", "starfish", "worm"};
    int[] invertebrates_n = {R.drawable.octopus, R.drawable.scorpion, R.drawable.seawasp, R.drawable.slug, R.drawable.starfish, R.drawable.worm};

    String[] amphibians = {"frog", "toad"};
    int[] amphibians_n = {R.drawable.frog, R.drawable.toad};

    String[] reptiles = {"pitviper", "seasnake", "slowworm", "tortoise", "tuatara"};
    int[] reptiles_n = {R.drawable.pitviper, R.drawable.seasnake, R.drawable.slowworm, R.drawable.tortoise, R.drawable.tuatara};

    String[] birds = {"chicken", "crow", "dove", "duck", "flamingo", "gull", "hawk", "kiwi", "ladybird", "lark", "ostrich", "parakeet", "penguin", "pheasant", "rhea", "skimmer", "skua", "sparrow", "swan", "vulture", "wren"};
    int[] birds_n = {R.drawable.chicken, R.drawable.crow, R.drawable.dove, R.drawable.duck, R.drawable.flamingo, R.drawable.gull, R.drawable.hawk, R.drawable.kiwi, R.drawable.ladybird, R.drawable.lark, R.drawable.ostrich, R.drawable.parakeet, R.drawable.penguin, R.drawable.pheasant, R.drawable.rhea, R.drawable.skimmer, R.drawable.skua, R.drawable.sparrow, R.drawable.swan, R.drawable.vulture, R.drawable.wren};

    String[] mammal = {"aardvark", "antelope", "bear", "boar", "buffalo", "calf", "cavy", "cheetah", "deer", "elephant", "fruitbat", "giraffe", "goat", "gorilla", "hamster", "hare", "leopard", "lion", "lynx", "mink", "mole", "mongoose", "opossum", "oryx", "platypus", "polecat", "pony", "porpoise", "puma", "pussycat", "raccoon", "reindeer", "seal", "sealion", "squirrel", "vole", "wallaby", "wolf"};
    int[] mammal_n = {R.drawable.aardvark, R.drawable.antelope, R.drawable.bear, R.drawable.boar, R.drawable.buffalo, R.drawable.calf, R.drawable.cavy, R.drawable.cheetah, R.drawable.deer, R.drawable.elephant, R.drawable.fruitbat, R.drawable.giraffe, R.drawable.goat, R.drawable.gorilla, R.drawable.hamster, R.drawable.hare, R.drawable.leopard, R.drawable.lion, R.drawable.lynx, R.drawable.mink, R.drawable.mole, R.drawable.mongoose, R.drawable.opossum, R.drawable.oryx, R.drawable.platypus, R.drawable.polecat, R.drawable.pony, R.drawable.porpoise, R.drawable.puma, R.drawable.pussycat, R.drawable.raccoon, R.drawable.reindeer
            , R.drawable.seal, R.drawable.sealion, R.drawable.squirrel, R.drawable.vole, R.drawable.wallaby, R.drawable.wolf};

    String[] insects = {"flea", "gnat", "honeybee", "housefly", "moth", "termite", "wasp"};
    int[] insects_n = {R.drawable.flea, R.drawable.gnat, R.drawable.honeybee, R.drawable.housefly, R.drawable.moth, R.drawable.termite, R.drawable.wasp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        animals = new ArrayList<>();


        for (int i = 0; i < image.length; i++) {
            if (names[i].equals("fish")) {
                count = fish.length;
            } else if (names[i].equals("invertebrates")) {
                count = invertebrates.length;
            } else if (names[i].equals("amphibians")) {
                count = amphibians.length;
            } else if (names[i].equals("reptiles")) {
                count = reptiles.length;
            } else if (names[i].equals("birds")) {
                count = birds.length;
            } else if (names[i].equals("insects")) {
                count = insects.length;
            } else if (names[i].equals("mammal")) {
                count = mammal.length;
            }
            animals.add(new Animals(image[i], names[i], count + ""));
        }


        recycleAnimalsAdapter = new RecycleAnimalsAdapter(animals, this);
        recyclerView = findViewById(R.id.rv_animals_type);
        recyclerView.setAdapter(recycleAnimalsAdapter);
        recycleAnimalsAdapter.setOnClickListenerItem(new OnClickListenerItem() {
            @Override
            public void onClick(Animals position) {
                Intent intent = new Intent(getApplicationContext(), AnimalsDetials.class);
                intent.putExtra("image", position.animal_img);
                intent.putExtra("name", position.animal_name);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            back.cancel();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            super.onBackPressed();
            return;
        } else {
            back = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_LONG);
            back.show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}