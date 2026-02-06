package home.hlazkov.metric_server.db.mapper;

import home.hlazkov.metric_server.db.dto.MetricDto;
import home.hlazkov.metric_server.db.entity.Metric;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetricMapper {

    MetricDto toDto(Metric metric);
    Metric toEntity(MetricDto dto);

    List<MetricDto> toDtoList(List<Metric> entities);
    List<Metric> toEntityList(List<MetricDto> dtos);
}
