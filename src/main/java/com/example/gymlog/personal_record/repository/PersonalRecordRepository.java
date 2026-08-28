package com.example.gymlog.personal_record.repository;

import com.example.gymlog.personal_record.model.PersonalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PersonalRecordRepository extends JpaRepository<PersonalRecord, UUID> {
}
