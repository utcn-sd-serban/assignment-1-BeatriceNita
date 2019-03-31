package ro.utcn.sd.btn.assig1.persistence.jdbc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.persistence.api.TagRepository;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "assig1.repository-type", havingValue = "JDBC")
public class JdbcTagRepository implements TagRepository {

    private final JdbcTemplate template;

    public JdbcTagRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM tag", new TagMapper());
    }

    @Override
    public Tag save(Tag tag) {
        if (tag.getId() == null) {
            tag.setId(insert(tag));
        } else {
            update(tag);
        }
        return tag;
    }

    @Override
    public void remove(Tag tag) {
        template.update("DELETE FROM tag WHERE id = ?", tag.getId());
    }

    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> tags = template.query("SELECT * FROM tag WHERE id = ?", new TagMapper(), id);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    public Optional<Tag> findByName(String name) {
        List<Tag> tags = template.query("SELECT * FROM tag WHERE tag_name = ?", new TagMapper(), name);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    private int insert(Tag tag){
        SimpleJdbcInsert ins = new SimpleJdbcInsert(template);
        ins.setTableName("tag");
        ins.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("tag_name", tag.getName());

        return ins.executeAndReturnKey(data).intValue();
    }

    private void update(Tag tag){
        template.update("UPDATE tag SET tag_name = ? WHERE id = ?",
                tag.getName(), tag.getId());
    }

}

