insert into SPEAKER
values(1,'zika','zika je mali decak','@zika');

insert into ATTENDEE
values(1,'laza');
insert into ATTENDEE
values(2,'pera');

insert into Conf_Session
values (1,'graphql vs rest','let us look at graphql vs rest :)');
insert into Conf_Session
values (2,'conf2','this is conf session 2');

insert into ATTENDEE_CONF_SESSION
values(1,1,2);

insert into ATTENDEE_WORKSHOP
values(1,1,1);

insert into SPEAKER_CONF_SESSION
values(1,1,1);

insert into SPEAKER_WORKSHOP
values(1,1,1);

insert into WORKSHOP
values (1,'graphql in java world','let us build graphql java app','bring laptop');

