package com.example.marketing.Repostiroy;

import com.example.marketing.Model.Influencer;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer,Integer> {
    Influencer findInfluencerById(Integer id);
}