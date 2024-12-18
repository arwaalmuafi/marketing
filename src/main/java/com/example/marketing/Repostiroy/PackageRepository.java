package com.example.marketing.Repostiroy;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.marketing.Model.Package;

public interface PackageRepository extends JpaRepository<Package, Integer> {
    Package findPackageById(Integer id);

}
