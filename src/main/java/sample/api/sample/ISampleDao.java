package sample.api.sample;

import java.util.List;

public interface ISampleDao {
	// 登録されている日記を取得
	List<SampleResource> findList(SampleResource from);
	
	// idを指定して日記を1件取得
	SampleResource findById(int id);
	
	// 日記を登録する
	int insert(SampleResource form);

	// 日記を更新する
	int update(SampleResource form);

	// 日記を削除する
	int delete(int id);
}
