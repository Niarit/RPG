package com.codecool.quest.logic;

import com.codecool.quest.Main;
import javafx.scene.control.Label;

public class Dialogue {
    private static Label dialogueText;

    static{
        dialogueText = Main.dialogueLabel;
    }

    public Dialogue(String text){
        dialogueText.setText(text);
    }

}
