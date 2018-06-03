package xyz.itshark.confworkshop.graphqlworkshop.resolver;

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

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Attendee;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.ConfSession;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Speaker;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Workshop;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.ConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.WorkshopRepository;

import java.util.List;

public class Query implements GraphQLQueryResolver {

	private final SpeakerRepository speakerRepository;
	private final AttendeeRepository attendeeRepository;
	private final ConfSessionRepository confSessionRepository;
	private final WorkshopRepository workshopRepository;
	
	
	private Query(SpeakerRepository speakerRepository,AttendeeRepository a, ConfSessionRepository c, WorkshopRepository w) {
		this.speakerRepository = speakerRepository;
		this.attendeeRepository = a;
		this.confSessionRepository = c;
		this.workshopRepository = w;
	}
	
	public static Query of(SpeakerRepository s, AttendeeRepository a, ConfSessionRepository c, WorkshopRepository w) {
		return new Query(s,a,c,w);
	}
	
    public List<Speaker> allSpeakers() {
    	return speakerRepository.findAll();
    }

    public List<Attendee> allAttendees() {
        return attendeeRepository.findAll();
    }

    public List<Workshop> allWorkshops () {
        return workshopRepository.findAll();
    }
    
    public List<ConfSession> allSessions() {
    	return confSessionRepository.findAll();
    }

}
