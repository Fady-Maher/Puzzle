package com.example.puzzle.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.puzzle.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Dialog extends AppCompatActivity {
    android.app.Dialog dialog, dailog_sad;
    Button btn_yes, btn_no;
    TextView questionTextView;
    TextToSpeech mTTS;
    String name = "", QES = "Does it suffer from ", key = "";
    boolean flag = true, flag2 = true;
    int count = 0, countflag = 0, img_animal;
    Map<String, String[]> map = new HashMap<>();
    Map<String, String> mapprolog = new HashMap<>();
    String[] arraykeys;
    ArrayList<String> arrayList = new ArrayList<>();
    PyObject main_program;
    String name_mammel, name_animal, user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));//error is here!
        }

        final Python py = Python.getInstance();
        main_program = py.getModule("prolog");

        Intent intent = getIntent();
        name_mammel = intent.getStringExtra("name_mammel");
        img_animal = intent.getIntExtra("img_animal", 0);
        name_animal = intent.getStringExtra("name_animal");
        user_name = intent.getStringExtra("user_name");

        if (name_mammel.equals("fish")) {
            map.put("fever",
                    new String[]{"fever", "headache", "nausea", "vomiting", "agitation", "anxiety", "confusion", "hyperactivity"});
            map.put("ulcers_on_body",
                    new String[]{"ulcers_on_body", "nodules_in_internal", "organs_fin_and_tail_rot", "loss_of_appetite", "loss_of_weight"});
            arraykeys = new String[]{"fever", "ulcers_on_body"};

            mapprolog.put("fever", "[fever,headache,nausea,vomiting,agitation,anxiety,confusion,hyperactivity]");
            mapprolog.put("ulcers_on_body", "[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight]");

        } else if (name_mammel.equals("invertebrates")) {
            map.put("ulcers_on_body",
                    new String[]{"ulcers_on_body", "nodules_in_internal", "organs_fin_and_tail_rot", "loss_of_appetite", "loss_of_weight"});
            arraykeys = new String[]{"ulcers_on_body"};

            mapprolog.put("ulcers_on_body", "[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight]");

        } else if (name_mammel.equals("amphibians")) {
            map.put("cough",
                    new String[]{"cough", "fever", "sore_throat", "muscle_pain", "headache", "shortness_of_breath"});
            map.put("high_fever",
                    new String[]{"high_fever", "weakness", "difficulty_standing", "vomiting", "diarrhea"});
            map.put("mouth_sores",
                    new String[]{"mouth_sores", "skin_rash", "fever", "sore_throat", "loss_of_appetite"});
            map.put("nausea",
                    new String[]{"nausea", "fever", "headache", "vomiting", "agitation", "anxiety", "confusion", "hyperactivity"});
            map.put("intense_headache",
                    new String[]{"intense_headache", "fever", "lymphadenopathy", "back_pain", "intense_asthenia"});
            map.put("ulcers_on_body",
                    new String[]{"ulcers_on_body", "nodules_in_internal", "organs_fin_and_tail_rot", "loss_of_appetite", "loss_of_weight"});
            map.put("difficulty_swallowing_and_breathing",
                    new String[]{"difficulty_swallowing_and_breathing", "fever,reddening_of_the_lining_of_the_mouth_and_nose", "swelling_of_the_lips_tongue_and_gums", "a_swollen_purple_coloured_tongue", "lameness"});

            arraykeys = new String[]{"cough", "high_fever", "mouth_sores", "nausea", "intense_headache", "ulcers_on_body", "difficulty_swallowing_and_breathing"};
            ;

            mapprolog.put("cough", "[cough,fever,sore_throat,muscle_pain,headache,shortness_of_breath]");
            mapprolog.put("high_fever", "[high_fever,weakness,difficulty_standing,vomiting,diarrhea]");
            mapprolog.put("mouth_sores", "[mouth_sores,skin_rash,fever,sore_throat,loss_of_appetite]");
            mapprolog.put("nausea", "[nausea,fever,headache,vomiting,agitation,anxiety,confusion,hyperactivity]");
            mapprolog.put("intense_headache", "[intense_headache,fever,lymphadenopathy,back_pain,intense_asthenia]");
            mapprolog.put("ulcers_on_body", "[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight]");
            mapprolog.put("difficulty_swallowing_and_breathing", "[difficulty_swallowing_and_breathing,fever,reddening_of_the_lining_of_the_mouth_and_nose,swelling_of_the_lips_tongue_and_gums,a_swollen_purple_coloured_tongue,lameness]");


        } else if (name_mammel.equals("reptiles")) {

            map.put("fever",
                    new String[]{"fever", "intense_headache", "lymphadenopathy", "back_pain", "intense_asthenia"});
            map.put("ulcers_on_body",
                    new String[]{"ulcers_on_body", "nodules_in_internal", "organs_fin_and_tail_rot", "loss_of_appetite", "loss_of_weight"});

            arraykeys = new String[]{"fever", "ulcers_on_body"};

            mapprolog.put("fever", "[fever,intense_headache,lymphadenopathy,back_pain,intense_asthenia]");
            mapprolog.put("ulcers_on_body", "[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight]");

        } else if (name_mammel.equals("birds")) {

            map.put("nasal_discharge",
                    new String[]{"nasal_discharge", "sneezing", "coughing", "watery_diarrhoea", "depression", "muscular_tremors", "drooping_wings", "complete_paralysis"});
            map.put("lack_of_energy_and_appetite",
                    new String[]{"lack_of_energy_and_appetite", "fever", "cough", "sneezing", "diarrhea", "decreased_egg_production", "purple_discoloration_of_the_wattles_and_combs_and_legs", "lack_of_coordination", "sudden_death"});
            map.put("shortness_of_breath",
                    new String[]{"shortness_of_breath", "cough", "fever", "sore_throat", "muscle_pain", "headache"});
            map.put("difficulty_standing",
                    new String[]{"difficulty_standing", "high_fever", "weakness", "vomiting", "diarrhea"});
            map.put("skin_rash",
                    new String[]{"skin_rash", "mouth_sores", "fever", "sore_throat", "loss_of_appetite"});
            map.put("nausea",
                    new String[]{"nausea", "fever", "headache", "vomiting", "agitation", "anxiety", "confusion", "hyperactivity"});
            map.put("reddening_of_the_lining_of_the_mouth_and_nose",
                    new String[]{"reddening_of_the_lining_of_the_mouth_and_nose", "fever", "swelling_of_the_lips_tongue_and_gums", "difficulty_swallowing_and_breathing", "a_swollen_purple_coloured_tongue", "lameness"});
            map.put("dehydration",
                    new String[]{"dehydration", "anorexia", "diarrhoea", "weakness", "high_mortality", "decreased_egg_production", "decreased_fertility_and_hatchability"});
            map.put("lesions",
                    new String[]{"lesions", "fever", "lameness", "loss_of_appetite", "lethargy", "recumbency", "crepitation", "difficulty_breathing", "tachycardia"});
            map.put("death_in_good_condition",
                    new String[]{"death_in_good_condition", "sudden_death", "depression", "fall_on_side", "paddling_of_legs"});
            map.put("ulcers_on_body",
                    new String[]{"ulcers_on_body", "nodules_in_internal", "organs_fin_and_tail_rot", "loss_of_appetite,loss_of_weight"});
            map.put("rhinitis",
                    new String[]{"rhinitis", "swollen_head", "breathing_difficulty", "drop_in_productivity", "loss_of_appetite", "bronchial_bleeding"});
            map.put("blood_around_the_nose",
                    new String[]{"blood_around_the_nose", "low_energy", "eating_less", "high_temperature", "sudden_death"});
            map.put("ruffled",
                    new String[]{"ruffled", "weakness", "weight_loss", "lack_of_appetite", "feathers_appearing", "droppings", "conjunctivitis"});


            arraykeys = new String[]{"nasal_discharge", "nasal_discharge", "shortness_of_breath", "difficulty_standing", "skin_rash",
                    "nausea", "reddening_of_the_lining_of_the_mouth_and_nose", "dehydration", "lesions", "death_in_good_condition",
                    "ulcers_on_body", "rhinitis", "blood_around_the_nose", "ruffled"};

            mapprolog.put("nasal_discharge", "[nasal_discharge,sneezing,coughing,watery_diarrhoea,depression,muscular_tremors,drooping_wings,complete_paralysis]");
            mapprolog.put("lack_of_energy_and_appetite", "[lack_of_energy_and_appetite,fever,cough,sneezing,diarrhea,decreased_egg_production,purple_discoloration_of_the_wattles_and_combs_and_legs,lack_of_coordination,sudden_death]");
            mapprolog.put("shortness_of_breath", "[shortness_of_breath,cough,fever,sore_throat,muscle_pain,headache]");
            mapprolog.put("difficulty_standing", "[difficulty_standing,high_fever,weakness,vomiting,diarrhea]");
            mapprolog.put("skin_rash", "[skin_rash,mouth_sores,fever,sore_throat,loss_of_appetite]");
            mapprolog.put("nausea", "[nausea,fever,headache,vomiting,agitation,anxiety,confusion,hyperactivity]");
            mapprolog.put("reddening_of_the_lining_of_the_mouth_and_nose", "[reddening_of_the_lining_of_the_mouth_and_nose,fever,swelling_of_the_lips_tongue_and_gums,difficulty_swallowing_and_breathing,a_swollen_purple_coloured_tongue,lameness]");
            mapprolog.put("dehydration", "[dehydration,anorexia,diarrhoea,weakness,high_mortality,decreased_egg_production,decreased_fertility_and_hatchability]");
            mapprolog.put("lesions", "[lesions,fever,lameness,loss_of_appetite,lethargy,recumbency,crepitation,difficulty_breathing,tachycardia]");
            mapprolog.put("death_in_good_condition", "[death_in_good_condition,sudden_death,depression,fall_on_side,paddling_of_legs]");
            mapprolog.put("ulcers_on_body", "[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight]");
            mapprolog.put("rhinitis", "[rhinitis,swollen_head,breathing_difficulty,drop_in_productivity,loss_of_appetite,bronchial_bleeding]");
            mapprolog.put("blood_around_the_nose", "[blood_around_the_nose,low_energy,eating_less,high_temperature,sudden_death]");
            mapprolog.put("ruffled", "[ruffled,weakness,weight_loss,lack_of_appetite,feathers_appearing,droppings,conjunctivitis]");


        } else if (name_mammel.equals("insects")) {

            map.put("high_fever",
                    new String[]{"high_fever", "weakness", "difficulty_standing", "vomiting", "diarrhea"});
            map.put("fever",
                    new String[]{"fever", "headache", "nausea", "vomiting", "agitation", "anxiety", "confusion", "hyperactivity"});
            map.put("blood_around_the_nose",
                    new String[]{"blood_around_the_nose", "low_energy", "eating_less", "high_temperature", "sudden_death"});
            map.put("ulcers_on_body",
                    new String[]{"ulcers_on_body", "nodules_in_internal", "organs_fin_and_tail_rot", "loss_of_appetite", "loss_of_weight"});

            arraykeys = new String[]{"high_fever", "fever", "blood_around_the_nose", "ulcers_on_body"};
            ;

            mapprolog.put("high_fever", "[high_fever,weakness,difficulty_standing,vomiting,diarrhea]");
            mapprolog.put("fever", "[fever,headache,nausea,vomiting,agitation,anxiety,confusion,hyperactivity]");
            mapprolog.put("blood_around_the_nose", "[blood_around_the_nose,low_energy,eating_less,high_temperature,sudden_death]");
            mapprolog.put("ulcers_on_body", "[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight]");

        } else if (name_mammel.equals("mammal")) {
            map.put("drop_in_milk_production",
                    new String[]{"drop_in_milk_production", "fever", "bisters_in_the_mouth_and_on_feet", "weight_loss", "loss_of_appetite", "lameness"});
            map.put("paralysis",
                    new String[]{"paralysis", "lethargy", "fever", "vomiting", "weakness", "diffculty_breathing", "diffculty_swallowing", "excessive_salivation", "abnormal_behavior", "agression"});
            map.put("muscle_pain",
                    new String[]{"muscle_pain", "cough", "fever", "sore_throat", "headache", "shortness_of_breath"});
            map.put("difficulty_standing",
                    new String[]{"difficulty_standing", "high_fever", "weakness", "vomiting", "diarrhea"});
            map.put("mouth_sores",
                    new String[]{"mouth_sores", "skin_rash", "fever", "sore_throat", "loss_of_appetite"});
            map.put("nausea",
                    new String[]{"nausea", "fever", "headache", "vomiting", "agitation", "anxiety", "confusion", "hyperactivity"});
            map.put("difficulty_swallowing_and_breathing",
                    new String[]{"difficulty_swallowing_and_breathing", "fever", "reddening_of_the_lining_of_the_mouth_and_nose", "swelling_of_the_lips_tongue_and_gums", "a_swollen_purple_coloured_tongue", "lameness"});
            map.put("blood_around_the_nose",
                    new String[]{"blood_around_the_nose", "low_energy", "eating_less", "high_temperature", "sudden_death"});
            map.put("alivation",
                    new String[]{"alivation", "blisters", "injury_in_the_foot", "reduction_in_the_growth_rate", "high_fever"});
            map.put("recumbency",
                    new String[]{"recumbency", "fever", "lameness", "loss_of_appetite", "lethargy", "lesions", "crepitation", "difficulty_breathing", "tachycardia"});
            map.put("ulcers_on_body",
                    new String[]{"ulcers_on_body", "nodules_in_internal", "organs_fin_and_tail_rot", "loss_of_appetite", "loss_of_weight"});
            map.put("blood_is_watery",
                    new String[]{"blood_is_watery", "present_within_the_mucous", "the_liver_is_often_pale_and_friable"});
            map.put("weakness",
                    new String[]{"weakness", "weight_loss", "lack_of_appetite", "ruffled", "feathers_appearing", "droppings", "conjunctivitis"});

            arraykeys = new String[]{"drop_in_milk_production", "paralysis", "muscle_pain", "difficulty_standing",
                    "mouth_sores", "nausea", "difficulty_swallowing_and_breathing", "blood_around_the_nose", "alivation",
                    "recumbency", "ulcers_on_body", "blood_is_watery", "weakness"};

            mapprolog.put("drop_in_milk_production", "[drop_in_milk_production,fever,bisters_in_the_mouth_and_on_feet,weight_loss,loss_of_appetite,lameness]");
            mapprolog.put("paralysis", "[paralysis,lethargy,fever,vomiting,weakness,diffculty_breathing,diffculty_swallowing,excessive_salivation,abnormal_behavior,agression]");
            mapprolog.put("muscle_pain", "[muscle_pain,cough,fever,sore_throat,headache,shortness_of_breath]");
            mapprolog.put("difficulty_standing", "[difficulty_standing,high_fever,weakness,vomiting,diarrhea]");
            mapprolog.put("mouth_sores", "[mouth_sores,skin_rash,fever,sore_throat,loss_of_appetite]");
            mapprolog.put("nausea", "[nausea,fever,headache,vomiting,agitation,anxiety,confusion,hyperactivity]");
            mapprolog.put("difficulty_swallowing_and_breathing", "[difficulty_swallowing_and_breathing,fever,reddening_of_the_lining_of_the_mouth_and_nose,swelling_of_the_lips_tongue_and_gums,a_swollen_purple_coloured_tongue,lameness]");
            mapprolog.put("blood_around_the_nose", "[blood_around_the_nose,low_energy,eating_less,high_temperature,sudden_death]");
            mapprolog.put("alivation", "[alivation,blisters,injury_in_the_foot,reduction_in_the_growth_rate,high_fever]");
            mapprolog.put("recumbency", "[recumbency,fever,lameness,loss_of_appetite,lethargy,lesions,crepitation,difficulty_breathing,tachycardia]");
            mapprolog.put("ulcers_on_body", "[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight]");
            mapprolog.put("blood_is_watery", "[blood_is_watery,present_within_the_mucous,the_liver_is_often_pale_and_friable]");
            mapprolog.put("weakness", "[weakness,weight_loss,lack_of_appetite,ruffled,feathers_appearing,droppings,conjunctivitis]");

        }

        showDialogQuestion();

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.GERMAN);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTs", "Language not supported");
                    } else {
                        Log.e("TTs", "Language  supported");
                    }
                } else {
                    Log.e("TTs", "Initialization failed");
                }
            }
        });
    }

    private void speak() {
        mTTS.setSpeechRate(0.6f);
        mTTS.speak(name, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void showDialogQuestion() {
        dialog = new android.app.Dialog(this);
        dialog.setContentView(R.layout.ok);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        btn_yes = dialog.findViewById(R.id.btn_yes);
        btn_no = dialog.findViewById(R.id.btn_no);
        questionTextView = dialog.findViewById(R.id.txt_que);

        if (flag2) {
            questionTextView.setText(QES + arraykeys[count] + " ?");
            Log.e("arraykeys ", arraykeys[count]);
        } else {
            questionTextView.setText(QES + map.get(key)[countflag] + " ?");
            Log.e("map1 ", map.get(key)[countflag]);
        }

        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag) {
                    if (count < arraykeys.length) {
                        key = arraykeys[count];
                        count++;
                        flag = false;
                        flag2 = false;
                        Log.e("btn_yes", "yes");
                        Log.e("key yes", key);
                    } else {
                        Log.e("error yes", "index out of range");
                        dialog.dismiss();
                        showSad();
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dailog_sad.dismiss();
                                Intent intent2 = new Intent(Dialog.this, Dashboard.class);
                                startActivity(intent2);
                                finish();
                            }
                        }, 3000);
                    }
                } else {
                    if (countflag < map.get(key).length) {

                        arrayList.add(map.get(key)[countflag]);
                        countflag++;
                        if (countflag != map.get(key).length) {
                            dialog.dismiss();
                            showDialogQuestion();

                            Log.e("mammals.get(key) yes", map.get(key)[countflag]);
                        } else {
                            Log.e("arrayList is DISMISS ", String.valueOf(arrayList.size()));
                            if (arrayList.isEmpty()) {
                                Log.e("no arrayList is empty", " no ");
                                showSad();
                                new android.os.Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dailog_sad.dismiss();
                                        Intent intent2 = new Intent(Dialog.this, Dashboard.class);
                                        startActivity(intent2);
                                        finish();
                                    }
                                }, 3000);
                            } else if (arrayList.size() < (map.get(key).length) / 2) {
                                Log.e("no arrayL", " jkjk ");
                                showSad();
                                new android.os.Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dailog_sad.dismiss();
                                        Intent intent2 = new Intent(Dialog.this, Dashboard.class);
                                        startActivity(intent2);
                                        finish();
                                    }
                                }, 3000);
                            } else {

                                //python
                                String question = name_mammel + "(X," + mapprolog.get(key) + ",Y)";
                                Log.e("question : ", question);
                                PyObject str = main_program.callAttr("prolog_treatment", question);
                                //[{'X': 'fBGFBGFFGB', 'Y': 'gBGFDBGFFBGFFBg'}]
                                PyObject diseases = str.asList().get(0).asMap().get('X');
                                PyObject treatment = str.asList().get(0).asMap().get('Y');
                                Log.e("flag", str + "");
                                String replace = treatment.toString().replace("&", " and ").replace("_", " ");
                                Log.e("flag", "X: " + diseases.toString().replace("_", " ") + " Y: " + replace);

                                Intent intent = new Intent(Dialog.this, AnimalDiseases.class);
                                intent.putExtra("treatment", replace);
                                intent.putExtra("diseases", diseases.toString().replace("_", " "));
                                intent.putExtra("img_animal", img_animal);
                                intent.putExtra("name_animal", name_animal);
                                intent.putExtra("symptoms", mapprolog.get(key).replaceAll("_", " ")
                                        .replace("[", " ").replace("]", " "));
                                name = "Hi " + user_name + ", you have an " + name_animal + "suffering from a " + diseases.toString().replace("_", " ") + ".";
//                                            speak();
                                startActivity(intent);
                                finish();


                            }
                            dialog.dismiss();
                        }
                    }
                }
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    if (count < arraykeys.length) {
                        count++;
                        questionTextView.setText(QES + arraykeys[count] + " ?");
                        Log.e("btn_no", "no");
                    } else {
                        Log.e("error no", "index out of range");
                        dialog.dismiss();
                        showSad();
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dailog_sad.dismiss();
                                Intent intent2 = new Intent(Dialog.this, Dashboard.class);
                                startActivity(intent2);
                                finish();
                            }
                        }, 3000);
                    }

                } else {
                    if (countflag < map.get(key).length) {
                        countflag++;
                        if (countflag != map.get(key).length) {
                            dialog.dismiss();
                            showDialogQuestion();
                            Log.e("mammals.get(key) no", map.get(key)[countflag]);
                        } else {
                            Log.e("arrayList is DISMISS ", String.valueOf(arrayList.size()));
                            if (arrayList.isEmpty()) {
                                Log.e("no arrayList is empty", " no ");

                                showSad();
                                new android.os.Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dailog_sad.dismiss();
                                        Intent intent2 = new Intent(Dialog.this, Dashboard.class);
                                        startActivity(intent2);
                                        finish();
                                    }
                                }, 3000);
                            } else if (arrayList.size() < (map.get(key).length) / 2) {
                                Log.e("no arrayL", " jkjk ");

                                showSad();
                                new android.os.Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dailog_sad.dismiss();
                                        Intent intent2 = new Intent(Dialog.this, Dashboard.class);
                                        startActivity(intent2);
                                        finish();
                                    }
                                }, 3000);
                            } else {
                                //python
                                String question = name_mammel + "(X," + mapprolog.get(key) + ",Y)";
                                PyObject str = main_program.callAttr("prolog_treatment", question);
                                //[{'X': 'fBGFBGFFGB', 'Y': 'gBGFDBGFFBGFFBg'}]
                                PyObject diseases = str.asList().get(0).asMap().get('X');
                                PyObject treatment = str.asList().get(0).asMap().get('Y');
                                Log.e("flag", str + "");
                                String replace = treatment.toString().replace("&", " and ").replace("_", " ");
                                Log.e("flag", "X: " + diseases.toString().replace("_", " ") + " Y: " + replace);

                                Intent intent = new Intent(Dialog.this, AnimalDiseases.class);
                                intent.putExtra("treatment", replace);
                                intent.putExtra("diseases", diseases.toString().replace("_", " "));
                                intent.putExtra("img_animal", img_animal);
                                intent.putExtra("name_animal", name_animal);

                                intent.putExtra("symptoms", mapprolog.get(key).replaceAll("_", " ")
                                        .replace("[", " ").replace("]", " "));
                                name = "Hi " + user_name + ", you have an " + name_animal + "suffering from a " + diseases.toString().replace("_", " ") + ".";
//                                speak();
                                startActivity(intent);
                                finish();
                            }
                            dialog.dismiss();
                        }
                    }
                }
            }
        });

    }


    public void showSad() {
        dailog_sad = new android.app.Dialog(this);
        dailog_sad.setContentView(R.layout.sad_dailog);
        dailog_sad.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dailog_sad.getWindow();
        dailog_sad.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dailog_sad.show();
    }

}