package com.example.marketing.Service;

import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.Model.Package;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Repostiroy.InfluencerRepository;
import com.example.marketing.Repostiroy.PackageRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;

    private final InfluencerRepository influencerRepository;


    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Package getPackageById(Integer id) {
        return packageRepository.findById(id).orElseThrow(()-> new ApiException("Package not found"));
    }

    public void addPackage(Package pack) {

        if (pack.getInfluencer() == null || pack.getInfluencer().getId() == null) {
            throw new ApiException("Influencer must be provided with a valid ID");
        }

        Influencer influencer = influencerRepository.findById(pack.getInfluencer().getId())
                .orElseThrow(() -> new ApiException("Influencer not found"));


        pack.setInfluencer(influencer);

        packageRepository.save(pack);
    }

    public void updatePackage(Package pack) {
        Influencer influencer = influencerRepository.findInfluencerById(pack.getId());
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }

        Package oldPackage = packageRepository.findById(pack.getId()).orElseThrow(()-> new ApiException("Package not found"));

        oldPackage.setPackage_price(pack.getPackage_price());
        oldPackage.setPackage_times(pack.getPackage_times());
        oldPackage.setAvailable(pack.isAvailable());

        packageRepository.save(oldPackage);

    }

    public void deletePackage(Integer id) {
        Package pack = packageRepository.findById(id).orElseThrow(()-> new ApiException("Package not found"));
        packageRepository.delete(pack);
    }



}
