package xyz.itshark.confworkshop.graphqlworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.SpeakerTalk;

import java.util.List;

@Repository
public interface SpeakerTalkRepository extends JpaRepository<SpeakerTalk,Long> {
    List<SpeakerTalk> findAllBySpeakerId(Long speakerId);
    List<SpeakerTalk> findAllByTalkId(Long talkId);
}
