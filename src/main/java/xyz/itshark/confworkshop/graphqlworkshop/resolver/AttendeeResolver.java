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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.coxautodev.graphql.tools.GraphQLResolver;

import xyz.itshark.confworkshop.graphqlworkshop.pojo.Attendee;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.AttendeeConfSession;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.AttendeeWorkshop;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeWorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.ConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.WorkshopRepository;

public class AttendeeResolver implements GraphQLResolver<Attendee> {

	private final AttendeeConfSessionRepository attendeeConfSessionRepository;
	private final ConfSessionRepository confSessionRepository;
	private final AttendeeWorkshopRepository attendeeWorkshopRepository;
	private final WorkshopRepository workshopRepository;

	private AttendeeResolver(ConfSessionRepository confSessionRepository, AttendeeConfSessionRepository a,AttendeeWorkshopRepository aw, WorkshopRepository w) {
		this.confSessionRepository = confSessionRepository;
		this.attendeeConfSessionRepository = a;
		this.attendeeWorkshopRepository = aw;
		this.workshopRepository = w;
	}

	public static AttendeeResolver of(ConfSessionRepository c,AttendeeConfSessionRepository a, AttendeeWorkshopRepository aw, WorkshopRepository w) {
		return new AttendeeResolver(c,a,aw,w);
	}

	public List<Object> listen(Attendee attendee) {

	    List<AttendeeConfSession> as = attendeeConfSessionRepository.findAttendeeConfSessionByAttendeeId(attendee.getId());

	    List<Object> list = as.stream()
                .map( e -> confSessionRepository.findById(e.getConfSessionId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());


	    attendeeWorkshopRepository.findAllByAttendeeId(attendee.getId()).stream()
				.map(e -> workshopRepository.findById(e.getWorkshopId()))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.forEachOrdered(e -> list.add(e));

		return list;
	}

}
