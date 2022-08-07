package com.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.entities.PatientRecordDetails;

public interface PatientRecordsRepository extends JpaRepository<PatientRecordDetails,Integer> {

	@Transactional
	@Modifying
	@Query(value = "Update PatientRecordDetails pr SET pr.status='DISCHARGE' where pr.recordId = :recordId")//, nativeQuery = true)
	void dischargeById(@Param("recordId") int recordId);
}
