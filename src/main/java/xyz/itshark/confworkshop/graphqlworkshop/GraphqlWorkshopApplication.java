package xyz.itshark.confworkshop.graphqlworkshop;

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

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.AttendeeWorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.ConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerConfSessionRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.SpeakerWorkshopRepository;
import xyz.itshark.confworkshop.graphqlworkshop.repository.WorkshopRepository;

@SpringBootApplication
public class GraphqlWorkshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlWorkshopApplication.class, args);
	}

	@Resource
	SpeakerRepository speakerRepository;
	
	@Resource
	AttendeeRepository attendeeRepository;
	
	@Resource
	ConfSessionRepository confSessionRepository;
	
	@Resource
	WorkshopRepository workshopRepository;

	@Resource
	AttendeeConfSessionRepository attendeeConfSessionRepository;

	@Resource
	AttendeeWorkshopRepository attendeeWorkshopRepository;

	@Resource
    SpeakerConfSessionRepository speakerConfSessionRepository;

	@Resource
    SpeakerWorkshopRepository speakerWorkshopRepository;
	
/*
	@Bean
	public ServletRegistrationBean graphQLServlet() {
		GraphQLEntryPoint graphQLEntryPoint = GraphQLEntryPoint.of();
		
		return new ServletRegistrationBean(graphQLEntryPoint,"/graphql");
	}
*/

}
