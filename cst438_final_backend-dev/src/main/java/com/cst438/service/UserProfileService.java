package com.cst438.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst438.domain.User1;
import com.cst438.domain.UserRepository;

import se.michaelthelin.spotify.model_objects.specification.User;

@Service
public class UserProfileService {

	@Autowired
	private UserRepository userRepository;
	
	public User1 insertOrUpdateUserDetails(User user, String accessToken, String refreshToken) {
		User1 userDetails = userRepository.findById(user.getId());
		if (Objects.isNull(userDetails)) { userDetails = new User1(); }
		userDetails.setAccessToken(accessToken);
		userDetails.setRefreshToken(refreshToken);
		return new User1();
	}
}
