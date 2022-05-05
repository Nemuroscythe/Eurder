package com.switchfully.eurder.item_group.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemGroupRepository extends JpaRepository<ItemGroup, UUID> {
}
