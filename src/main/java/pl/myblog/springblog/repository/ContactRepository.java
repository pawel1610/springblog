package pl.myblog.springblog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.Contact;
import pl.myblog.springblog.service.ContactService;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

}
