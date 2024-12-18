package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Repostiroy.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfluencerService {

    private final InfluencerRepository influencerRepository;

    public List<Influencer> getAllInfluencer(){
        return influencerRepository.findAll();
    }

    public void add(Influencer influencer){
        influencerRepository.save(influencer);
    }

    public void update(Integer id ,Influencer influencer){
        Influencer influencer1 = influencerRepository.findInfluencerById(id);
        if(influencer1 == null){
            throw new ApiException("influencer not found");
        }
        influencer1.setInfluencer_name(influencer.getInfluencer_name());
        influencer1.setInfluencer_email(influencer.getInfluencer_email());
        influencer1.setInfluencer_phone(influencer.getInfluencer_phone());
        influencerRepository.save(influencer1);
    }

    public void delete(Integer id){
        Influencer influencer1 = influencerRepository.findInfluencerById(id);
        if(influencer1 == null){
            throw new ApiException("influencer not found");
        }
        influencerRepository.delete(influencer1);
    }

}
