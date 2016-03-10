package sunkl.jiai.com.zeroword.model;

/**
 * Created by admin on 2016/3/10.
 */
public class Word {
    private String id;
    private String _id;
    private String word;
    private String mean;
    private String example;
    private String degree;
    private String date;

    public Word(String id, String _id, String word, String mean, String example, String degree, String date) {
        this.id = id;
        this._id = _id;
        this.word = word;
        this.mean = mean;
        this.example = example;
        this.degree = degree;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id='" + id + '\'' +
                ", _id='" + _id + '\'' +
                ", word='" + word + '\'' +
                ", mean='" + mean + '\'' +
                ", example='" + example + '\'' +
                ", degree='" + degree + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
