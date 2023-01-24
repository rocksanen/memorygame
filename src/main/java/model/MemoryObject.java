package Model;

public class MemoryObject implements IMemoryObject, Comparable{

    private final Integer id;
    private Boolean active;
    public MemoryObject(Integer id) {

        this.id = id;
        this.active = false;

    }
    @Override
    public Integer getIdNumber() {
        return id;
    }

    // When the piece is chosen by the player
    @Override
    public void setActive() {

        this.active = !this.active;

    }

    @Override
    public Boolean isActive() {
        return this.active;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
