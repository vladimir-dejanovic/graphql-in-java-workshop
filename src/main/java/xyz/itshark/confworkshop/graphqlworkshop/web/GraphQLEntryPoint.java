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

import com.coxautodev.graphql.tools.SchemaParser;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.ConfSession;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeWorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.ConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerWorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.WorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.resolver.AttendeeResolver;
import xyz.itshark.confworkshop.graphqlworkshop.resolver.ConfSessionResolver;
import xyz.itshark.confworkshop.graphqlworkshop.resolver.Mutation;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Person;
import xyz.itshark.confworkshop.graphqlworkshop.resolver.Query;
import xyz.itshark.confworkshop.graphqlworkshop.resolver.SpeakerResolver;
import xyz.itshark.confworkshop.graphqlworkshop.resolver.WorkshopResolver;

public class GraphQLEntryPoint extends SimpleGraphQLServlet {
	
	
    private GraphQLEntryPoint(GraphQLSchema graphQLSchema) {
        super(graphQLSchema);
    }
    
    public static GraphQLEntryPoint of(SpeakerRepository speakerRepository,
                                       AttendeeRepository attendeeRepository,
                                       ConfSessionRepository confSessionRepository,
                                       WorkshopRepository workshopRepository,
                                       AttendeeConfSessionRepository attendeeConfSessionRepository,
                                       AttendeeWorkshopRepository attendeeWorkshopRepository,
                                       SpeakerConfSessionRepository speakerConfSessionRepository,
                                       SpeakerWorkshopRepository speakerWorkshopRepository) {
    	return new GraphQLEntryPoint(buildSchema(speakerRepository, attendeeRepository, confSessionRepository, workshopRepository, attendeeConfSessionRepository,attendeeWorkshopRepository,speakerConfSessionRepository,speakerWorkshopRepository));
    }

    private static GraphQLSchema buildSchema(SpeakerRepository speakerRepository,
                                             AttendeeRepository attendeeRepository,
                                             ConfSessionRepository confSessionRepository,
                                             WorkshopRepository workshopRepository,
                                             AttendeeConfSessionRepository attendeeConfSessionRepository,
                                             AttendeeWorkshopRepository attendeeWorkshopRepository,
                                             SpeakerConfSessionRepository speakerConfSessionRepository,
                                             SpeakerWorkshopRepository speakerWorkshopRepository) {
        return SchemaParser
                .newParser()
                .file("schema.graphqls")
                .dictionary(ConfSession.class)
                .resolvers(
                        Query.of(speakerRepository, attendeeRepository, confSessionRepository, workshopRepository),
                        SpeakerResolver.of(confSessionRepository, workshopRepository, speakerConfSessionRepository, speakerWorkshopRepository),
                        AttendeeResolver.of(confSessionRepository, attendeeConfSessionRepository,attendeeWorkshopRepository,workshopRepository),
                        ConfSessionResolver.of(speakerRepository, speakerConfSessionRepository),
                        WorkshopResolver.of(speakerWorkshopRepository,speakerRepository),
                        Mutation.of(speakerRepository)
                )
                .build()
                .makeExecutableSchema();
    }

}
