package com.training.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.mobile.model.LOB;
import com.training.mobile.model.Mobile;
import com.training.mobile.model.Status;
@Repository
public interface MobileRepository extends JpaRepository<Mobile, Integer> {

	List<Mobile> findByName(String name);

	List<Mobile> findByPrice(Integer price);
	/* JPQL named Parameters
	 * 
	 */
	@Query("select m from Mobile m where (:name is null or m.name=:name) " +
			" and (:price is null or m.price=:price) " +
			" and (:status is null or m.status=:status) " +
			" and (:lob is null or m.lob=:lob) " 
			
			)
	List<Mobile> findAll(String name, Integer price, Status status, LOB lob);

	/* JPQL Placeholders 
	@Query("select m from Mobile m where (?1 is null or m.name=?1) " +
			" and (?2 is null or m.price=?2) " +
			" and (?3 is null or m.status=?3) " +
			" and (?4 is null or m.lob=?4) " 
			
			)
			

	List<Mobile> findAll(String name, Integer price, String status, String lob);
	*/
	/* Native Query
	@Query("select * from Mobile m where (?1 is null or m.name=?1) " +
			" and (?2 is null or m.price=?2) " +
			" and (?3 is null or m.status=?3) " +
			" and (?4 is null or m.lob=?4) " 
			
			)
			

	List<Mobile> findAll(String name, Integer price, Integer status, Integer lob);
	*/

}
