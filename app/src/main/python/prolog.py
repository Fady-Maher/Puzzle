#https://github.com/MNoorFawi/pytholog
import pytholog as pl



def prolog_puzzle(expression):
        new_kb = pl.KnowledgeBase("flavor")
        new_kb(["domestic([calf,crap,cavy,chicken,dove,goat,hamster,honeybee,parakeet,pony,pussycat,reindeer])",
                "milk([aardvark,antelope,bear,boar,buffalo,calf,cavy,cheetah,deer,dolphin,elephant,fruitbat,giraffe,goat,gorilla,hamster,hare,leopard,lion,lynx,mink,mole,mongoose,opossum,oryx,platypus,polecat,pony,porpoise,puma,pussycat,raccoon,reindeer,seal,sealion,squirrel,vampire,vole,wallaby,wolf])",
                "airborne([chicken,crow,dove,duck,flamingo,fruitbat,gnat,gull,hawk,honeybee,housefly,ladybird,lark,moth,parakeet,pheasant,skimmer,skua,sparrow,swan,vampire,vulture,wasp,wren])",
                "aquatic([bass,carp,catfish,chub,crab,crayfish,dogfish,dolphin,duck,frog,gull,haddock,herring,lobster,mink,newt,octopus,penguin,pike,piranha,platypus,porpoise,seahorse,seal,sealion,seasnake,seawasp,skimmer,skua,sole,starfish,stingray,swan,toad,tuna])",
                "hair([aardvark,antelope,bear,boar,buffalo,calf,cavy,cheetah,deer,elephant,fruitbat,giraffe,goat,gorilla,hamster,hare,honeybee,housefly,leopard,lion,lynx,mink,mole,mongoose,moth,opossum,oryx,platypus,polecat,pony,puma,pussycat,raccoon,reindeer,seal,sealion,squirrel,vampire,vole,wallaby,wasp,wolf])",
                "feathers([chickencrow,dove,duck,flamingo,gull,hawk,kiwi,lark,ostrich,parakeet,penguin,pheasant,rhea,skimmer,skua,sparrow,swan,vulture,wren])",
                "eggs([bass,carp,catfish,chicken,chub,clam,crab,crayfish,crow,dogfish,dove,duck,flamingo,flea,frog,frog,gnat,gull,haddock,hawk,herring,honeybee,housefly,kiwi,ladybird,lark,lobster,moth,newt,octopus,ostrich,parakeet,penguin,pheasant,pike,piranha,pitviper,platypus,rhea,seahorse,seawasp,skimmer,skua,slowworm,slug,sole,sparrow,starfish,stingray,swan,termite,toad,tortoise,tuatara,tuna,vulture,wasp,worm,wren])",
                "backbone([aardavrk,antelope,bass,bear,boar,buffalo,calf,carp,catfish,cavy,cheetah,chicken,crow,deer,dogfish,dolphin,dove,duck,elephant,flamingo,frog,fruitbat,giraffe,gnat,goat,gorilla,gull,haddock,hamster,hare,hawk,herring,kiwi,lark,leopard,lion,lynx,mink,mole,mongoose,newt,opossum,oryx,ostrich,parakeet,penguin,pheasant,pike,piranha,pitviper,platypus,polecat,pony,porpoise,puma,pussycat,raccoon,reindeer,rhea,seahorse,seal,sealion,seasnake,skimmer,skua,slowworm,sole,sparrow,squirrel,stingray,swan,toad,tortoise,tuatara,tuna,vampire,vole,vulture,wallaby,wolf,wren])",
                "breathes([aardavrk,antelope,bear,boar,buffalo,claf,cavy,cheetah,chicken,crow,deer,dolphin,dove,duck,elephant,flamingo,flea,frog,fruitbat,giraffe,gnat,goat,gorilla,gull,hamster,hare,hawk,honeybee,housefly,kiwi,ladybird,lark,leopard,lion,lynx,mink,mole,mongoose,moth,newt,opossum,oryx,ostrich,parakeet,penguin,pheasant,pitviper,platypus,polecat,pony,porpoise,puma,pussycat,raccoon,reindeer,rhea,scorpion,seal,sealion,skimmer,skua,slowworm,slug,sparrow,squirrel,swan,termite,toad,tortoise,tuatara,vampire,vole,vulture,wallaby,wasp,wolf,worm,wren])",
                "venomous([frog,honeybee,pitviper,scorpion,seasnake,seawasp,stingray,wasp])",
                "fins([bass,carp,catfish,chub,dogfish,dolphin,haddock,herring,pike,piranha,porpoise,seahorse,seal,sealion,sole,stingray,tuna])",
                "predator([aardvark,bass,bear,boar,catfish,cheetah,chub,clam,crab,crayfish,crow,dogfish,dolphin,frog,gull,hawk,herring,kiwi,ladybird,leopard,lion,lobster,lynx,mink,mole,mongoose,newt,octopus,opossum,penguin,pike,piranha,pitviper,platypus,polecat,porpoise,puma,pussycat,raccoon,rhea,scorpion,seal,sealion,seasnake,seawasp,skimmer,skua,slowworm,starfish,stingray,tuatara,tuna,vulture,wolf])",
                "toothed([aardvark,antelope,bass,bear,boar,buffalo,calf,carp,catfish,cavy,cheetah,chub,deer,dogfish,dolphin,elephant,frog,fruitbat,giraffe,goat,gorilla,haddock,hamster,hare,herring,leopard,lion,lynx,mink,mole,mongoose,newt,opossum,oryx,pike,piranha,pitviper,polecat,pony,porpoise,puma,pussycat,raccoon,reindeer,seahorse,seal,sealion,seasnake,slowworm,sole,squirrel,stingray,toad,tuatara,tuna,vampirebat,vole,wallaby,wolf])",
                "legs([aardvark,antelope,bear,boar,buffalo,calf,cavy,cheetah,chicken,crab,crayfish,crow,deer,dove,duck,elephant,flamingo,flea,frog,fruitbat,giraffe,gnat,goat,gorilla,gull,hamster,hare,hawk,honeybee,housefly,kiwi,ladybird,lark,leopard,lion,lobster,lynx,mink,mole,mongoose,moth,newt,octopus,opossum,oryx,ostrich,parakeet,penguin,pheasant,platypus,polecat,pony,porpoise,puma,pussycat,raccoon,reindeer,rhea,scorpion,seahorse,seal,sealion,seasnake,seawasp,skimmer,skua,slowworm,slug,sole,sparrow,squirrel,starfish,stingray,swan,termite,toad,tortoise,tuatara,vole,vulture,wallaby,wasp,wolf,wren])",
                "tail([antelope ,bass ,bear, boar, buffalo, calf, carp, catfish,cavy ,cheetah,cat, chicken, chub, clam, crab, crayfish, crow,deer, dogfish, dolphin, dove, duck, elephant, flamingo,flea, frog, frog, fruitbat, giraffe, gnat, goat,gorilla, gull, haddock, hamster, hare, hawk, herring,honeybee, housefly, kiwi, ladybird, lark, leopard, lion,lobster, lynx, mink, mole, mongoose, moth, newt, octopus, opossum, oryx, ostrich, parakeet, penguin, pheasant, pike, piranha, pitviper, platypus, polecat, pony, porpoise, puma, pussycat, raccoon, reindeer, rhea, scorpion, seahorse, seal,sealion, seasnake, seawasp, skimmer, skua, slowworm, slug, sole, sparrow, squirrel, starfish, stingray, swan, termite, toad, tortoise, tuatara, tuna, vole, vulture, wallaby, wasp, wolf, worm, wren])",
                "member(X,[X|_])",
                "member(X,[_|L]):-member(X,L)",
                "in_my_tail_elements(X):-tail(L),member(X,L)",
                "in_my_legs_elements(X):-legs(L),member(X,L)",
                "in_my_toothed_elements(X):-toothed(L),member(X,L)",
                "in_my_predator_elements(X):-predator(L),member(X,L)",
                "in_my_fins_elements(X):-fins(L),member(X,L)",
                "in_my_venomous_elements(X):-venomous(L),member(X,L)",
                "in_my_breathes_elements(X):-breathes(L),member(X,L)",
                "in_my_backbone_elements(X):-backbone(L),member(X,L)",
                "in_my_eggs_elements(X):-eggs(L),member(X,L)",
                "in_my_feathers_elements(X):-feathers(L),member(X,L)",
                "in_my_hair_elements(X):-hair(L),member(X,L)",
                "in_my_aquatic_elements(X):-aquatic(L),member(X,L)",
                "in_my_airborne_elements(X):-airborne(L),member(X,L)",
                "in_my_milk_elements(X):-milk(L),member(X,L)",
                "in_my_domestic_elements(X):-domestic(L),member(X,L)"])


        return new_kb.query(pl.Expr(expression))


def prolog_treatment(expression):
        new_kb = pl.KnowledgeBase("flavor")
        new_kb([
            #ahmed
            "mammal(foot_and_mouthDisease,[drop_in_milk_production,fever,bisters_in_the_mouth_and_on_feet,weight_loss,loss_of_appetite,lameness],there_is_no_treatment_but_some_countries_use_antibiotic_therapy_to_control_second_bacterial_infection_and_recovery_take_several_weeks)"
            ,"mammal(rabiesDisease,[paralysis,lethargy,fever,vomiting,weakness,diffculty_breathing,diffculty_swallowing,excessive_salivation,abnormal_behavior,agression],there_is_no_cure_for_rabies_and_animal_usually_dies_within_few_days)"
            ,"mammal(influenza_avian,[muscle_pain,cough,fever,sore_throat,headache,shortness_of_breath],flumadine&relenza&tamiflu)"
            ,"mammal(african_swine_fever,[difficulty_standing,high_fever,weakness,vomiting,diarrhea],treatment_of_the_disease_is_not_possible)"
            ,"mammal(foot_and_mouth_disease,[mouth_sores,skin_rash,fever,sore_throat,loss_of_appetite],antibiotics&flunixin_meglumine&mild_disinfectants)"
            ,"mammal(rabies,[nausea,fever,headache,vomiting,agitation,anxiety,confusion,hyperactivity],there_is_no_treatment)"
            ,"mammal(bluetongue,[difficulty_swallowing_and_breathing,fever,reddening_of_the_lining_of_the_mouth_and_nose,swelling_of_the_lips_tongue_and_gums,a_swollen_purple_coloured_tongue,lameness],antibiotic)"
            ,"mammal(rabbit_haemorrhagic_disease,[blood_around_the_nose,low_energy,eating_less,high_temperature,sudden_death],there_is_no_cure_and_the_solution_is_euthanasia)"
            ,"mammal(vesicular_stomatitis,[alivation,blisters,injury_in_the_foot,reduction_in_the_growth_rate,high_fever],there_is_no_cure_and_the_solution_is_euthanasia_and_antibiotics_may_avoid_secondary_infection_of_abraded_tissues)"
            ,"mammal(blackleg,[recumbency,fever,lameness,loss_of_appetite,lethargy,lesions,crepitation,difficulty_breathing,tachycardia],penicillin_can_be_effective_in_saving_an_animal’s_life)"
            ,"mammal(bacterial_diseases,[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight],copper_sulphate_for_1_minute_for_3_4_days_antibiotics_are_not_successful)"
            ,"mammal(theileriosis,[blood_is_watery,present_within_the_mucous,the_liver_is_often_pale_and_friable],buparvaquone&halofuginone&chemotherapeutics&tetracyclines)"
            ,"mammal(avian_chlamydiosis,[weakness,weight_loss,lack_of_appetite,ruffled,feathers_appearing,droppings,conjunctivitis],antibiotics)"


            ,"birds(newcastleDisease,[nasal_discharge,sneezing,coughing,watery_diarrhoea,depression,muscular_tremors,drooping_wings,complete_paralysis],antibitoc_to_control_secondray_bacterial_infections&a_vaccine_used_routinely_in_poutrly_flocks)"
            ,"birds(influenza_avian,[lack_of_energy_and_appetite,fever,cough,sneezing,diarrhea,decreased_egg_production,purple_discoloration_of_the_wattles_and_combs_and_legs,lack_of_coordination,sudden_death],antiviral_drugs_oseltamivir_tamiflu&zanamivir_relenza)"
            ,"birds(influenza_avian,[shortness_of_breath,cough,fever,sore_throat,muscle_pain,headache],flumadine&relenza&tamiflu)"
            ,"birds(african_swine_fever,[difficulty_standing,high_fever,weakness,vomiting,diarrhea],treatment_of_the_disease_is_not_possible)"
            ,"birds(foot_and_mouth_disease,[skin_rash,mouth_sores,fever,sore_throat,loss_of_appetite],antibiotics&flunixin_meglumine&mild_disinfectants)"
            ,"birds(rabies,[nausea,fever,headache,vomiting,agitation,anxiety,confusion,hyperactivity],there_is_no_treatment)"
            ,"birds(bluetongue,[reddening_of_the_lining_of_the_mouth_and_nose,fever,swelling_of_the_lips_tongue_and_gums,difficulty_swallowing_and_breathing,a_swollen_purple_coloured_tongue,lameness],antibiotic)"




            ,"birds(fowl_typhoid,[dehydration,anorexia,diarrhoea,weakness,high_mortality,decreased_egg_production,decreased_fertility_and_hatchability],eradication_of_the_disease_through_isolation_and_destruction_of_contaminated_flocks_proper_disposal_of_carcasses)"
            ,"birds(blackleg,[lesions,fever,lameness,loss_of_appetite,lethargy,recumbency,crepitation,difficulty_breathing,tachycardia],penicillin_can_be_effective_in_saving_an_animal’s_life)"
            ,"birds(duck_virus_hepatitis,[death_in_good_condition,sudden_death,depression,fall_on_side,paddling_of_legs],antiserum&0.5_ml_serum_of_recovered_birds_given_intramuscularly)"
            ,"birds(bacterial_diseases,[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight],copper_sulphate_for_1_minute_for_3_4_days_antibiotics_are_not_successful)"
            ,"birds(avian_infectious_laryngotracheitis,[rhinitis,swollen_head,breathing_difficulty,drop_in_productivity,loss_of_appetite,bronchial_bleeding],there_are_no_specifictreatments_for_the_disease_but_antibiotics_to_control_secondary_bacterial)"
            ,"birds(rabbit_haemorrhagic_disease,[blood_around_the_nose,low_energy,eating_less,high_temperature,sudden_death],there_is_no_cure_and_the_solution_is_euthanasia)"
            ,"birds(avian_chlamydiosis,[ruffled,weakness,weight_loss,lack_of_appetite,feathers_appearing,droppings,conjunctivitis],antibiotics)"


            ,"amphibians(influenza_avian,[cough,fever,sore_throat,muscle_pain,headache,shortness_of_breath],flumadine&relenza&tamiflu)"
            ,"amphibians(african_swine_fever,[high_fever,weakness,difficulty_standing,vomiting,diarrhea],treatment_of_the_disease_is_not_possible)"
            ,"amphibians(foot_and_mouth_disease,[mouth_sores,skin_rash,fever,sore_throat,loss_of_appetite],antibiotics&flunixin_meglumine&mild_disinfectants)"
            ,"amphibians(rabies,[nausea,fever,headache,vomiting,agitation,anxiety,confusion,hyperactivity],there_is_no_treatment)"
            ,"amphibians(monkey_Pox,[intense_headache,fever,lymphadenopathy,back_pain,intense_asthenia],there_is_currently_no_specific_treatment_recommended_for_monkeypox&vaccination_against_smallpox_with_vaccinia_vaccine_was_demonstrated_through_several_observational_studies_to_be_about_85_effective_in_preventing_monkeypox)"
            ,"amphibians(bacterial_diseases,[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight],copper_sulphate_for_1_minute_for_3_4_days_antibiotics_are_not_successful)"
            ,"amphibians(bluetongue,[difficulty_swallowing_and_breathing,fever,reddening_of_the_lining_of_the_mouth_and_nose,swelling_of_the_lips_tongue_and_gums,a_swollen_purple_coloured_tongue,lameness],antibiotic)"

            #aya
            ,"insects(african_swine_fever,[high_fever,weakness,difficulty_standing,vomiting,diarrhea],treatment_of_the_disease_is_not_possible)"
            ,"insects(rabies,[fever,headache,nausea,vomiting,agitation,anxiety,confusion,hyperactivity],there_is_no_treatment)"
            ,"insects(rabbit_haemorrhagic_disease,[blood_around_the_nose,low_energy,eating_less,high_temperature,sudden_death],there_is_no_cure_and_the_solution_is_euthanasia)"
            ,"insects(bacterial_diseases,[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight],copper_sulphate_for_1_minute_for_3_4_days_antibiotics_are_not_successful)"

            ,"reptiles(monkey_Pox,[fever,intense_headache,lymphadenopathy,back_pain,intense_asthenia],there_is_currently_no_specific_treatment_recommended_for_monkeypox&vaccination_against_smallpox_with_vaccinia_vaccine_was_demonstrated_through_several_observational_studies_to_be_about_85_effective_in_preventing_monkeypox)"
            ,"reptiles(bacterial_diseases,[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight],copper_sulphate_for_1_minute_for_3_4_days_antibiotics_are_not_successful)"

            ,"fish(rabies,[fever,headache,nausea,vomiting,agitation,anxiety,confusion,hyperactivity],there_is_no_treatment)"
            ,"fish(bacterial_diseases,[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight],copper_sulphate_for_1_minute_for_3_4_days_antibiotics_are_not_successful)"

            ,"invertebrates(bacterial_diseases,[ulcers_on_body,nodules_in_internal,organs_fin_and_tail_rot,loss_of_appetite,loss_of_weight],copper_sulphate_for_1_minute_for_3_4_days_antibiotics_are_not_successful)"

        ])

        return new_kb.query(pl.Expr(expression))
