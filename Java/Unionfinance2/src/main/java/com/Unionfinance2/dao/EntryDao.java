package com.Unionfinance2.dao;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Unionfinance2.base.BaseDao;
import com.Unionfinance2.entity.Entry;
import com.Unionfinance2.service.EntryService;
import com.Unionfinance2.util.PageCut;

@Service
public class EntryDao extends BaseDao<Entry> implements EntryService{
	
	@Override
	public boolean addEntry(Entry entry) {
		String hql = "from Entry e where e.en_name='"+entry.getEn_name()+"'";
		Entry entryDataBase = (Entry) this.uniqueResult(hql);
		if(entryDataBase==null){
			this.saveEntity(entry);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteEntry(Entry entry) {
		return this.deleteEntity(entry);
	}

	@Override
	public boolean updateEntry(Entry entry) {
		return this.updateEntity(entry);
	}
	
	@Override
	public Entry viewEntry(int id){
		Entry entry = this.getEntity(id);
		return entry;
	}

	@Override
	public List<Entry> CheckName() {
		return selectAll();
	}

	@Override
	public List<Entry> selectOne(String pass, String replace) {
		String sql = "select * from z_entry e where 1=1";
		if(!("").equals(pass) && !("").equals(replace)){
			sql+=" and e.en_type="+pass+" and e.en_name like"+"'%"+replace+"%'";
		}else if(!("").equals(pass) && ("").equals(replace)){
			sql+=" and e.en_type="+pass;
		}else{
			sql+=" and e.en_name like"+"'%"+replace+"%'";
		}
		List<Entry> l = this.executeSQLQuery(sql);
		return l;
	}

}
