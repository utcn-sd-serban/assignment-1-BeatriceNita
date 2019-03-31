package ro.utcn.sd.btn.assig1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.btn.assig1.exception.TagNotFoundException;
import ro.utcn.sd.btn.assig1.exception.UserNotFoundException;
import ro.utcn.sd.btn.assig1.model.Tag;
import ro.utcn.sd.btn.assig1.model.User;
import ro.utcn.sd.btn.assig1.persistence.api.RepositoryFactory;
import ro.utcn.sd.btn.assig1.persistence.api.TagRepository;
import ro.utcn.sd.btn.assig1.persistence.api.UserRepository;

import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class TagManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Tag> listTags() {
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional
    public Tag addTag(String name) {
        return repositoryFactory.createTagRepository().save(new Tag(name));
    }

    @Transactional
    public void removeTag(int id) {
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findById(id).orElseThrow(TagNotFoundException::new);
        repository.remove(tag);
    }

    @Transactional
    public Tag findTag(String name){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findByName(name).orElseThrow(TagNotFoundException::new);
        return tag;
    }

}
