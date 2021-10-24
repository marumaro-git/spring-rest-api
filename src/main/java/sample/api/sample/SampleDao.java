package sample.api.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDao implements ISampleDao {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public SampleDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<SampleResource> findList(SampleResource form) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT id, cd, name "
				+ "FROM sample "
				+ "WHERE 1 = 1");

		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		// パラメータが存在した場合、where句にセット
		if(form.getCd() != null && form.getCd() != "") {
			sqlBuilder.append(" AND cd = :cd");
			param.put("cd", form.getCd());
		}
		if(form.getName() != null) {
			sqlBuilder.append(" AND name = :name");
			param.put("name", form.getName());
		}

		String sql = sqlBuilder.toString();

		//タスク一覧をMapのListで取得
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
		//return用の空のListを用意
		List<SampleResource> list = new ArrayList<SampleResource>();

		//データをDiaryにまとめる
		for(Map<String, Object> result : resultList) {
			SampleResource sample = new SampleResource();
			sample.setId((int)result.get("id"));
			sample.setCd((String) result.get("cd"));
			sample.setName((String) result.get("name"));
			list.add(sample);
		}
		return list;
	}

	@Override
	public SampleResource findById(int id) throws  IncorrectResultSizeDataAccessException {
		String sql = "SELECT id, cd, name "
				+ "FROM sample "
				+ "WHERE id = :id";

		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		// 一件取得
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, param);
		SampleResource sample = new SampleResource();
		sample.setId((int)result.get("id"));
		sample.setCd((String) result.get("cd"));
		sample.setName((String) result.get("name"));
		
		return sample;
	}

	// 日記を登録する
	@Override
	public int insert(SampleResource form) {
		// 登録件数を格納
		int count = 0;
		String sql = "INSERT INTO sample(cd, name) "
				+ "VALUES(:cd, :name)";
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		param.put("cd", form.getCd());
		param.put("name", form.getName());
		count = jdbcTemplate.update(sql, param);
		return count;
	}

	// 日記を編集する
	@Override
	public int update(SampleResource form) {
		int count = 0;
		String sql = "UPDATE sample "
				+ "SET cd=:cd, name=:name "
				+ "WHERE id=:id";
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		param.put("id", form.getId());
		param.put("cd", form.getCd());
		param.put("name", form.getName());
		count = jdbcTemplate.update(sql, param);
		return count;
	}

	// 日記を削除する
	@Override
	public int delete(int id) {
		int count = 0;
		String sql = "DELETE FROM sample "
				+ "WHERE id = :id";
		// パラメータ設定用Map
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		count = jdbcTemplate.update(sql, param);
		return count;
	}
}
