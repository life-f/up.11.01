public abstract class EntityLiving {
    protected static int idCounter =0;
    protected int id;
    protected String name;

    abstract String getType();

    public EntityLiving(int id, String name) {
        this.id = id;
        this.name = name;
        idCounter++;
    }
}
