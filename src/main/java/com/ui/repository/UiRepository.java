package com.ui.repository;

import com.ui.entity.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UiRepository extends MongoRepository<Device, String> {

    List<Device> findByStartSessionGreaterThanAndEndSessionLessThan(Long startSession, Long endSession);

    List<Device> findDistinctDevicesByName(String name);

}
