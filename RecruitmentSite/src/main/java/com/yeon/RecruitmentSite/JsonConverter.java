package com.yeon.RecruitmentSite;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//DB의 JSON 컬럼을 위한 JSON 변환기
@Slf4j
@Converter(autoApply = true)
@RequiredArgsConstructor
public class JsonConverter implements AttributeConverter<List<String>, String>{

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	//JSON 컬럼 값으로 변환
	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		
		if(attribute == null || attribute.isEmpty()) return "[]";
		
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			log.error("DB 컬럼 변환 중 에러"); 
			throw new RuntimeException("DB 컬럼 변환 중 에러", e);
		}
	}

	//JSON 컬럼 값을 List타입 값으로 변환
	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isEmpty()) return Collections.emptyList();
		
		try {
			return objectMapper.readValue(dbData, List.class);
		} catch (IOException  e) {
			log.error("Entity 변환 중 에러");  
			throw new RuntimeException("Entity 변환 중 에러", e);
		}
	}

}
