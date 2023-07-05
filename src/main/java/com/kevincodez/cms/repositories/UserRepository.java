package com.kevincodez.cms.repositories;

import com.kevincodez.cms.models.User;
import com.kevincodez.cms.queryresults.CourseEnrollmentQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    @Query("MATCH (user:User {username: $username}) " +
            "MATCH (course:Course {identifier: $identifier}) " +
            "RETURN EXISTS((user)-[:ENROLLED_IN]->(course));")
    Boolean findEnrollmentStatus(String username, String identifier);

    @Query("MATCH (user:User {username: $username}) " +
            "MATCH (course:Course {identifier: $identifier}) " +
            "CREATE (user)-[:ENROLLED_IN]->(course) " +
            "RETURN user, course")
    CourseEnrollmentQueryResult createEnrollmentRelationship(String username, String identifier);
}
