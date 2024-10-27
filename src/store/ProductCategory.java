package store;


/*
 * category_id INT PRIMARY KEY AUTO_INCREMENT,
 * name VARCHAR(50) UNIQUE NOT NULL
 */
public class ProductCategory {
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
     * @return String return the name of the product category
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

    public ProductCategory(int i, String n){
        if (!constraintCheck(i,n)){
            throw new IllegalArgumentException("Constraint(s) not respected");
        }
        this.id = i;
        this.name = n;
    }
    public ProductCategory(String n){
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
        ProductCategory that = (ProductCategory) obj;
        return id == that.getId() &&
            name.equals(that.getName());
    }

    @Override
    public ProductCategory clone() {
        return new ProductCategory(
            this.id,
            this.name
        );
    }

    public void update(ProductCategory c){
        //this.id = c.getId();
        this.name = c.getName();
    }
}
