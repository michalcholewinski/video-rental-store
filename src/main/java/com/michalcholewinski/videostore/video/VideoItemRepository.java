package com.michalcholewinski.videostore.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoItemRepository extends JpaRepository<VideoItem, Long> {
}
