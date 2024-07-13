package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ProviderDTO;
import com.igrowker.donatello.models.ProviderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProviderMapper {
    @Mapping(target = "userId", source = "user")
    @Mapping(target = "id", source = "id")
    ProviderDTO ProviderToProviderDTO(ProviderEntity providerEntity);

    @Mapping(target = "user",source = "userId")
    @Mapping(target = "id",source = "id")
    ProviderEntity ProviderDtoToProvider(ProviderDTO providerDTO);

    List<ProviderDTO> ProviderToProviderDtoList(List<ProviderEntity> providerEntities);

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "id",source = "id")
    void updateProvider(@MappingTarget ProviderEntity provider, ProviderDTO providerDTO);



}
