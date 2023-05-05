package model;


/**

 The MemoryObject class represents a memory object that has a unique id, a type id and can be active or inactive.

 It implements the IMemoryObject interface and the Comparable interface to compare objects by their type id.
 */
public class MemoryObject implements IMemoryObject, Comparable<MemoryObject>{

    private Integer id;
    private final Integer typeId;
    private Boolean active;



    /**

     Constructs a MemoryObject with a given id and type id. The object is initialized as inactive.

     @param id the unique identifier of the object

     @param typeId the type identifier of the object
     */
    public MemoryObject(Integer id, Integer typeId) {

        this.id = id;
        this.typeId = typeId;
        this.active = false;

    }

    /**

     Returns the id of the memory object.
     @return the id of the memory object
     */
    @Override
    public Integer getIdNumber() {
        return id;
    }

    /**

     Returns the type id of the memory object.
     @return the type id of the memory object
     */
    @Override
    public Integer getTypeId() {
        return typeId;
    }

    /**

     Toggles the active state of the memory object.
     */
    @Override
    public void setActive() {

        this.active = !this.active;

    }

    /**

     Returns whether the memory object is currently active or not.
     @return true if the object is active, false otherwise
     */
    @Override
    public Boolean isActive() {

        return this.active;
    }


    /**

     Sets the id of the memory object.
     @param id the new id of the memory object
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**

     Compares the memory object to another object by their type ids.
     @param o the memory object to compare to
     @return -1 if this object has a lower type id, 0 if they have the same type id, and 1 if this object has a higher type id
     */
    @Override
    public int compareTo(MemoryObject o) {
        return this.typeId - o.typeId;
    }


}
