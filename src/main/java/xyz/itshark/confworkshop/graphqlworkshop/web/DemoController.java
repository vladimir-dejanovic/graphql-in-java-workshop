package xyz.itshark.confworkshop.graphqlworkshop.web;

/*
Code used in my workshop for GraphQL in Java World
Copyright (C) 2018  Vladimir Dejanović

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Attendee;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.ConfSession;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Speaker;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Workshop;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.ConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerWorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.WorkshopRepository;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    SpeakerRepository speakerRepository;

    @Autowired
    AttendeeRepository attendeeRepository;

    @Autowired
    ConfSessionRepository confSessionRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    @Autowired
    SpeakerWorkshopRepository speakerWorkshopRepository;

    @RequestMapping(path = "/speakers")
    public List<Speaker> allSpekers() {
        return speakerRepository.findAll();
    }

    @RequestMapping (path = "/speakers/{id}")
    public Speaker speakerById(@PathVariable Long id) {
        Speaker speaker = speakerRepository.getOne(id);
        List list = speakerWorkshopRepository.findAllBySpeakerId(id);
        return  speakerRepository.getOne(id);
    }

    @RequestMapping(path = "/attendees")
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    @RequestMapping(path = "/attendees/{id}")
    public Attendee getAttendeeById(@PathVariable Long id) {
        return attendeeRepository.getOne(id);
    }

    @RequestMapping(path ="/confsessions")
    public List<ConfSession> getAllConfSessions() {
        return confSessionRepository.findAll();
    }

    @RequestMapping(path ="/confsessions/{id}")
    public ConfSession getConfSessionsById(@PathVariable Long id) {
        return confSessionRepository.getOne(id);
    }

    @RequestMapping(path="/workshops")
    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    @RequestMapping(path="/workshops/{id}")
    public Workshop getWorkshopById(@PathVariable Long id) {
        return workshopRepository.getOne(id);
    }
}
