import java.util.HashSet;
import java.util.Set;

public class Post {

    private Long posterId;
    private String title;
    private String content;
    private Set<Long> likedBy;

    public Post() {
        this.posterId = null;
        this.title = "";
        this.content = "";
        this.likedBy = new HashSet<Long>();
    }

    public Post(long posterId, String title, String content) {
        this.title = title;
        this.content = content;
        this.posterId = posterId;
        this.likedBy = new HashSet<Long>();
    }

    public Post(long posterId, Set<Long> likedBy, String title, String content) {
        this.title = title;
        this.content = content;
        this.posterId = posterId;
        this.likedBy = likedBy;
    }

    
    /** 
     * @return String
     */
    public String getTitle() {
        return title;
    }

    
    /** 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    /** 
     * @return String
     */
    public String getContent() {
        return content;
    }

    
    /** 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    
    /** 
     * @return long
     */
    public long getPosterId() {
        return posterId;
    }

    
    /** 
     * @param posterId
     */
    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    
    /** 
     * Gets if the post mentions a user.
     * @param user user to check.
     * @return boolean if the post mentions a user.
     */
    public boolean mentions(User user) {
        return this.content.contains("@"+user.getUsername());
    }

    
    /** 
     * Get the ids of all the users to have liked the post.
     * @return Set<Long> The ids of all the users to have liked the post.
     */
    public Set<Long> getLikedBy() {
        return this.likedBy;
    }

    
    /** 
     * Makes a user like the post.
     * @param userId user of who's like to add.
     */
    public void like(long userId) {
        this.likedBy.add(userId);
    }

    
    /** 
     * Makes a user unlike the post.
     * @param userId user of who's like to remove.
     */
    public void unlike(long userId) {
        this.likedBy.remove(userId);
    }

    
    /** 
     * Gets if the user has liked the post.
     * @param userId Id of user to check.
     * @return if the user has liked the post.
     */
    public boolean isLikedBy(long userId) {
        return this.likedBy.contains(userId);
    }

    
    /** 
     * Makes user like post if they don't already and unlike it if they do.
     * @param userId id of user who's like is being toggled.
     */
    public void toggleLike(long userId) {
        if(this.isLikedBy(userId)) {
            this.unlike(userId);
        } else {
            this.like(userId);
        }
    }

    
    /** 
     * Get how many likes the post has.
     * @return How many likes the post has.
     */
    public Integer getLikeCount() {
        return this.likedBy.size();
    }

    
    /** 
     * Gets the data of the post as a single string.
     * @return Data of the post as a single string.
     */
    @Override
    public String toString() {
        String header = this.posterId.toString() + '\n';
        header += likedBy.size() + "\n";
        for (Long liker : likedBy) {
            header += liker.toString() + '\n';
        }
        header+= this.title + '\n';
        header += this.content + '\3';
        return header;
    }
}
