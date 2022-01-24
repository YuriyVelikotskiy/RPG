import Personage.Hero;
import Entity.Character;

public class Battle {
    public void fight(Character Hero, Character Monster, World.FightCallback fightCallback) {
        Runnable task = () -> {
            int turn = 1;
            boolean isTurnsEnded = false;
            while (!isTurnsEnded) {
                System.out.println("Turn: " + turn);
                if (turn++ % 2 != 0) {
                    isTurnsEnded = makeHit(Monster, Hero, fightCallback);//каждый нечетный
                } else {
                    isTurnsEnded = makeHit(Hero, Monster, fightCallback);//каждый четный
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    private boolean makeHit(Character att, Character def, World.FightCallback fightCallback) {
        int hit = att.attack();
        int defHP = def.getHP() - hit;

        if (hit == 0) {
            System.out.println(String.format("%s - miss!", att.getName()));
        } else {
            System.out.println(String.format("%s - hit - %d!", att.getName(), hit));
            System.out.println(String.format("%s - HP - %d", def.getName(), defHP));
        }

        if (defHP <= 0 && def instanceof Hero) {
            System.out.println("Wasted");
            fightCallback.fightLost();
            return true;
        } else if (defHP <= 0){
            System.out.println(String.format("The monster is killed, extracted Gold %d and XP %d", def.getGold(), def.getXp()));
            att.setGold(att.getGold()+def.getGold());
            att.setXp(att.getXp()+def.getXp());
            fightCallback.fightWin();
            return true;
        } else {
            def.setHP(defHP);
            return false;
        }
    }
}