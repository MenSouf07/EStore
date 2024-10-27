package store;



public class Brand {
    private int id;
    private String name;

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the brand name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    public boolean constraintCheck(int i, String n){
        if ((i<1) || (n == null) || (n.length() > 50)){
            //System.out.println(i + " / " + n);
            return false;
        }
        return true;
    }

    public Brand(int i, String n){
        if (!constraintCheck(i,n)){
            throw new IllegalArgumentException("Constraint(s) not respected");
        }
        this.id = i;
        this.name = n;
    }
    public Brand(String n){
        if (!constraintCheck(1,n)){
            throw new IllegalArgumentException("Constraint(s) not respected");
        }
        this.id = 0;
        this.name = n;
    }

    @Override
    public String toString() {
        return name + "["+ id +"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Brand that = (Brand) obj;
        return id == that.getId() &&
            name.equals(that.getName());
    }

    @Override
    public Brand clone() {
        return new Brand(
            this.id,
            this.name
        );
    }

    public void update(Brand c){
        //this.id = c.getId();
        this.name = c.getName();
    }

}
