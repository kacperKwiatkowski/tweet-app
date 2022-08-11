package com.kacperKwiatkowski.tweetApp.mapper;

import com.kacperKwiatkowski.tweetApp.dto.TweetDto;
import com.kacperKwiatkowski.tweetApp.model.TweetEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TweetMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public TweetDto toDto(TweetEntity tweetToConvert) {
        return modelMapper
                .map(tweetToConvert, TweetDto.class);
    }
}
