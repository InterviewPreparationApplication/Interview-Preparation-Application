show tables;
Alter Table SubjectsTypes modify column Title text;

Alter Table SubjectsTypes modify column Subjects text;

Select Subjectid,Subjects,Title from SubjectsTypes;

INSERT INTO  SubjectsTypes (Subjects,Title) values('Operating system','Computer Science And Engineering');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Data Structure and Alogrithms','Computer Science And Engineering');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Programming Language','Computer Science And Engineering');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Object-Oriented Programming','Computer Science And Engineering');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Network Security','Computer Science And Engineering');

INSERT INTO  SubjectsTypes (Subjects,Title) values('Data Visualization','Data Science');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Exploratory  Data analysis','Data Science');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Statistics','Data Science');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Big Data','Data Science');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Business intelligence','Data Science');

INSERT INTO  SubjectsTypes (Subjects,Title) values('Deep Learning','Artificial Intelligence');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Machine Learning','Artificial Intelligence');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Artificial Intelligence','Artificial Intelligence');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Computer vision','Artificial Intelligence');
INSERT INTO  SubjectsTypes (Subjects,Title) values('AI for Everyone','Artificial Intelligence');

INSERT INTO  SubjectsTypes (Subjects,Title) values('SEO analytics','Digital Marketing');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Facebook Analytics','Digital Marketing');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Market research','Digital Marketing');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Facebook ads','Digital Marketing');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Email marketing','Digital Marketing');

INSERT INTO  SubjectsTypes (Subjects,Title) values('Cyber Defense','Cybersecurity');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Cyber Ethics, Cyber Law and Cyber Policy','Cybersecurity');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Digital Forensics','Cybersecurity');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Information Systems','Cybersecurity');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Data Communications & Networking','Cybersecurity');

INSERT INTO  SubjectsTypes (Subjects,Title) values('Public Health','Healthcare');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Patient care','Healthcare');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Health Informatics','Healthcare');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Nursing','Healthcare');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Healthcare Management','Healthcare');

INSERT INTO  SubjectsTypes (Subjects,Title) values('Cryptocurrency','Blockchain');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Blockchain interoperability','Blockchain');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Cryptography','Blockchain');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Blockchain','Blockchain');
INSERT INTO  SubjectsTypes (Subjects,Title) values('Blockchain fundamentals','Blockchain');

Select Questionid,SubjectsTypes.Title,SubjectsTypes.Subjects,Question,Answer  from SubjectsTypes,DemoQuestions where DemoQuestions.Subjectid = SubjectsTypes.Subjectid;

INSERT INTO FeedbackRecords (Interviewerid,Userid,Feedback) values(2000,6,'Need Improvement');

INSERT into DemoQuestions (Question,Answer)  values('hello','say') where DemoQuestions.Subjectid = 1;

INSERT INTO DemoQuestions (Question, Answer,Subjectid) values ('hello', 'say' ,1);


Select * from Users;
Select * from DemoQuestions;
Delete from DemoQuestions ;



