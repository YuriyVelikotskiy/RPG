import Personage.Monster.Goblin;
import Personage.Monster.Skeleton;
import Personage.Hero;
import Entity.Character;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class World {
    private static BufferedReader reader;
    private static Character gamer = null;
    private static Battle battle = null;

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    public static void main(String[] args) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();

        System.out.println("what is your name?");
        try {
            command(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (gamer == null) {
            gamer = new Hero(string, 100, 100, 100, 100, 100);
            System.out.println(String.format("The name of the hero %s. Stats: S - %d, D - %d; Inventory: gold = %d ", gamer.getName(), gamer.getStrength(), gamer.getDexterity(), gamer.getGold()));
            pLocation();
        }
        switch (string) {
            case "1": {
                System.out.println("The merchant has nothing for you");
                reader.readLine();
                pLocation();
                command(reader.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "Yes":
                command("2");
                break;
            case "No": {
                pLocation();
                command(reader.readLine());
            }
        }
        command(reader.readLine());
    }

    private static void commitFight() {
        battle.fight(gamer, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s won! HP: %d XP: %d, Gold: %d.", gamer.getName(), gamer.getHP(), gamer.getXp(), gamer.getGold()));
                System.out.println("Continue the hike? (Yes/No)");
                try {
                    command(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
            }
        });
    }

    private static Character createMonster() {
        int random = (int) (Math.random() * 100);
        if (random > 50) return new Goblin("Goblin", 10, 10, 10, 100, 20);
        else return new Skeleton("Skeleton", 10, 20, 20, 100, 20);
    }


    private static void pLocation() {
        System.out.println("Where are we going?");
        System.out.println("1. Merchant");
        System.out.println("2. Dark forest");
        System.out.println("3. The end of the hike");
    }


}
