package ro.utcn.sd.btn.assig1;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.btn.assig1.exception.QuestionNotFoundException;
import ro.utcn.sd.btn.assig1.model.Question;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;
import ro.utcn.sd.btn.assig1.persistence.memory.InMemoryRepositoryFactory;
import ro.utcn.sd.btn.assig1.service.QuestionManagementService;

public class QuestionManagementServiceUnitTests {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createQuestionRepository().save(new Question(new User(4, "user1", "pass1"), "how to solve java error", "help"));
        factory.createQuestionRepository().save(new Question(new User(5, "user2", "pass2"), "how to solve python error", "help"));
        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        QuestionManagementService service = new QuestionManagementService(factory);

        // act - remove a student with a well-known ID
        service.removeQuestion(1);

        // assert - expect that the student was removed from the repository and the other student is still there
        Assert.assertEquals(1, factory.createQuestionRepository().findAll().size());
        Assert.assertTrue(factory.createQuestionRepository().findById(2).isPresent());
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testRemoveThrowsWithNotExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        QuestionManagementService service = new QuestionManagementService(factory);

        // act - remove a student with a non-existent ID
        service.removeQuestion(999);

        // no assert, we expect an exception (see the @Test annotation)
    }
}
