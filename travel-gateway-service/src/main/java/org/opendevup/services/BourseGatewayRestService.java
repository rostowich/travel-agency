package org.opendevup.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BourseGatewayRestService {

	@Autowired
	private RestTemplate restTemplate;
	
	/*@RequestMapping(value="/names")
	public Collection<Societe> listSocietes(@RequestParam(defaultValue="0")int page, 
			@RequestParam(defaultValue="30") int size){
		
		ParameterizedTypeReference<Resources<Societe>> responseType =
				new ParameterizedTypeReference<Resources<Societe>>() { };
		return restTemplate.exchange("http://societe-service/societes?page="+page+"&size="+size, 
						HttpMethod.GET, null, responseType).getBody().getContent();
		
	}*/
}
	
