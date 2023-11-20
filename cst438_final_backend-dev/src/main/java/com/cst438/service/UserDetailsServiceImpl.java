package  com.cst438.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cst438.domain.User1;
import com.cst438.domain.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User1 currentUser = repository.findByAlias(username); 

		UserBuilder builder = null;
		if (currentUser!=null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(currentUser.getPassword());
			builder.roles(currentUser.getRole());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();	    
	}
}
