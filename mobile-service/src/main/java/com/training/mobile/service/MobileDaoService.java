package com.training.mobile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.training.mobile.Dao.MobileRepository;
import com.training.mobile.dto.GetAllMobileRequest;
import com.training.mobile.dto.SaveMobileRequest;
import com.training.mobile.exception.MobileNotFoundException;
import com.training.mobile.model.LOB;
import com.training.mobile.model.Mobile;
import com.training.mobile.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileDaoService {
	@Autowired
	private MobileRepository mobileRepository;
	
	public List<Mobile> getAllMobiles(GetAllMobileRequest request){
		Status enumStatus = request.getStatus()==null ? null : Status.valueOf(request.getStatus());
		LOB enumlob = request.getLob()==null ? null : LOB.valueOf(request.getLob());
		
		return mobileRepository.findAll(request.getName(), request.getPrice(), enumStatus, enumlob);
	}

	public Mobile getMobileById(int mobileId)  {
		Optional<Mobile> mobile = mobileRepository.findById(mobileId);
		if(mobile.isPresent())
			return mobile.get();
		throw new MobileNotFoundException(1004, "Mobile not found with the given mobile Id :" +  mobileId);
	}

	public List<Mobile> getMobileByName(String name) {
		// TODO Auto-generated method stub
		return mobileRepository.findByName(name);
	}
	

	public void saveMobile(SaveMobileRequest saveMobileRequest) {
		Mobile mobile = Mobile
							.builder()
							.name(saveMobileRequest.getName())
							.price(saveMobileRequest.getPrice())
							.status(Status.valueOf(saveMobileRequest.getStatus()))
							.lob(LOB.valueOf(saveMobileRequest.getLob()) )
							.build();
							
		mobile.setPublicationDate(LocalDate.now());
		mobileRepository.save(mobile);
	}
	public void updateMobile(Mobile mobile) {
		mobile.setPublicationDate(LocalDate.now());
		
		mobileRepository.save(mobile);
	}

	public void deleteMobile(int mobileId) {
		
		Mobile mobile = getMobileById(mobileId);
		mobileRepository.delete(mobile);
	}

	public List<Mobile> getMobileByPrice(Integer price) {
		// TODO Auto-generated method stub
		return mobileRepository.findByPrice(price);
	}


//	private Mobile mobileById(int mobileId) {
//		Optional<Mobile> mobile = this.mobiles.stream().filter(m->m.getId()==mobileId).findFirst();
//		if(!mobile.isPresent()){
//			throw new MobileNotFoundException(13001, "Medium error : Mobile not found for mobileId :" + mobileId);
//		}
//		return mobile.get();
//	}




}
