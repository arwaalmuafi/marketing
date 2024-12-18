package com.example.marketing.DTOs;

import com.example.marketing.Model.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class InfluencerDTO {

    private String Influencer_name;

    private String Influencer_phone;

    private String Influencer_email;

    private List<Platform> platforms;
}