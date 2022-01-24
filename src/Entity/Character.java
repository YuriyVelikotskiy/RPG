package Entity;

public abstract class Character implements Fighter {

    private String name;
    private int hp;
    private int strength;
    private int dexterity;
    private int gold;
    private int xp;

    public Character(String name, int hp, int strength, int dexterity, int gold, int xp) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.gold = gold;
        this.xp = xp;
    }

    @Override
    public String toString() {
        return String.format("%s здоровье: %d", name, hp);
    }

    @Override
    public int attack() {
        return getRandomValue() > 50 ? powerAttack() : regAttack();
    }

    private int regAttack() {
        if (dexterity * 3 > getRandomValue()) return strength;
        else return 0;
    }

    private int powerAttack() {
        if (dexterity * 3 > getRandomValue()) return strength * 2;
        else return 0;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
