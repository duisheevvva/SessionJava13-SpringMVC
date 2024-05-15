package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.exception.MyException;
import peaksoft.repository.AgencyRepository;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class AgencyRepositoryImpl implements AgencyRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveAgency(Agency agency) {
        boolean isFalse = false;
        for (Agency agency1 : getAllAgency()) {
            if (agency1.getName().equals(agency.getName())) {
                isFalse = true;
                break;
            }
        }
        if (!isFalse) {
            entityManager.persist(agency);
        } else {
            try {
                throw new MyException("Not found");
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Agency> getAllAgency() {
        return entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
    }

    @Override
    public Agency getById(Long id) {
        boolean isFalse = false;
        try {
            for (Agency agency : getAllAgency()) {
                if (Objects.equals(agency.getId(), id)) {
                    isFalse = true;
                }
                if (isFalse) {
                    return entityManager.createQuery("select a from Agency  a where a.id = :id", Agency.class)
                            .setParameter("id", id)
                            .getSingleResult();
                } else {
                    throw new MyException(" Agency with" + id + "not found");
                }
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateAgencyById(Long id, Agency agency) {
        Agency agency1 = entityManager.find(Agency.class, id);
        if (agency1 == null) {
            try {
                throw new MyException(String.format("User with id %s not found", id));
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        agency1.setName(agency.getName());
        agency1.setCountry(agency.getCountry());
        agency1.setEmail(agency.getEmail());
        agency1.setPhoneNumber(agency.getPhoneNumber());
        agency1.setImageLink(agency.getImageLink());

    }

    @Override
    public void deleteById(Long id) {
        try {
            Agency agency1 = entityManager.find(Agency.class, id);
            if (agency1.getId().equals(id)) {
                entityManager.remove(agency1);
            } else {
                throw new MyException("Not found");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return entityManager.createQuery("select a from Agency a where a.name ilike : word", Agency.class)
                .setParameter("word", "%" + word + "%")
                .getResultList();
    }
}
