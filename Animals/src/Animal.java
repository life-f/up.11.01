public abstract class Animal extends EntityLiving{
    protected boolean catPet;

    public Animal(int id, String name, boolean catPet) {
        super(id, name);
        this.catPet = catPet;
    }
}
