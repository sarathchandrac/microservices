package com.training.mobile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.training.mobile.dto.GetAllMobileRequest;
import com.training.mobile.dto.MobileDto;
import com.training.mobile.dto.SaveMobileRequest;
import com.training.mobile.exception.MobileNotFoundException;
import com.training.mobile.model.LOB;
import com.training.mobile.model.Mobile;
import com.training.mobile.model.Status;
import com.training.mobile.repository.MobileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileDaoService {
	@Autowired
	private MobileRepository mobileRepository;
	
	public List<MobileDto> getAllMobiles(GetAllMobileRequest request){
		Status enumStatus = request.getStatus()==null ? null : Status.valueOf(request.getStatus());
		LOB enumlob = request.getLob()==null ? null : LOB.valueOf(request.getLob());
		
		List<Mobile> dbMobiles = mobileRepository.findAll(request.getName(), request.getPrice(), enumStatus, enumlob);
		List<MobileDto> mobilesDto =  dbMobiles.stream().map((Mobile mobile) -> convertEntityToDto(mobile)).collect(Collectors.toList());
		return mobilesDto;
	}
	


	public MobileDto getMobileById(int mobileId)  {
		return convertEntityToDto(getMobileObjectById(mobileId));
	}

	public List<MobileDto> getMobileByName(String name) {
		// TODO Auto-generated method stub
		return mobileRepository.findByName(name)
						.stream()
						.map(mobile -> convertEntityToDto(mobile))
						.collect(Collectors.toList());
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
	public MobileDto updateMobile(MobileDto mobileDto) {
		Mobile mobile = convertDtoToEntity(mobileDto);
		mobile.setPublicationDate(LocalDate.now());
		
		return convertEntityToDto(mobileRepository.save(mobile));
	}

	public void deleteMobile(int mobileId) {
		
		Mobile mobile = getMobileObjectById(mobileId);
		mobileRepository.delete(mobile);
	}

	public List<Mobile> getMobileByPrice(Integer price) {
		// TODO Auto-generated method stub
		return mobileRepository.findByPrice(price);
	}
	
	private Mobile getMobileObjectById(int mobileId)  {
		Optional<Mobile> mobile = mobileRepository.findById(mobileId);
		if(mobile.isPresent())
			return mobile.get();
		throw new MobileNotFoundException(1004, "Mobile not found with the given mobile Id :" +  mobileId);
	}
	
	private MobileDto convertEntityToDto(Mobile mobile) {
		return MobileDto
				.builder()
				.id(mobile.getId())
				.lob(mobile.getLob().name())
				.name(mobile.getName())
				.price(mobile.getPrice())
				.countryCode(mobile.getCountryCode())
				.status(mobile.getStatus().name())
				.publicationDate(mobile.getPublicationDate().toString())
				.build();
	}
	private Mobile convertDtoToEntity(MobileDto mobileDto) {
		return Mobile
				.builder()
				.id(mobileDto.getId())
				.lob(LOB.valueOf(mobileDto.getLob()) )
				.name(mobileDto.getName())
				.price(mobileDto.getPrice())
				.countryCode(mobileDto.getCountryCode())
				.status(Status.valueOf(mobileDto.getStatus()))
				.publicationDate(LocalDate.now())
				.build();
	}


//	private Mobile mobileById(int mobileId) {
//		Optional<Mobile> mobile = this.mobiles.stream().filter(m->m.getId()==mobileId).findFirst();
//		if(!mobile.isPresent()){
//			throw new MobileNotFoundException(13001, "Medium error : Mobile not found for mobileId :" + mobileId);
//		}
//		return mobile.get();
//	}




}
