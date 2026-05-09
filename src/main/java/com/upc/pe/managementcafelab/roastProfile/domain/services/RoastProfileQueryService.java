package com.upc.pe.managementcafelab.roastProfile.domain.services;

import com.upc.pe.managementcafelab.roastProfile.domain.model.aggregates.RoastProfile;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetAllRoastProfilesQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetFavoriteRoastProfilesQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetRoastProfileByIdQuery;
import com.upc.pe.managementcafelab.roastProfile.domain.model.queries.GetRoastProfilesByCoffeeLotIdQuery;

import java.util.List;
import java.util.Optional;

public interface RoastProfileQueryService {
    Optional<RoastProfile> handle(GetRoastProfileByIdQuery query);
    List<RoastProfile> handle(GetAllRoastProfilesQuery query);
    List<RoastProfile> handle(GetRoastProfilesByCoffeeLotIdQuery query);
    List<RoastProfile> handle(GetFavoriteRoastProfilesQuery query);
}
