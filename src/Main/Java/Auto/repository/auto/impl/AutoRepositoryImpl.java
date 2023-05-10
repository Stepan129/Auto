package Auto.repository.auto.impl;

import Auto.dto.AutoPatch;
import Auto.dto.quary.params.AutoFitterOptions;
import Auto.entity.AutoEntity;
import Auto.repository.auto.AutoRepository;
import lombok.AllArgsConstructor;
import Auto.exception.DataConflictException;
import Auto.exception.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AutoRepositoryImpl implements AutoRepository {

    private static final String INSERT_AUTO_QUERY = """
                INSERT INTO autos (
                    id,
                    type,
                    brand,
                    model,
                    year,
                    country,
                    condition,
                    mileage,
                    price,
                    info
                ) VALUES (
                    :id,
                    :type,
                    :brand,
                    :model,
                    :year,
                    :country,
                    :condition,
                    :mileage,
                    :price,
                    :info
                )
            """;

    private static final String SELECT_AUTOS_QUERY = """
            SELECT
                id,
                type,
                brand,
                model,
                year,
                country,
                condition,
                mileage,
                price,
                info
            FROM autos
            """;

    private static final String SELECT_AUTO_BY_ID_QUERY = """
        SELECT
                id,
                type,
                brand,
                model,
                year,
                country,
                condition,
                mileage,
                price,
                info
        FROM autos
        WHERE id = :id
        """;




    private static final String UPDATE_AUTO_BY_ID_QUERY = """
            UPDATE autos SET
                type= :type,
                brand= :brand,
                model= :model,
                year= :year,
                country= :country,
                condition= :condition,
                mileage= :mileage,
                price= :price,
                info= :info
            WHERE id = :id
            """;

    private static final String SELECT_AUTO_COUNT_QUERY = "SELECT COUNT(1) FROM autos";

    private static final String PATCH_AUTO_BY_ID_QUERY_TEMPLATE = """
            UPDATE autos SET
                %s
            WHERE id = :id
            """;

    private static final String DELETE_AUTO_BY_ID_QUERY = """
            DELETE FROM autos WHERE id = :id
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<AutoEntity> AUTO_ROW_MAPPER = (rs, rowNum) -> {
        AutoEntity entity = new AutoEntity();

        entity.setId(rs.getObject("id", Long.class));
        entity.setType(rs.getString("type"));
        entity.setBrand(rs.getString("brand"));
        entity.setModel(rs.getString("model"));
        entity.setYear(rs.getInt("year"));
        entity.setCountry(rs.getString("country"));
        entity.setCondition(rs.getString("condition"));
        entity.setMileage(rs.getDouble("mileage"));
        entity.setPrice(rs.getDouble("price"));
        entity.setInfo(rs.getString("info"));

        return entity;
    };





    @Override
    public AutoEntity create(AutoEntity auto) {
        SqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("id", auto.getId())
                .addValue("type", auto.getType())
                .addValue("brand", auto.getBrand())
                .addValue("model", auto.getModel())
                .addValue("year", auto.getYear())
                .addValue("country", auto.getCountry())
                .addValue("condition", auto.getCondition())
                .addValue("mileage", auto.getMileage())
                .addValue("price", auto.getPrice())
                .addValue("info", auto.getInfo());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(INSERT_AUTO_QUERY, sqlParameters, keyHolder);
        } catch (DuplicateKeyException e) {
            if (e.getCause().getMessage().contains("duplicate key value violates unique constraint \"faculties_name_key\"")) {
                throw new DataConflictException(String.format("Faculty with name \"%s\" already exists!", auto.getType()));
            }

            throw e;
        }

        Long id = (Long) keyHolder.getKeys().get("id");
        auto.setId(id);

        return auto;
    }

    @Override
    public List<AutoEntity> findAll(AutoFitterOptions fitterOptions, Integer limit, Integer offset) {
        StringBuilder queryBuilder = new StringBuilder(SELECT_AUTOS_QUERY);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        appendConditions(queryBuilder, parameters, fitterOptions);

        if (limit != null) {
            queryBuilder.append(" LIMIT :limit");
            parameters.addValue("limit", limit);
        }

        if (offset != null && offset != 0) {
            queryBuilder.append(" OFFSET :offset");
            parameters.addValue("offset", offset);
        }

        String query = queryBuilder.toString();

        return jdbcTemplate.query(query, parameters, AUTO_ROW_MAPPER);
    }

    @Override
    public AutoEntity find(Long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_AUTO_BY_ID_QUERY, new MapSqlParameterSource("id", id), AUTO_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Auto with id " + id + " not found!");
        }
    }

    @Override
    public int count(AutoFitterOptions params) {
        StringBuilder queryBuilder = new StringBuilder(SELECT_AUTO_COUNT_QUERY);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        appendConditions(queryBuilder, parameters, params);

        String query = queryBuilder.toString();

        return jdbcTemplate.queryForObject(query, parameters, Integer.class);
    }

    @Override
    public void update(AutoEntity auto) {
        int affectedRows;
        try {
            affectedRows = jdbcTemplate.update(UPDATE_AUTO_BY_ID_QUERY, new MapSqlParameterSource()
                    .addValue("id", auto.getId())
                    .addValue("type", auto.getType())
                    .addValue("brand", auto.getBrand())
                    .addValue("model", auto.getModel())
                    .addValue("year", auto.getYear())
                    .addValue("country", auto.getCountry())
                    .addValue("condition", auto.getCondition())
                    .addValue("mileage", auto.getMileage())
                    .addValue("price", auto.getPrice())
                    .addValue("info", auto.getInfo()));

        } catch (DuplicateKeyException e) {
            if (e.getCause().getMessage().contains("duplicate key value violates unique constraint \"faculties_name_key\"")) {
                throw new DataConflictException(String.format("Faculty with name \"%s\" already exists!", auto.getType()));
            }

            throw e;
        }

        if (affectedRows == 0) {
            throw new NotFoundException("Auto with id " + auto.getId() + " not found!");
        }
    }

    @Override
    public void patch(Long id, AutoPatch autoPatch) {
        List<String> assignments = new ArrayList<>();
        MapSqlParameterSource parameters = new MapSqlParameterSource("id", id);

        if (autoPatch.isTypeUpdated()) {
            assignments.add("type = :type");
            parameters.addValue("type", autoPatch.getType());
        }

        if (autoPatch.isBrandUpdated()) {
            assignments.add("brand = :brand");
            parameters.addValue("brand", autoPatch.getBrand());
        }

        if (autoPatch.isModelUpdated()) {
            assignments.add("model = :model");
            parameters.addValue("model", autoPatch.getModel());
        }

        if (autoPatch.isYearUpdated()) {
            assignments.add("year = :year");
            parameters.addValue("year", autoPatch.getYear());
        }

        if (autoPatch.isCountryUpdated()) {
            assignments.add("country = :country");
            parameters.addValue("country", autoPatch.getCountry());
        }

        if (autoPatch.isConditionUpdated()) {
            assignments.add("condition = :condition");
            parameters.addValue("condition", autoPatch.getCondition());
        }

        String assignmentStr = String.join(", ", assignments);
        String query = String.format(PATCH_AUTO_BY_ID_QUERY_TEMPLATE, assignmentStr);

        int affectedRows;

        try {
            affectedRows = jdbcTemplate.update(query, parameters);
        } catch (DuplicateKeyException e) {
            if (e.getCause().getMessage().contains("duplicate key value violates unique constraint \"faculties_name_key\"")) {
                throw new DataConflictException(String.format("Auto with name \"%s\" already exists!", autoPatch.getType()));
            }

            throw e;
        }

        if (affectedRows == 0) {
            throw new NotFoundException("Auto with id " + id + " not found!");
        }
    }


    @Override
    public void delete(Long id) {
        int affectedRows = jdbcTemplate.update(DELETE_AUTO_BY_ID_QUERY, new MapSqlParameterSource("id", id));

        if (affectedRows == 0) {
            throw new NotFoundException("Auto with id " + id + " not found!");
        }
    }

    private void appendConditions(StringBuilder queryBuilder, MapSqlParameterSource parameters, AutoFitterOptions params) {
        List<String> conditions = new ArrayList<>();

        if (params != null) {
            String nameParam = params.getType();
            if (nameParam != null) {
                conditions.add("type LIKE(:type)");
                parameters.addValue("type", "%" + nameParam + "%");
            }

            String infoParam = params.getBrand();
            if (infoParam != null) {
                conditions.add("brand LIKE(:brand)");
                parameters.addValue("brand", "%" + infoParam+ "%");
            }
        }

        if (!conditions.isEmpty()) {
            String conditionStr = String.join(" AND ", conditions);

            queryBuilder.append(" WHERE ");
            queryBuilder.append(conditionStr);
        }
    }
}
