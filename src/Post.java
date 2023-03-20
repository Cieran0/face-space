public class Post {

    private Long posterId;
    private String title;
    private String content;

    public Post() {
        this.posterId = null;
        this.title = "";
        this.content = "";
    }

    public Post(long posterId, String title, String content) {
        this.title = title;
        this.content = content;
        this.posterId = posterId;
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


}
