package xyz.itshark.confworkshop.graphqlworkshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Attendee;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.AttendeeTalk;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Talk;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeTalkRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private AttendeeTalkRepository attendeeTalkRepository;

    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    public List<Attendee> findAllAttendiesForTalk(Talk talk) {
        List<AttendeeTalk> at = attendeeTalkRepository.findAllByTalkId(talk.getId());

        return at.stream()
                .map(e -> attendeeRepository.findById(e.getAttendeeId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Attendee save(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }
}
