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
import xyz.itshark.confworkshop.graphqlworkshop.pojo.SpeakerConfSession;
import xyz.itshark.confworkshop.graphqlworkshop.repository.ConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerWorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.WorkshopRepository;

public class SpeakerResolver implements GraphQLResolver<Speaker> {

    private final ConfSessionRepository confSessionRepository;
    private final WorkshopRepository workshopRepository;
    private final SpeakerConfSessionRepository speakerConfSessionRepository;
    private final SpeakerWorkshopRepository speakerWorkshopRepository;

    private  SpeakerResolver(ConfSessionRepository confSessionRepository, WorkshopRepository workshopRepository, SpeakerConfSessionRepository speakerConfSessionRepository, SpeakerWorkshopRepository speakerWorkshopRepository) {
        this.confSessionRepository = confSessionRepository;
        this.workshopRepository = workshopRepository;
        this.speakerConfSessionRepository = speakerConfSessionRepository;
        this.speakerWorkshopRepository = speakerWorkshopRepository;
    }

    public static SpeakerResolver of(ConfSessionRepository confSessionRepository, WorkshopRepository workshopRepository, SpeakerConfSessionRepository speakerConfSessionRepository, SpeakerWorkshopRepository speakerWorkshopRepository) {
        return new SpeakerResolver(confSessionRepository,workshopRepository,speakerConfSessionRepository,speakerWorkshopRepository);
    }

    public List<Object>  giveTalks(Speaker speaker) {
        List<Object> list = speakerConfSessionRepository.findAllBySpeakerId(speaker.getId()).stream()
                .map(e -> confSessionRepository.findById(e.getConSessionId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        speakerWorkshopRepository.findAllBySpeakerId(speaker.getId()).stream()
                .map(e -> workshopRepository.findById(e.getWorkshopId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEachOrdered(list::add);

        return list;
	}

}
