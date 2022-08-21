package com.kacperKwiatkowski.tweetApp.dto.tweet;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WallDto {
    List<ThreadDto> threads;

    public WallDto sortThreadByPostTime() {
        List<ThreadDto> threadDtoList = new ArrayList<>(this.threads);
        Collections.sort(threadDtoList);
        this.threads = threadDtoList;
        return this;
    }
}
