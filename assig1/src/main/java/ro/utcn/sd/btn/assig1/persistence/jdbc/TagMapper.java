package ro.utcn.sd.btn.assig1.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tag(rs.getInt("id"),
                rs.getString("tag_name"));
    }

}
