package racingcar.persistence;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Repository
public class PlayWinnerDAO {
    private SimpleJdbcInsert insertActor;

    public PlayWinnerDAO(DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_WINNER")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(int newId, String winner) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("play_result_id", newId);
        parameters.put("winner", winner);
        parameters.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(parameters).intValue();
    }
}