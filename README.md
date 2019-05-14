# GraphQL Workshop


This is code for GraphQL workshop. 

As database H2 is used. 

In order to connect to local instance of H2 once app is deployed hit url 

http://localhost:8080/h2-console/

Make sure that URL is set to jdbc:h2:mem:testdb

## Conference simulation

This is "simulation" of conference 


Data consists of 
* Attendee
* Speaker
* Talk

Init version of POJO and repositories are already present. 
There is hook for GraphQL code which need to be implemented. 

## Enviroment

This is setup on my machine

```
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T09:58:13+02:00)
Java version: 11.0.2, vendor: AdoptOpenJDK
```

## GraphQL Dependencies

Let us  check pom.xml

.....

## Query

### Talk in GraphQL SDL

```
type Talk {
  id: ID!
  title: String!
  description: String
}

type Query {
  allTalks: [Talk]
}

schema {
  query: Query
}
```

### All Talks Query

```
	@Bean
	public ServletRegistrationBean graphQLServlet() {

		return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema(speakerService,attendeeService,talkService)).build(),"/graphql");
	}

	private static GraphQLSchema buildSchema(SpeakerService speakerService, AttendeeService attendeeService, TalkService talkService) {
		return SchemaParser
				.newParser()
				.file("graphql/schema.graphqls")
//                .dictionary()
				.resolvers(new Query(attendeeService,speakerService,talkService))
				.build()
				.makeExecutableSchema();
	}

```

```java
@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private final AttendeeService attendeeService;
    private final SpeakerService speakerService;
    private final TalkService talkService;

    public List<Talk> allTalks() {
        return talkService.findAll();
    }
}
```

### Over/under fetching

....

### Renaming fields

....

### Error

...

### Speaker in GraphQL SDL

### All Speakers Query

### Attendee in GraphQL SDL

### Speaker from Talk in GraphQL SDL

```
type Talk {
  id: ID!
  title: String!
  description: String
  speakers: [Speaker]
}
```

### Field resolver

```
	private static GraphQLSchema buildSchema(SpeakerService speakerService, AttendeeService attendeeService, TalkService talkService) {
		return SchemaParser
				.newParser()
				.file("graphql/schema.graphqls")
//                .dictionary()
				.resolvers( new Query(attendeeService,speakerService,talkService),
						new TalkReslover(speakerService))
				.build()
				.makeExecutableSchema();
	}
```

```java
@RequiredArgsConstructor
public class TalkReslover implements GraphQLResolver<Talk> {

    private final SpeakerService speakerService;

    public List<Speaker> speakers(Talk talk) {
        return speakerService.findAllSpeakersForTalk(talk);
    }

}
```

### Simplify Java Code

....

### Talk from Speaker in GraphQL SDL

### Field resolver

### conditional 

### variables

### Union in GraphQL SDL

```
union Person = Speaker | Attendee

type Query {
  allTalks: [Talk]
  allAttendees: [Attendee]
  allSpeakers: [Speaker]
  allPeople: [Person]
}
```

### Union in Java code

```
@Component
public class Query implements GraphQLQueryResolver {

    @Resource
    private AttendeeService attendeeService;
    @Resource
    private SpeakerService speakerService;
    @Resource
    private TalkService talkService;

 ....

    public List<Object> allPeople() {
        List<Attendee> attendees = attendeeService.findAll();
        List people = speakerService.findAll();

        people.addAll(attendees);
        return people;
    }

}
```

### Fragments

### Interface in GraphQL SDL

```
interface Human {
 name : String!
}

type Speaker implements Human {
    id: ID!
    name: String!
    twitter: String
    talks: [Talk]
}

type Attendee implements Human {
 id: ID!
 name: String!
}

type Query {
  allTalks: [Talk]
  allAttendees: [Attendee]
  allSpeakers: [Speaker]
  allPeople: [Person]
  allHumans: [Human]
}
```

### Interface in Java code

```java
public interface Human {

    String getName();

}

```

```java
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker implements Human{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String twitter;
}
```

```
@Component
public class Query implements GraphQLQueryResolver {

    @Resource
    private AttendeeService attendeeService;
    @Resource
    private SpeakerService speakerService;
    @Resource
    private TalkService talkService;

 ...
 
    public List<Human> allHumans() {
        List<Attendee> attendees = attendeeService.findAll();
        List people = speakerService.findAll();

        people.addAll(attendees);
        return people;
    }

}
```

## Mutation

### Adding Speaker in GraphQL SDL

```
type Mutation {
   addSpeaker(name: String): Speaker
}

schema {
  query: Query
  mutation: Mutation
}
```

### Adding Speaker in Java code

```
@Component
public class Mutation implements GraphQLMutationResolver {

    @Resource
    private SpeakerService speakerService;

    public Speaker addSpeaker(String name ) {
        Speaker speaker  = new Speaker();
        speaker.setName(name);

        return speakerService.save(speaker);
    }

}
```

### Adding Attendee in GraphQL SDL

.....

### Adding Attendee in Java code



### Adding Speaker by using InputType in GraphQL SDL

```

input SpeakerInput {
  name: String
  twitter: String
}

type Mutation {
   addSpeaker(speaker: SpeakerInput): Speaker
}
```

### Adding Speaker by using InputType in Java code

```java
import lombok.Data;

@Data
public class SpeakerInput {

    private String name;
    private String twitter;
}
```


```java
@Component
public class Mutation implements GraphQLMutationResolver {

    @Resource
    private SpeakerService speakerService;

    public Speaker addSpeaker(SpeakerInput si ) {
        Speaker speaker  = new Speaker();
        speaker.setName(si.getName());
        speaker.setTwitter(si.getTwitter());

        return speakerService.save(speaker);
    }

}

```


## Subscription

### Subscription in GraphQL SDL

```
type Score {
  title: String!
  score: Int
}

type Subscription {
  talkScores(title: String!): Score
}


schema {
  query: Query
  mutation: Mutation
  subscription: Subscription
}
```

### Subscription in Java code

```java
@Data
@Builder
public class Score {

    private String title;
    private Integer score;
}
```

```java
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import io.reactivex.BackpressureStrategy;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

    public Publisher<Score> talkScores(final String title) {
        Observable<Score> observable = Observable.create(e -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(() -> {
                Score s = Score.builder()
                               .title(title)
                               .score(Integer.valueOf((int) Math.floor(Math.random()*10)))
                               .build();
                e.onNext(s);
            }, 0, 2, TimeUnit.SECONDS);
        });

        ConnectableObservable connectableObservable = observable.share().publish();
        connectableObservable.connect();
        return connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

}

```





 
