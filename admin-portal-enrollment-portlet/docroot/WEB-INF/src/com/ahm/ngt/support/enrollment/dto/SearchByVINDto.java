package com.ahm.ngt.support.enrollment.dto;

/**
 * @author Paul Hsu
 *
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchByVINDto extends DtoBase implements Serializable {
	private static final long serialVersionUID = -1261821104276852974L;
	private String searchStatus;
	List<EnrollmentDto> enrollmentDtos = new ArrayList<EnrollmentDto>();

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public List<EnrollmentDto> getEnrollmentDtos() {
		return enrollmentDtos;
	}

	public void setEnrollmentDtos(List<EnrollmentDto> enrollmentDtos) {
		this.enrollmentDtos = enrollmentDtos;
	}

	public void addEnrollment(EnrollmentDto dto) {
		enrollmentDtos.add(dto);
	}

	public EnrollmentDto getFirstEnrollment() {
		if (enrollmentDtos.size() == 0)
			return null;
		Iterator<EnrollmentDto> iter = enrollmentDtos.iterator();
		EnrollmentDto first = (EnrollmentDto) iter.next();
		return first;

	}

	public EnrollmentDto findEnrolmentDtoByStatus(String status) {

		for (EnrollmentDto oneDto : enrollmentDtos) {
			if (oneDto.getEnrollmentStatus().equalsIgnoreCase(status))
				return oneDto;
		}

		return null;
	}

}
