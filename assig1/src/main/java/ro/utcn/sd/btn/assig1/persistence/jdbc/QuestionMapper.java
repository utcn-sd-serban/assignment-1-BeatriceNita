package ro.utcn.sd.btn.assig1.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(rs.getInt("id"),
                new User(rs.getInt("author_id")),
                rs.getString("title"),
                null,
                rs.getString("text"),
                rs.getTimestamp("create_date")
                );
    }

}