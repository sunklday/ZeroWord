package sunkl.jiai.com.zeroword.model;

/**
 * Created by admin on 2016/3/10.
 */
public class User {
    private String name;
    private String _id;
    private String time;
    private String wordmark;
    private Integer amount;

    public User(String name, String _id, String time, String wordmark, Integer amount) {
        this.name = name;
        this._id = _id;
        this.time = time;
        this.wordmark = wordmark;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWordmark() {
        return wordmark;
    }

    public void setWordmark(String wordmark) {
        this.wordmark = wordmark;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", _id='" + _id + '\'' +
                ", time=" + time +
                ", wordmark='" + wordmark + '\'' +
                ", amount=" + amount +
                '}';
    }
}
