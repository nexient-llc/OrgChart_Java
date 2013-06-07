package com.systemsinmotion.orgchart.dao;

import java.util.List;

public interface IDao<T> {
	/**
	 * Deletes the <code>T</code> instance from the database.
	 * 
	 * @param t
	 *            T instance to delete
	 */
	public abstract void delete(T t);

	/**
	 * Returns all <code>T</code> instances as a <code>List</code>
	 * 
	 * @return List of T
	 */
	public abstract List<T> findAll();

	/**
	 * Saves a give <code>T</code instance and returns its generated id.
	 * 
	 * @param t
	 *            The <code>T</code> instance to be saved
	 * @return The identifier (id) of the new <code>T</code> instance.
	 */
	public abstract Integer save(T t);

	/**
	 * Updates an existing <code>T</code> instance with new values.
	 * 
	 * @param t
	 *            The <code>T</code> instance to be updated
	 */
	public abstract void update(T t);

	/**
	 * Returns a <code>T</code> instance having a given <code>id</code> value.
	 * 
	 * @param id
	 *            Identifier of <code>T</code> instance to find
	 * @return Single <code>T</code> instance. Null if not found.
	 */
	public abstract T findById(Integer id);

	/**
	 * Returns a <code>T</code> instance with a given <code>name</code> value.
	 * 
	 * @param name
	 *            Name of the <code>T</code> to find
	 * @return Instance of <code>T</code>. Null if not found.
	 */
	public abstract T findByName(String name);

	public abstract String toString();
}
