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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPosterId() {
        return posterId;
    }

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    public boolean mentions(User user) {
        return this.content.contains("@"+user.getUsername());
    }

    public Set<Long> getLikedBy() {
        return this.likedBy;
    }

    public void like(long userId) {
        this.likedBy.add(userId);
    }

    public void unlike(long userId) {
        this.likedBy.remove(userId);
    }

    public boolean isLikedBy(long userId) {
        return this.likedBy.contains(userId);
    }

    public void toggleLike(long userId) {
        if(this.isLikedBy(userId)) {
            this.unlike(userId);
        } else {
            this.like(userId);
        }
    }

    public Integer getLikeCount() {
        return this.likedBy.size();
    }

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
