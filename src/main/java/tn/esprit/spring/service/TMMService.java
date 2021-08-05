package tn.esprit.spring.service;

import org.springframework.stereotype.Service;

@Service
public class TMMService implements TMMManager{

	    @Override
	    public double getTMM() {
	        return 7;
	    }
}
