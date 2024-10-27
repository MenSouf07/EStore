package store;

import java.time.LocalDate;

public class CreditCard {
    private int id;
    private int customerId;
    private String name;
    private String cardNumber;
    private int expiration_date_month;
    private int expiration_date_year;
    private String CVV;

    /**
     * @return int return the id
     */
    public int getId(){
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        if (id<1) {
            throw new IllegalArgumentException("Constraint not respected : id < 1");
        }
        this.id = id;
    }

    /**
     * @return int return the customerId
     */
    public int getCustomerId(){
        return customerId;
    }
    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        if (customerId<1){
            throw new IllegalArgumentException("Constraint not respected : customerId < 1");
        }
        this.customerId = customerId;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        if ((name.length() > 100) || (name == null)) {
            throw new IllegalArgumentException("Constraint not respected : name.length() > 100");
        }
        this.name = name;
    }

    /**
     * @return String return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }
    /**
     * @return String return the cardNumber but crypted
     */
    public String getCryptCardNumber() {
        return "**** **** **** "+cardNumber.substring(cardNumber.length() - 4);
    }
    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        if ((cardNumber == null) || (!cardNumber.matches("(\\d{4} ?){3}\\d{4}"))) {
            throw new IllegalArgumentException("Constraint not respected : cardNumber.length() > 19");
        }
        this.cardNumber = cardNumber;
    }

    /**
     * @return int return the expiration_date_month
     */
    public int getExpiration_date_month() {
        return expiration_date_month;
    }
    /**
     * @param expiration_date_month the expiration_date_month to set
     */
    public void setExpiration_date_month(int expiration_date_month) {
        if ((expiration_date_month < 1) || (expiration_date_month > 12)){
            throw new IllegalArgumentException("Constraint not respected : expiration_date_month not between 1 and 12");
        }
        this.expiration_date_month = expiration_date_month;
    }
    
    /**
     * @return int return the expiration_date_year
     */
    public int getExpiration_date_year() {
        return expiration_date_year;
    }
    /**
     * @param expiration_date_year the expiration_date_year to set
     */
    public void setExpiration_date_year(int expiration_date_year) {
        if (expiration_date_month < LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Constraint not respected : expiration_date_year < "+LocalDate.now().getYear());
        }
        this.expiration_date_year = expiration_date_year;
    }

    /**
     * @return String return the CVV
     */
    public String getCVV() {
        return CVV;
    }
    /**
     * @param CVV the CVV to set
     */
    public void setCVV(String CVV) {
        if ((CVV == null) || (!CVV.matches("\\d{3}"))) {
            throw new IllegalArgumentException("Constraint not respected : CVV.length() > 3");
        }
        this.CVV = CVV;
    }

    public boolean constraintCheck(int i, int c, String n, String cnb, int edm, int edy, String cvv){
        if ( (i<1) || (c<1) || (n == null) || (n.length() > 100) || (cnb == null) || (!cnb.matches("(\\d{4} ?){3}\\d{4}")) || (edm<1) || (edm>12) || (edy<LocalDate.now().getYear()) || (cvv == null) || (!cvv.matches("\\d{3}"))){
            //System.out.println(i + " / " + c + " / " + n + " / " + cnb + " / " + edm + " / " + edy + " / " + cvv);
            return false;
        }
        return true;
    }

    public CreditCard(int i, int c, String n, String cnb, int edm, int edy, String cvv){
        if (!constraintCheck(i, c, n, cnb, edm, edy, cvv)){
            throw new IllegalArgumentException("Constraint(s) not respected");
        }

        this.id = i;
        this.customerId = c;
        this.name = n;
        this.cardNumber = cnb;
        this.expiration_date_month = edm;
        this.expiration_date_year = edy;
        this.CVV = cvv;
    }

    public CreditCard(int c, String n, String cnb, int edm, int edy, String cvv){
        if (!constraintCheck(1, c, n, cnb, edm, edy, cvv)){
            throw new IllegalArgumentException("Constraint(s) don't respected");
        }

        this.id = 0;
        this.customerId = c;
        this.name = n;
        this.cardNumber = cnb;
        this.expiration_date_month = edm;
        this.expiration_date_year = edy;
        this.CVV = cvv;
    }

    @Override
    public String toString() {
        return "("+id+")  " +name + "["+customerId+"] : " + getCryptCardNumber() + " - " + CVV + " ("+expiration_date_month+"/"+expiration_date_year+")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CreditCard that = (CreditCard) obj;
        return id == that.id &&
            customerId == that.customerId &&
            expiration_date_month == that.expiration_date_month &&
            expiration_date_year == that.expiration_date_year &&
            CVV.equals(that.CVV) &&
            name.equals(that.name) &&
            cardNumber.equals(that.cardNumber);
    }

    @Override
    public CreditCard clone() {
        return new CreditCard(
            this.id, 
            this.customerId, 
            this.name, 
            this.cardNumber, 
            this.expiration_date_month, 
            this.expiration_date_year, 
            this.CVV
        );
    }

    public void update(CreditCard c){
        //this.customerId = c;
        this.name = c.getName();
        this.cardNumber = c.getCardNumber();
        this.expiration_date_month = c.getExpiration_date_month();
        this.expiration_date_year = c.getExpiration_date_year();
        this.CVV = c.getCVV();
    }


    
    public static void main(String[] args) {
        String s = "266";
        System.out.println(s.matches("\\d{3}"));


        String carte = "1111111111111111";
        System.out.println(carte.matches("(\\d{4} ?){3}\\d{4}"));
  


        
    }




}
    


