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

import xyz.itshark.confworkshop.graphqlworkshop.pojo.Speaker;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.SpeakerWorkshop;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Workshop;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerWorkshopRepository;

public class WorkshopResolver implements GraphQLResolver<Workshop>{

	private final SpeakerWorkshopRepository speakerWorkshopRepository;
	private final SpeakerRepository speakerRepository;

	private WorkshopResolver(SpeakerWorkshopRepository speakerWorkshopRepository, SpeakerRepository speakerRepository) {
		this.speakerWorkshopRepository = speakerWorkshopRepository;
		this.speakerRepository = speakerRepository;
	}

	public static WorkshopResolver of(SpeakerWorkshopRepository speakerWorkshopRepository, SpeakerRepository speakerRepository) {
		return new WorkshopResolver(speakerWorkshopRepository,speakerRepository);
	}

	public List<Speaker> speakers(Workshop workshop) {

		List<SpeakerWorkshop> sw = speakerWorkshopRepository.findAllByWorkshopId(workshop.getId());

		return sw.stream()
				.map(e -> speakerRepository.findById(e.getSpeakerId()))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	
}
