package ro.utcn.sd.btn.assig1.persistence.api;

public interface RepositoryFactory {

    QuestionRepository createQuestionRepository();
    UserRepository createUserRepository();
    TagRepository createTagRepository();
}
