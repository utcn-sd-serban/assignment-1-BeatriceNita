package ro.utcn.sd.btn.assig1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private User author;
    private String title;
    private Tag tag;
    private String text;
    private Timestamp creationDate;

    public Question(User author, Timestamp creationDate, String title, Tag tag, String text){
        this.author = author;
        this.creationDate = creationDate;
        this.title = title;
        this.tag = tag;
        this.text = text;
    }

    public Question(User author, String title, String text){
        this.author = author;
        this.title = title;
        this.text = text;
    }

}
