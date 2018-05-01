package Entite;

public class Personnage {
	private int hp;
	private int maxHp;
	private int dmg;

	// constructeur
	public Personnage(int hp, int dmg) {
		super();
		this.hp = hp;
		this.maxHp = hp;
		this.dmg = dmg;
	}

	// getter and setter
	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	

}
