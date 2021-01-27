package com.muaynetakip.Repository.Abstract;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
	public void add(T data) throws SQLException;
	public void update(int id, T data) throws SQLException, Exception;
	public void delete(int id) throws SQLException;
	public List<T> fetchAll() throws SQLException;
	public T fetchById(int id) throws SQLException, Exception;
}
