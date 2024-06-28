package com.maitreya.jobms.job;

import org.springframework.data.jpa.repository.JpaRepository;

// the two params in JpaRepo are the entity with which it needs to be associated with and the
// data type of the primary key of that entity(in our case Job Entity and ID has a Long data type)
public interface JobRepository extends JpaRepository<Job, Long> {
}
