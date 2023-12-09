package models;

public class Wishlist {
    // ################ Properties #################
    private Integer wishlistId;
    private Post post;
    private User user;
    private Status status;
    
    // ################ Constructors #################
    public Wishlist(){

    }
    // ################ Other Methods #################
    
    // ################ Getters-Setters #################
    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    
}
