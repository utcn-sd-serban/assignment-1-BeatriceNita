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
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Question question;
    private User author;
    private String title;
    private String text;
    private Timestamp creationDate;

    public Answer(User author, Question question, Timestamp creationDate, String title, String text){
        this.author = author;
        this.creationDate = creationDate;
        this.title = title;
        this.text = text;

    }
}
