package com.example.marketing.Repostiroy;

import com.example.marketing.Model.BookingPackage;
import com.example.marketing.Model.Package;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingPackageRepository extends JpaRepository<BookingPackage, Integer> {

    BookingPackage findBookingPackageById(Integer id);

    List<BookingPackage> findAllByInfluencerId(Integer influencerId);

    List<BookingPackage> findAllByCompanyIdAndBookingStatus(Integer companyId, String status);

}
