package home.hlazkov.metric_server.db.repo;

import home.hlazkov.metric_server.db.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metric, Long> {

    @Query(value = "select m from Metric m where m.createDate between :fromDate and :toDate")
    List<Metric> findByCreateDate(LocalDateTime fromDate, LocalDateTime toDate);
}
