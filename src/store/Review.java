package store;

import java.time.LocalDateTime;

public class Review {
    private int id;
    private int orderId;
    private float rating;
    private String commentTitle;
    private String comment;
    private LocalDateTime reviewDate;

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
        if (id < 1) {
            throw new IllegalArgumentException("Constraint not respected: id must be greater than 0");
        }
        this.id = id;
    }

    /**
     * @return int return the orderId
     */
    public int getOrderId() {
        return orderId;
    }
    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        if (orderId < 1) {
            throw new IllegalArgumentException("Constraint not respected: orderId must be greater than 0");
        }
        this.orderId = orderId;
    }

    /**
     * @return float return the rating
     */
    public float getRating() {
        return rating;
    }
    /**
     * @param rating the rating to set
     */
    public void setRating(float rating) {
        if (rating < 0 || rating > 5 || rating * 2 != Math.floor(rating * 2)) {
            throw new IllegalArgumentException("Constraint not respected: rating must be between 0 and 5 with one decimal place");
        }
        this.rating = rating;
    }

    /**
     * @return String return the commentTitle
     */
    public String getCommentTitle() {
        return commentTitle;
    }
    /**
     * @param commentTitle the commentTitle to set
     */
    public void setCommentTitle(String commentTitle) {
        if (this.comment != null && (commentTitle == null || commentTitle.isEmpty())) {
            throw new IllegalArgumentException("Constraint not respected: commentTitle is required if comment is provided");
        }
        this.commentTitle = commentTitle;
    }

    /**
     * @return String return the comment
     */
    public String getComment() {
        return comment;
    }
    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return LocalDateTime return the reviewDate
     */
    public LocalDateTime getReviewDate() {
        return reviewDate;
    }
    /**
     * @param reviewDate the reviewDate to set
     */
    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }


    /**
     * Check if all constraints on the Review object is valid.
     */
    public boolean constraintCheck(int id, int orderId, float rating, String commentTitle, String comment) {
        if (id < 1 || orderId < 1 || rating < 0 || rating > 5 || rating * 2 != Math.floor(rating * 2)) {
            return false;
        }
        if (comment != null && (commentTitle == null || commentTitle.isEmpty())) {
            return false;
        }
        return true;
    }

    /**
     * Constructor with all fields
     */
    public Review(int id, int orderId, float rating, String commentTitle, String comment, LocalDateTime reviewDate) {
        if (!constraintCheck(id, orderId, rating, commentTitle, comment)) {
            throw new IllegalArgumentException("Constraint(s) not respected");
        }
        this.id = id;
        this.orderId = orderId;
        this.rating = rating;
        this.commentTitle = commentTitle;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    /**
     * Constructor for creating a new review (without ID and default date)
     */
    public Review(int orderId, float rating, String commentTitle, String comment) {
        if (!constraintCheck(0, orderId, rating, commentTitle, comment)) {
            throw new IllegalArgumentException("Constraint(s) not respected");
        }
        this.orderId = orderId;
        this.rating = rating;
        this.commentTitle = commentTitle;
        this.comment = comment;
        this.reviewDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", rating=" + rating +
                ", commentTitle='" + commentTitle + '\'' +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Review review = (Review) obj;
        return id == review.id &&
                orderId == review.orderId &&
                Float.compare(review.rating, rating) == 0 &&
                ((commentTitle == null && review.commentTitle == null) || (commentTitle != null && commentTitle.equals(review.commentTitle))) &&
                ((comment == null && review.comment == null) || (comment != null && comment.equals(review.comment))) &&
                ((reviewDate == null && review.reviewDate == null) || (reviewDate != null && reviewDate.equals(review.reviewDate)));
    }

    @Override
    public Review clone() {
        return new Review(id, orderId, rating, commentTitle, comment, reviewDate);
    }

    public void update(Review r){
        //this.orderId = r.getOrderId();
        this.id = r.getId();
        this.rating = r.getRating();
        this.commentTitle = r.getCommentTitle();
        this.comment = r.getComment();
        this.reviewDate = r.getReviewDate();
    }

}
