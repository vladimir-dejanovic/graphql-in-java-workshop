package xyz.itshark.confworkshop.graphqlworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Speaker;


@Repository
public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
}
