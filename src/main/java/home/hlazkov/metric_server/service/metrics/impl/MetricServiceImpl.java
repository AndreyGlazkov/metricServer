package home.hlazkov.metric_server.service.metrics.impl;

import home.hlazkov.metric_server.db.dto.MetricDto;
import home.hlazkov.metric_server.db.entity.Metric;
import home.hlazkov.metric_server.db.mapper.MetricMapper;
import home.hlazkov.metric_server.db.repo.MetricsRepository;
import home.hlazkov.metric_server.enums.MetricType;
import home.hlazkov.metric_server.service.metrics.MetricService;
import home.hlazkov.metric_server.service.mqtt.MetricMqttData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricMapper mapper;
    private final MetricsRepository repository;

    public MetricServiceImpl(MetricMapper mapper, MetricsRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public MetricDto addMetric(MetricDto metric) {
        var metricEntity = mapper.toEntity(metric);
        var saveEntity = repository.save(metricEntity);
        return mapper.toDto(saveEntity);
    }

    @Override
    public List<MetricDto> addMetricList(List<MetricDto> metricList) {
        var metricEntityList = mapper.toEntityList(metricList);
        var saveEntityList = repository.saveAll(metricEntityList);
        return mapper.toDtoList(saveEntityList);
    }

    @Override
    public List<MetricDto> findByDateTime(LocalDateTime fromTime, LocalDateTime toTime) {
        var entityList = repository.findByCreateDate(fromTime,toTime);
        return mapper.toDtoList(entityList);
    }

    @Override
    public List<MetricDto> findLast(Integer count) {
        return List.of();
    }

    @Override
    @Transactional
    public void saveMetricData(MetricMqttData data) {
        saveTemperature(data.getTemperature());
        saveHumidity(data.getHumidity());
    }

    private void saveTemperature(Float value) {
        var entity = new Metric();
        entity.setType(MetricType.TEMPERATURE);
        entity.setValue(value);
        repository.save(entity);
    }

    private void saveHumidity(Float value) {
        var entity = new Metric();
        entity.setType(MetricType.HUMIDITY);
        entity.setValue(value);
        repository.save(entity);
    }
}
