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

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import xyz.itshark.confworkshop.graphqlworkshop.service.AttendeeService;
import xyz.itshark.confworkshop.graphqlworkshop.service.SpeakerService;
import xyz.itshark.confworkshop.graphqlworkshop.service.TalkService;
import xyz.itshark.confworkshop.graphqlworkshop.web.GraphQLEntryPoint;

@SpringBootApplication
public class GraphqlWorkshopApplication {


	@Resource
	private SpeakerService speakerService;

	@Resource
	private TalkService talkService;

	@Resource
	private AttendeeService attendeeService;


	public static void main(String[] args) {
		SpringApplication.run(GraphqlWorkshopApplication.class, args);
	}


//	@Bean
//	public ServletRegistrationBean graphQLServlet() {
//		return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema()).build(),"/graphql");
//	}
//
//	private static GraphQLSchema buildSchema() {
//		return SchemaParser
//				.newParser()
//				.file("graphql/schema.graphqls")
////                .dictionary()
//				.resolvers()
//				.build()
//				.makeExecutableSchema();
//	}


}
