package model;

public class MemoryObject implements IMemoryObject, Comparable<MemoryObject>{

    private Integer id;
    private final Integer typeId;
    private Boolean active;
    public MemoryObject(Integer id, Integer typeId) {

        this.id = id;
        this.typeId = typeId;
        this.active = false;

    }
    @Override
    public Integer getIdNumber() {
        return id;
    }

    @Override
    public Integer getTypeId() {
        return typeId;
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
    public void setId(int id) {

        this.id = id;
    }

    //false -1 and 0 is true
    @Override
    public int compareTo(MemoryObject o) {
        return this.typeId - o.typeId;
    }


}
