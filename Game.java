
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    //intializes JFrame 
    JFrame window;
    //creates a container to hold JPanels 
    Container content;
    //initalizes the JPanels
    JPanel titleNamePanel, startButtonPanel, continueButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    //initalizess the JLabels
    JLabel titleNameLabel, hpLabel, hpLabelNumber, moneyLabel, moneyLabelNumber, weaponLabel, weaponLabelName;
    //creates a font (font style, plain/bold/italic, size of text
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    //creates the JButtons for the choices represented in the game
    JButton startButton, continueButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    //initializes the playerHp, and monsterHp
    int playerHP, monsterHP, money, playerDamage, swords, bombs, shuriken;
    //initializes the weapons and Position of the player
    String weapon, position;
    Random generator = new Random(); // object to create random numbers
    // creates a new methos for each the title, the choices, and the continue
    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    ContinueHandler csHandler = new ContinueHandler();

    ImageIcon logo = new ImageIcon(".//res//jackfrost.jpg");

    //makes a new main
    public static void main(String[] args) throws IOException {

        new Game();
    }

    // initializes the 
    public Game() {

        // sets the frame and the size of the JFrame
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setIconImage(logo.getImage());
        content = window.getContentPane();

        //creates title Panel for the intro game
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("ADVENTURE");
        titleNameLabel.setForeground(Color.pink);
        titleNameLabel.setFont(titleFont);

        //
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(200, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        continueButtonPanel = new JPanel();
        continueButtonPanel.setBounds(400, 400, 200, 100);
        continueButtonPanel.setBackground(Color.black);

        continueButton = new JButton("CONTINUE");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setFont(normalFont);
        continueButton.addActionListener(csHandler);
        continueButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        continueButtonPanel.add(continueButton);

        content.add(titleNamePanel);
        content.add(startButtonPanel);
        content.add(continueButtonPanel);

        window.setVisible(true);
    }

    public void createGameScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        continueButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        content.add(mainTextPanel);
        mainTextArea = new JTextArea("This is the main text are. This game is going to be great. I'm sure of it!!!!!!!");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        content.add(choiceButtonPanel);
        //initalizes what it looks like for choice 1 
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

//		choice4.setContentAreaFilled(false);  // Disable highlighting on press!!!
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        content.add(playerPanel);
        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);
        moneyLabel = new JLabel("Money:");
        moneyLabel.setFont(normalFont);
        moneyLabel.setForeground(Color.white);
        moneyLabel.setBackground(Color.red);
        playerPanel.add(moneyLabel);
        moneyLabelNumber = new JLabel();
        moneyLabelNumber.setFont(normalFont);
        moneyLabelNumber.setForeground(Color.white);
        playerPanel.add(moneyLabelNumber);

        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        weaponLabel.setBackground(Color.red);
        playerPanel.add(weaponLabel);
        weaponLabelName = new JLabel();
        weaponLabelName.setFont(normalFont);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);
        playerSetup();

    }// end of createGameScreen

    public void playerSetup() {
        playerHP = 30;
        monsterHP = 5;
        money = 0;
        weapon = " Hands";
        weaponLabelName.setText(weapon);
        moneyLabelNumber.setText("" + money);
        hpLabelNumber.setText("" + playerHP);
        //make name into submition thingy 
        intro();
    }//

    public void intro() {
        position = "intro";
        mainTextArea.setText("What would you like to do?");
        choice1.setText("Hunt");
        choice2.setText("Shop");
        choice3.setText("Home");
        choice4.setText("Save Game");
    }// end of 

    public void hunt() {
        position = "hunt";
        mainTextArea.setText("You encounter a monster, With " + monsterHP + " Hp!");
        choice1.setText("Fight");
        choice2.setText("Run");
        choice3.setText("Talk");
        choice4.setText("");
    }

    //the four choices for HUNT
    public void fight() {
        position = "fight";
        mainTextArea.setText("What would you like to do?\nMonster HP:" + monsterHP);
        choice1.setText("Use Weapons");
        choice2.setText("Heal");
        choice3.setText("Normal attack");
        choice4.setText("back");
    }

    public void weaponUse() {
        position = "weaponUse";
        mainTextArea.setText("What weapon would you like to use?");
        choice1.setText("Sword");
        choice2.setText("Bomb");
        choice3.setText("Shuriken");
        choice4.setText("Back");

    }// end of weaponUse()

    public void weaponUseSwords() {
        position = "weaponUseSwords";
        if (swords < 1) {
            mainTextArea.setText("You don't have enough swords ");
            choice1.setText("back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            swords--;
            playerDamage = generator.nextInt(12);
            monsterHP = monsterHP - playerDamage;
            mainTextArea.setText("You have done " + playerDamage + " damage.");
        }
    }// end of weaponUseSwords

    public void weaponUseBombs() {
        position = "weaponUseBombs";
        if (bombs < 1) {
            mainTextArea.setText("You don't have enough bombs ");
            choice1.setText("back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            bombs--;
            playerDamage = generator.nextInt(18);
            monsterHP = monsterHP - playerDamage;
            mainTextArea.setText("You have done " + playerDamage + " damage.");
        }

    }// end of weaponUseBombs

    public void weaponUseShuriken() {
        position = "weaponUseShuriken";
        if (shuriken < 1) {
            mainTextArea.setText("You don't have enough shuriken ");
            choice1.setText("back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            shuriken--;
            playerDamage = generator.nextInt(20);
            monsterHP = monsterHP - playerDamage;
            mainTextArea.setText("You have done " + playerDamage + " damage.");
        }

    }//end of weaponUseShuriken

    public void healFromFighting() {
        position = "healFromFighting";
        if (playerHP == 30) {
            mainTextArea.setText("You are already at max health. ");
            choice1.setText(">");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            int playerHeal = 0;
            playerHeal = generator.nextInt(10);
            mainTextArea.setText("You have healed " + playerHeal + " HP!");
            playerHP = playerHP + playerHeal;
            hpLabelNumber.setText("" + playerHP);

            choice1.setText(">");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        }
    }

    public void runFromMonster() {
        position = "runningFromMonster";
        int running = 0;
        running = generator.nextInt(6);
        if (running == 3 || running == 6) {
            mainTextArea.setText("You have successfully escaped! For now...");
            choice1.setText(">");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            mainTextArea.setText("...you have failed to escape!");

            monsterAttack();
        }
    }

    public void talkToMonster() {
        position = "talkToMonster";
        mainTextArea.setText("Monster: Grr? Grr? You have confused the monster. The monster has run away");
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    //Choices for SHOP
    public void shop() {
        position = "shop";
        mainTextArea.setText("Clark (The store owner): Welcome to the shop. What would you like");
        choice1.setText("Swords: 5 gold");
        choice2.setText("Bombs: 10 gold");
        choice3.setText("Shuriken: 20 gold");
        choice4.setText("Back");
    }

    public void swords() {
        position = "swords";
        if (money < 5) {
            mainTextArea.setText("You do not have enough money. ");
            choice1.setText("back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            money = money - 5;
            moneyLabelNumber.setText("" + money);
            swords = swords + 1;
            mainTextArea.setText("You now have " + swords + " swords");
            choice1.setText("back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        }

    }// end of swords

    public void bombs() {
        position = "bombs";
        if (money < 10) {
            mainTextArea.setText("You do not have enough money for bombs. ");
            choice1.setText(">");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            money = money - 10;
            moneyLabelNumber.setText("" + money);
            bombs = bombs + 1;
            mainTextArea.setText("You now have " + bombs + " bombs");
            choice1.setText("back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        }

    }// end of bombs

    public void shuriken() {
        position = "shuriken";
        if (money < 20) {
            mainTextArea.setText("You do not have enough money for shuriken. ");
            choice1.setText(">");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        } else {
            money = money - 20;
            moneyLabelNumber.setText("" + money);
            shuriken = shuriken + 1;
            mainTextArea.setText("You now have " + shuriken + " shuriken");
            choice1.setText("back");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
        }

    }//end of shuriken

    public void home() {
        position = "home";
        mainTextArea.setText("Mom: Hi honey, I hope you are doing well, don't forget to sleep!! ");
        choice1.setText("Go to sleep");
        choice2.setText("Try to pick a fight with mom");
        choice3.setText("Go back");
        choice4.setText("");
    }

    public void sleep() {
        position = "sleep";
        mainTextArea.setText("Your health is maxed");
        playerHP = 30;
        hpLabelNumber.setText("" + playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void fightingMom() {
        position = "fightingMom";
        mainTextArea.setText("Mom: EXCUSE ME WHAT ARE YOU TRING TO DO! ");
        int momdmg = 0;
        for (int i = 0; i < 15; i++) {
            momdmg = generator.nextInt(15);
        }
        playerHP = playerHP - momdmg;
        hpLabelNumber.setText("" + playerHP);
        mainTextArea.setText("Your mom has hit you with a chancla your health is " + playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void saveGame() throws IOException {
        try (PrintWriter writer = new PrintWriter(new File("saveGame.txt"))) {
            writer.println(playerHP);
            writer.println(money);
            writer.println(swords);
            writer.println(bombs);
            writer.println(shuriken);
        }

        mainTextArea.setText("saveGame.txt been created. ");

    }

    public void continueGame() throws IOException {
             //scanner 

        //reads the file  'accounts_data.txt'
        Scanner reader = new Scanner(new File("saveGame.txt"));

        playerHP = reader.nextInt();
        money = reader.nextInt();
        swords = reader.nextInt();
        bombs = reader.nextInt();
        shuriken = reader.nextInt();
        //make name into submition thingy 
        hpLabelNumber.setText(playerHP + "");
        intro();
    }

    public void playerAttack() {
        position = "playerAttack";

        playerDamage = generator.nextInt(3);

        mainTextArea.setText("You attacked the monster and gave " + playerDamage + " damage!");

        monsterHP = monsterHP - playerDamage;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void monsterAttack() {
        position = "monsterAttack";

        int monsterDamage = 0;

        monsterDamage = generator.nextInt(6);

        mainTextArea.setText("The monster attacked you and gave " + monsterDamage + " damage!");

        playerHP = playerHP - monsterDamage;
        hpLabelNumber.setText("" + playerHP);

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void monterDeath() {
        position = "MonterDeath";
        int moneyDrop = 0;
        moneyDrop = generator.nextInt(15) + 1;
        mainTextArea.setText("You defeated the monster!\nThe monster dropped " + moneyDrop + " dollars");
        money = money + moneyDrop;
        moneyLabelNumber.setText("" + money);
        monsterHP = generator.nextInt(30);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    public void lose() {
        position = "lose";

        mainTextArea.setText("You are dead!\n\nGAME OVER");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public void ending() {
        position = "ending";

        mainTextArea.setText("");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public class ContinueHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                continueGame();
            } catch (Exception e) {
            }

        }
    }

    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            //put continue action button 
//            if (event.getSource() == continueButton) {
//                System.out.println("Test vode");
//                try {
//                    continueGame();
//                } catch (Exception e) {
//                    System.out.println("File not found.");
//                }
//            }
            switch (position) {
                case "intro":
                    switch (yourChoice) {
                        case "c1":
                            hunt();
                            break;
                        case "c2":
                            shop();
                            break;
                        case "c3":
                            home();
                            break;
                        case "c4":
                            try {
                                saveGame();
                            } catch (Exception e) {
                            }
                    }
                    break;

                case "hunt":
                    switch (yourChoice) {
                        case "c1":
                            fight();
                            break;
                        case "c2":
                            runFromMonster();
                            break;
                        case "c3":
                            talkToMonster();
                            break;
                    }
                    break;
                case "fight":
                    switch (yourChoice) {
                        case "c1":
                            weaponUse();
                            break;
                        case "c2":
                            healFromFighting();
                            break;
                        case "c3":
                            playerAttack();
                            break;
                        case "c4":
                            hunt();
                            break;

                    }
                    break;
                case "weaponUse":
                    switch (yourChoice) {
                        case "c1":
                            weaponUseSwords();
                            break;
                        case "c2":
                            weaponUseBombs();
                            break;
                        case "c3":
                            weaponUseShuriken();
                            break;
                        case "c4":
                            fight();
                            break;
                    }
                    break;
                case "healFromFighting":
                    switch (yourChoice) {
                        case "c1":
                            fight();
                            break;
                    }
                    break;

                case "runningFromMonster":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                            break;
                        case "c2":
                            break;
                    }
                    break;
                case "playerAttack":
                    switch (yourChoice) {
                        case "c1":
                            if (monsterHP < 1) {
                                monterDeath();
                            } else {
                                monsterAttack();
                            }

                    }

                    break;
                case "shop":
                    switch (yourChoice) {
                        case "c1":
                            swords();
                            break;
                        case "c2":
                            bombs();
                            break;
                        case "c3":
                            shuriken();
                            break;
                        case "c4":
                            intro();
                            break;
                    }
                    break;
                case "talkToMonster":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                            break;
                    }
                    break;
                case "home":
                    switch (yourChoice) {
                        case "c1":
                            sleep();
                            break;
                        case "c2":
                            if (playerHP < 1) {
                                lose();
                            } else {
                                fightingMom();
                            }
                            break;
                        case "c3":
                            intro();
                            break;
                    }
                    break;

                case "fightingMom":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                            break;
                    }
                    break;
                case "sleep":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                            break;
                    }
                    break;

                case "MonterDeath":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                    }
                    break;

                case "monsterAttack":
                    switch (yourChoice) {
                        case "c1":
                            if (playerHP < 1) {
                                lose();
                            } else {
                                fight();
                            }
                            break;
                    }
                    break;
                case "win":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                    }
                    break;
                case "swords":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                    }
                    break;
                case "bombs":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                    }
                    break;
                case "shuriken":
                    switch (yourChoice) {
                        case "c1":
                            intro();
                    }
                    break;
                case "weaponUseSwords":
                    switch (yourChoice) {
                        case "c1":
                            fight();
                    }
                    break;
                case "weaponUseBombs":
                    switch (yourChoice) {
                        case "c1":
                            fight();
                    }
                    break;

                case "weaponUseShuriken":
                    switch (yourChoice) {
                        case "c1":
                            fight();
                    }
                    break;

            }// end of switch
        }
    }
}// end of class
