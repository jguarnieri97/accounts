package ar.edu.unlam.tpi.accounts.mapper.impl;

import ar.edu.unlam.tpi.accounts.dto.request.*;
import ar.edu.unlam.tpi.accounts.mapper.UserMapper;
import ar.edu.unlam.tpi.accounts.models.*;

import ar.edu.unlam.tpi.accounts.utils.Converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public SupplierCompanyEntity toSupplierEntity(SupplierRegisterRequestDto req) {
        var entity = Converter.convertToEntity(req, SupplierCompanyEntity.class);
        var geo = new Geolocation();
        geo.setLat(req.getLat().floatValue());
        geo.setLn(req.getLn().floatValue());
        entity.setGeolocation(geo);
        entity.setIsVerified(true);
        entity.setIsActive(true);
        if (req.getLabels() != null && !req.getLabels().isEmpty()) {
            Set<LabelEntity> labelEntities = req.getLabels().stream()
                .map(tag -> LabelEntity.builder()
                        .tag(tag)
                        .supplier(entity)
                        .build())
                .collect(Collectors.toSet());
            entity.setLabels(labelEntities);
        }
        return entity;
    }

    @Override
    public WorkerEntity toWorkerEntity(WorkerRegisterRequestDto req, SupplierCompanyEntity supplier) {
        var entity = Converter.convertToEntity(req, WorkerEntity.class);
        entity.setCompany(supplier);
        entity.setIsActive(true);
        return entity;
    }

    @Override
    public ApplicantCompanyEntity toApplicantEntity(ApplicantRegisterRequestDto req) {
        var entity = Converter.convertToEntity(req, ApplicantCompanyEntity.class);
        var geo = new Geolocation();
        geo.setLat(req.getLat().floatValue());
        geo.setLn(req.getLn().floatValue());
        entity.setGeolocation(geo);
        entity.setIsActive(true);
        entity.setIsVerified(false);
        return entity;
    }
}
