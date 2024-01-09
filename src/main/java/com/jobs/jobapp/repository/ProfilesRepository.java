package com.jobs.jobapp.repository;

import com.jobs.jobapp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
}
