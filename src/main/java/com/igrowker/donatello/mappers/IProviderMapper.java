package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ProviderDTO;
import com.igrowker.donatello.models.ProviderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProviderMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "id", source = "id")
    ProviderDTO ProviderToProviderDTO(ProviderEntity providerEntity);

    @Mapping(target = "user.id",source = "userId")
    @Mapping(target = "id",source = "id")
    ProviderEntity ProviderDtoToProvider(ProviderDTO providerDTO);

    List<ProviderDTO> ProviderToProviderDtoList(List<ProviderEntity> providerEntities);


    // todo VERIFICAR FUNCIONAMIENTO DE ESTE MAPPER
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "id",source = "id")
    void updateProvider(@MappingTarget ProviderEntity provider, ProviderDTO providerDTO);



}
