package xyz.itshark.confworkshop.graphqlworkshop.input;
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

import lombok.Data;
import xyz.itshark.confworkshop.graphqlworkshop.pojo.Speaker;

@Data
public class SpeakerInput {
    private String name;
    private String twitter;
    private String shortBio;

    public Speaker toSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setName(name);
        speaker.setTwitter(twitter);
        speaker.setShortBio(shortBio);
        return speaker;
    }
}
