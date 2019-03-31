package ro.utcn.sd.btn.assig1.persistence.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.persistence.api.QuestionRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateQuestionRepository implements QuestionRepository{
    private final EntityManager entityManager;

    @Override
    public List<Question> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        query.select(query.from(Question.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Question save(Question question) {
        if (question.getId() == 0) {
            entityManager.persist(question);
            return question;
        } else {
            return entityManager.merge(question);
        }
    }

    @Override
    public void remove(Question question) {
        entityManager.remove(question);
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(entityManager.find(Question.class, id));
    }

    @Override
    public List<Question> findByTag(Tag tag) {
        return null;
    }

    @Override
    public List<Question> findByTitle(String title){
        return null;
    }

}

