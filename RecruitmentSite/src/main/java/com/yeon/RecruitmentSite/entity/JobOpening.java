package com.yeon.RecruitmentSite.entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.yeon.RecruitmentSite.converter.JsonConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "job_opening")
public class JobOpening {
	
	@Id
	@Column(name = "job_opening_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobOpeningId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	private String position;
	private int salary;
	private String contents;
	
	@Convert(converter = JsonConverter.class)
	private List<String> skill;
	
}
