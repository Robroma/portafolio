package com.example.androidfirebase.controller;

import android.view.View;

import com.example.androidfirebase.dao.FireStoreDB;
import com.example.androidfirebase.model.HangedMan;
import com.example.androidfirebase.utils.Constants;
import com.example.androidfirebase.view.HangedManActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class HangedManController implements ControllerInterface {

    private HangedManActivity hangedManActivity;
    private HangedMan hangedMan;
    private FireStoreDB fireStore;

    public HangedManController(HangedManActivity hangedManActivity, FireStoreDB firestore) {
        this.hangedManActivity = hangedManActivity;
        this.fireStore = firestore;
        hangedMan = new HangedMan();
    }

    @Override
    public void createActivityButtons() {
        startGame();

        this.hangedManActivity.getSendBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = hangedManActivity.getGuessTry().getText().toString().trim().toLowerCase();

                if (answer.isEmpty()) {
                    alertToast(hangedManActivity, "Introduzca una respuesta");
                } else if (answer.length() == 1) {
                    if (!addAnswers(answer)) {
                        hangedMan.getAnswers().add(answer);
                        hangedManActivity.getAnswers().setText(hangedMan.getAnswers().toString());

                        if (!checkAnswer(answer)) {
                            switch (hangedMan.getTries()) {
                                case 6:
                                    finishGame("Has perdido! \nRespuesta: ", "Lastima!");
                                default:
                                    alertToast(hangedManActivity, "Has fallado");
                            }
                        }
                    }
                } else {
                    if (Constants.hangedMan[hangedMan.getWordNumber()].equals(answer)) {
                        finishGame("Has ganado! \nRespuesta: ", "Bien hecho!");
                        fireStore.updateWinsHM(fireStore.getUser().getEmail());
                    } else {
                        if (!addAnswers(answer)) {
                            hangedMan.getAnswers().add(answer);
                            hangedManActivity.getAnswers().setText(hangedMan.getAnswers().toString());
                            hangedMan.setTries(hangedMan.getTries() + 1);
                            hangedManActivity.getImageTries().setImageResource(Constants.hangedManTries[hangedMan.getTries()]);

                            switch (hangedMan.getTries()) {
                                case 6:
                                    finishGame("Has perdido! \nRespuesta: ", "Lastima!");
                                default:
                                    alertToast(hangedManActivity, "Has fallado");

                            }
                        }
                    }
                }
                hangedManActivity.getGuessTry().setText("");
                fireStore.addGameHM(hangedMan);
            }
        });
    }

    private void startGame() {
        fireStore.getGameHM().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    hangedMan = documentSnapshot.toObject(HangedMan.class);
                    hangedManActivity.getImageTries().setImageResource(Constants.hangedManTries[hangedMan.getTries()]);
                } else {
                    createGame();
                }

                if (!addAnswers("")) {
                    hangedManActivity.getAnswers().setText(hangedMan.getAnswers().toString());
                }
                updateLetters(hangedMan);
            }
        });
    }

    private boolean addAnswers(String answer) {
        if (!answer.equals(" ")) {
            for (String a : hangedMan.getAnswers()) {
                if (a.equals(answer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateLetters(HangedMan hangedMan) {
        String word = "";
        for (char letter : Constants.hangedMan[hangedMan.getWordNumber()].toCharArray()) {
            if (hangedMan.getAnswers().contains(letter + "")) {
                word += letter + " ";
            } else {
                word += "_ ";
            }
        }
        hangedManActivity.getWord().setText(word);
    }

    private boolean checkAnswer(String answer) {
        updateLetters(hangedMan);

        for (char letter : Constants.hangedMan[hangedMan.getWordNumber()].toCharArray()) {
            if ((letter + "").equals(answer)) {
                return true;
            }
        }
        hangedMan.setTries(hangedMan.getTries() + 1);
        hangedManActivity.getImageTries().setImageResource(Constants.hangedManTries[hangedMan.getTries()]);
        return false;
    }

    private void createGame() {
        hangedMan.setWordNumber((int) (Math.random() * Constants.hangedMan.length));
        hangedMan.setTries(0);
        hangedMan.setAnswers(new ArrayList<>());
        hangedManActivity.getImageTries().setImageResource(Constants.hangedManTries[hangedMan.getTries()]);
        updateLetters(hangedMan);
        fireStore.addGameHM(hangedMan);
    }

    private void finishGame(String message, String title) {
        showAlert(hangedManActivity, message + Constants.hangedMan[hangedMan.getWordNumber()], title);
        hangedManActivity.getAnswers().setText("");
        createGame();
    }
}
